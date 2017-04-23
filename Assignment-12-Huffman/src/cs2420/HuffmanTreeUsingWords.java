package cs2420;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static cs2420.Bit_Operations.*;
import static cs2420.Utility.increment;

/**
 * @author Original Huffman code by Erin Parker, April 2007
 *         Adapted by H. James de St. Germain to words as symbols 2008
 *         Heavily Updated by H. James de St. Germain 2017
 * 
 *         Implements file compression and decompression using Huffman's algorithm.
 * 
 *         Instead of compressing on just characters, we treat words as symbols
 *         as well. To see what the best levels of compression are, we choose
 *         the "N" most frequent words (modified by their length) and use these
 *         along with characters as symbols for compression.
 * 
 */
public class HuffmanTreeUsingWords
{
	/**
	 * allows us to tag the end of a file with a special character
	 * 
	 * otherwise it's not clear how to end the bit stream output of compression
	 * (i.e., bit stream needs to print a full byte at end, but we may only have a partial byte)
	 */
	final static String	EOF			= "EOF";

	/**
	 * For encoding, how many words to treat as symbols
	 */
	public int			WORD_COUNT;

	/**
	 * For a verbose account of what is going on, set these to true.
	 */
	private static boolean		VERBOSE_ENCODING_TREE 		= true;
	private static boolean		VERBOSE_FILE_SIZE			= true;
	private static boolean		VERBOSE_PRINT_SYMBOL_BITS	= true;
	private static boolean		VERBOSE_PRINT_TREE			= true;
	private static boolean compressing = true;

	/**
	 * The root of the Huffman tree
	 */
	private static Node					root;

	/**
	 * Constructor for an empty tree
	 * 
	 * @param words_as_symbols_count - take the top N words and use as symbols 
	 *
	 */
	public HuffmanTreeUsingWords( int words_as_symbols_count )
	{
		this.WORD_COUNT = words_as_symbols_count;
		
		this.root = null;
	}

	/**
	 * Generates a compressed version of the input file.
	 * 
	 * 1) read the file, counting all the symbols
	 * 2) create the huffman tree based on frequency counts
	 * 3) compress the data into a binary file
	 * 
	 * @param infile
	 *            - input file of (uncompressed) data
	 * @param outfile
	 *            - output file of compressed data
	 */
	public void compress_file( File infile, File outfile )
	{
		compressing = true;

		List<String> ordered_list_of_symbols = new ArrayList<>();

		Hashtable<String, Node> top_words;
		Hashtable<String, Node> all_symbols; 

		ArrayList<Character> buffer  = read_file(infile);
			
		top_words   = compute_most_common_word_symbols(buffer, this.WORD_COUNT);
		all_symbols = compute_remaining_single_character_symbols(buffer, top_words, ordered_list_of_symbols);

		create_tree( all_symbols.values() );

		compress_data( outfile, all_symbols, ordered_list_of_symbols );

	}

	/**
	 * Generates a decompressed version of the input file.
	 * 
	 * 1) Read the encoding information (how to reconstruct the tree)
	 * from the front of the file.
	 * 2) Build the Huffman tree (exactly as it was for compression)
	 * 3) read the bits from the compressed file one by one until
	 * a bit sequence finds a leaf in the huffman tree. report
	 * the symbol in the leaf and start again.
	 * 
	 * @param path
	 *            - Path to input file (of compressed data)
	 * @param outfile
	 *            - output file of decompressed data
	 */
	public void decompress_file( Path path, File outfile )
	{
		try
		{
			//
			// have to read the file as bytes and then process bits inside of those bytes
			//
			compressing = false;
			byte[] bytes = Files.readAllBytes(path);
			ByteBuffer byte_buffer = ByteBuffer.wrap(bytes);
			
			Hashtable<String, Node> symbols = new Hashtable<>();
			
			symbols = read_file_header_with_symbol_frequencies( byte_buffer ); // builds symbol frequency table

			if (VERBOSE_FILE_SIZE)
			{
				System.out.println("");
				System.out.printf("\tHeader Size in Bytes:   %10d\n", byte_buffer.position());
				System.out.printf("\tBody Size in Bytes:     %10d\n", byte_buffer.remaining());
				System.out.printf("\tTotal Size in Bytes:    %10d\n", bytes.length);
			}

			this.root = create_tree( symbols.values() );

			decompress_data(this.root, byte_buffer, outfile);
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
	}


	/**
	 * Write the compressed file, including the encoding information
	 * and the compressed data.
	 * 
	 * @param file  - the file to write the data to
	 * @param ordered_list_of_symbols - the symbols created by parsing the input file
	 * 
	 * @throws IOException - if something goes wrong...
	 */
	private static void compress_data( File file, Hashtable<String,Node> symbols, List<String> ordered_list_of_symbols ) 
	{
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream( file )))
		{
			byte[] file_header        = build_file_header( symbols.values() );
			byte[] symbol_bit_stream  = build_compressed_bit_stream(ordered_list_of_symbols, symbols);

//			System.out.println(Arrays.toString(symbol_bit_stream));

			out.write( file_header );
			out.write( symbol_bit_stream );
			
			if (VERBOSE_FILE_SIZE)
			{
				System.out.printf("Header Size:   %10d bytes\n", file_header.length);
				System.out.printf("Encoding Size: %10d bytes\n", symbol_bit_stream.length);
				System.out.printf("Total Size:    %10d bytes\n", (file_header.length + symbol_bit_stream.length));
			}

		}
		catch (IOException e)
		{
			throw new RuntimeException("Eror: could not read file");
		}

	}

	/**
	 * Read the compressed data bit stream, translating it into
	 * characters, and writing the uncompressed values into "out"
	 * 
	 * @param bits  - the compressed bit stream that needs to be turned back into real characters and words
	 * @param outfile - the file to put this data into
	 * @throws IOException - if something goes wrong
	 */
	private static void decompress_data( Node huffman_root, ByteBuffer bits, File outfile ) throws IOException
	{
		List<String> symbol_list = convert_bitstream_to_original_symbols( bits, huffman_root);
		
		write_data( outfile, symbol_list );
	}

	/**
	 * The compressed file has two parts: 
	 *   a) the HEADER        - symbol frequency count
	 *   b) the DATA          - compressed symbol bit stream
	 *   
	 * Here we read the HEADER and build the list of symbols (and counts)
	 * 
	 * 1) read four bytes (representing the number of characters in the symbol
	 * 2) read that many groups of bytes (each is a character in the symbol)
	 * 3) read four bytes (representing the symbol's frequency)
	 * 
	 *    repeat from 1, unless we read a zero, then the encoding table is complete
	 * 
	 * 
	 * @param file_bytes - the bytes from the compressed file (which start with the header information)
	 * @return the hashtable containing all the symbol nodes
	 */
	private static Hashtable<String, Node> read_file_header_with_symbol_frequencies( ByteBuffer file_bytes )
	{
		
		if ( VERBOSE_ENCODING_TREE ) 		{ System.out.println("\n---------------- Reading encoding tree information  -----------------"); }

		// FIXME:
		Hashtable<String, Node> result = new Hashtable<>();
		String symbol = "";
		while(true) {
			if(file_bytes.remaining() == 0){
				break;
			}
			int length = file_bytes.getInt();
			if(length == 0){
				break;
			}
			for(int i = 0; i<length; i++) {
				symbol += (char) file_bytes.get();
			}
			int frequency = file_bytes.getInt();
			if (!result.contains(symbol)) {
				result.put(symbol, new Node(symbol, frequency));
			}
			symbol = "";
		}
		if ( VERBOSE_ENCODING_TREE )		{	System.out.println("\n\tRead encoding table. Size:  " + file_bytes.position() + " bytes"); }
		
		return result;
	}

	/**
	 * transfer all the data from the file into an array list
	 * @param infile - file we are compressing
	 *  
	 * @return the ArrayList representation of the file (ordered of course)
	 */
	static ArrayList<Character> read_file(File infile ) 
	{
		final int End_Of_Stream = -1;
		ArrayList<Character> buffer = new ArrayList<Character>(1000);

		try (FileReader reader = new FileReader(infile))
		{

			while (true)
			{
				int character_code = reader.read();

				if (character_code == End_Of_Stream)
				{
					break;
				}

				buffer.add((char) character_code);
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error: reading the file.");
		}
		
		return buffer;
	}
	
	/**
	 * To build the Huffman tree (for compression), we must compute the list of symbols from the file.
	 * 
 	 * Algorithm:
 	 *  
	 *   1) counts how often all the words appear
	 *   2) keep the N most common words
	 *   3) re-parse the file to count all non words
	 *   
	 *   4) put every symbol into a Node and store this in a hash table 
	 *   5) put every symbol in order into a list for future processing
	 *
	 * @param buffer        - input file
	 * @param count         - find the top N (count) words
	 *            
	 * WARNING: modifies the symbol hashtable
	 *  
	 * @return an ordered list of the symbols as they appear in the file
	 *            
	 */
	static Hashtable<String, Node> compute_most_common_word_symbols( ArrayList<Character> buffer, int count ) 
	{

		//  write code to find the N most frequently used words (word --> more than one letter in a row)
		// return a hash table containing nodes containing these words and frequnecies
		Hashtable<String, Node> result = new Hashtable<>();
		if(count == 0){
			return result;
		}
		String current = "";
		for(Character character : buffer){
			if(Character.isLetter(character)) {
				current += character;
			}else{
				if(current.length() > 0) {
					increment(current, result);
				}
				current = "";
			}
		}
		return result;
	}

	/**
	 * count all the symbols in the file including single characters and predefined "word" symbols
	 * 
	 * @param buffer  - the file's characters
	 * @param word_symbols - the words that have already been identified as most common and are to be used as symbols
	 * @param ordered_list_of_symbols - RETURNS an array list of all symbols in the file.
	 * 
	 * @return the final symbol table representing the huffman nodes (not connected yet) for the symbols in the file. 
	 */
	public static Hashtable<String, Node> 
	compute_remaining_single_character_symbols( ArrayList<Character> buffer, 
	                                            Hashtable<String, Node> word_symbols,
	                                            List<String> ordered_list_of_symbols)
	{
		//
		// Now count all the other symbols (e.g., single characters not in symbol words)
		// 

		Hashtable<String, Node> all_symbols = new Hashtable<>();
		String current_symbol = "";
		
		all_symbols.putAll(word_symbols);

		for (Character ch : buffer)
		{
			if (Character.isLetter(ch)) // build up words
			{
				current_symbol += ch;
			}
			else // a non letter (thus marking the division between possible word symbols
			{
				// 1) if we have started to build a word
				if (current_symbol.length() > 0)
				{
					// if it is not a TOP word
					if (!word_symbols.containsKey(current_symbol))
					{
						// add all it's letters to the symbols table and the ordered list
						for (int i = 0; i < current_symbol.length(); i++)
						{
							increment("" + current_symbol.charAt(i), all_symbols);
							ordered_list_of_symbols.add("" + current_symbol.charAt(i));
						}
					}
					else // just add the word to the ordered list
					{
						ordered_list_of_symbols.add(current_symbol);
					}
				}

				// 2) account for the current character (non-letter)
				increment("" + ch, all_symbols);
				ordered_list_of_symbols.add("" + ch);

				// start over building another word
				current_symbol = "";
			}
		}

		// add end of file at end of symbols
		all_symbols.put(EOF, new Node(EOF, 1));
		ordered_list_of_symbols.add(EOF);
		
		return all_symbols;
	}

	
	
	/**
	 * given a list of bits (and the root of the huffman tree) create a list of symbols
	 * 
	 * 
	 * DECOMPRESSION Pseudocode
	 * 
	 * For each bit in the bit stream (the compressed file, after the header),
	 *   code += get the next bit
	 *   if code forms path from root of huffman tree to leaf
	 *      we have a symbol, save it
	 *      reset code to empty
	 *   end
	 * end
	 * 
	 * @param bit_stream - all the bits representing the symbols in the file
	 * @param root - the root of the huffman tree
	 * 
	 * @return the reconstructed list of symbols
	 */
	static List<String> convert_bitstream_to_original_symbols( ByteBuffer bit_stream, Node root )
	{
		
		if ( VERBOSE_PRINT_SYMBOL_BITS )
		{
			System.out.println("------------- Converting bit sequences back into symbols -------------------");
		}
		List<String> strings = new LinkedList<>();
		String code = "";
		while (bit_stream.remaining() > 0){
			byte b = bit_stream.get();
			for(int i = 0; i < 8; i++){
				code += get_bit(b,i)?1:0;
				String symbol = root.get_symbol(code);
				if(symbol != null) {
//					System.out.print(symbol);
					if(symbol.equals("EOF")){
						break;
					}
					strings.add(symbol);
					code = "";
				}
			}
		}
//		throw new RuntimeException("Error: did not find EOF termination character");
		return strings;
	}

	/**
	 * COMPRESSION - write the symbol frequencies
	 * 
	 * 1) Writes the symbols and  frequency information to the output file,
	 *    o) This allows the Huffman tree to be reconstructed at the time of decompression.
	 *    
	 * 2) NOTE: for debug purposes, the symbols are written from most frequent to least frequent... but
	 *       this is not necessary 
	 *    
	 * 3) FORMAT of HEADER is:
	 * 
	 *     LENGTH, SYMBOL, FREQUENCY         (repeated for all symbols)
	 *     ZERO                              (so we know we are out of symbols - okay because a length of 0 doesn't make sense)
	 *     
	 * 4) EXAMPE (for the following symbols and frequencies: (a,5) (hello,10), (EOF,1)
	 * 
	 *    1a5, 5hello10, 3EOF1               (note: there of course are no spaces or commas and this information is written as bits....)
	 * 
	 * @param huffman_nodes - the collection of symbols and frequencies (i.e., Nodes) in the document 
	 * 
	 * @throws IOException - if something goes wrong with the file writing
	 */
	private static byte[] build_file_header( Collection<Node> huffman_nodes ) throws IOException
	{
		int count = 0;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		if ( VERBOSE_ENCODING_TREE)
		{
			System.out.printf("------------ encoding table (ordered by frequency) ------------\n");
		}

		//        Note: you will want to use the convert_integer_to_bytes method
		//              you will want to use the out.write method
		//
		//  pseudo code
		//    for every node in the list of huffman_nodes
		//        write the length of the symbol (to the out variable)
		//        write the symbol itself
		//        write the frequency
		//    write a close 0
		//    covert out into a byte array and return it.
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
		priorityQueue.addAll(huffman_nodes);

		while(priorityQueue.size() > 0){
			Node node = priorityQueue.poll();
			out.write(convert_integer_to_bytes(node.get_symbol().length()));
			count+=4;
			for(int i = 0; i<node.get_symbol().length(); i++) {
				out.write(node.get_symbol().charAt(i));
				count++;
			}
			out.write(convert_integer_to_bytes(node.get_frequency()));
			count+=4;
		}
		out.write(convert_integer_to_bytes(0));
		count+=4;
		if ( VERBOSE_ENCODING_TREE)
		{
			System.out.println("\n\tEncoding Table Size:  " + count + " bytes");
		}
		
		return out.toByteArray();

	}

	/**
	 * DECOMPRESSION
	 * 
	 * Writes the decompressed data (Symbols) to the output file.
	 * 
	 * As each symbol is decompressed, write its component characters
	 * 
	 * @param outfile
	 *            - stream for the output file
	 * @param symbol_list
	 *            - the symbolss to write
	 * 
	 * @throws IOException
	 */
	static void write_data( File outfile, List<String> symbol_list ) throws IOException
	{
		try (FileOutputStream fs = new FileOutputStream(outfile))
		{
			for (String symbol : symbol_list)
			{
				for (int i = 0; i < symbol.length(); i++)
				{
					fs.write(symbol.charAt(i));
				}
			}
		}
	}

	/**
	 * COMPRESSION
	 * 
	 * For each symbol in the input file, encode it using the Huffman
	 * tree by writing the bit code to the output file.
	 * 
	 * This method uses the "determine_bit_pattern_for_symbol" method
	 * 
	 * PSEUDOCODE:
	 * 
	 *        for every symbol (in order )
	 *           find bit pattern
	 *           put bits from pattern into bitset
	 *        return the byte[] from the bitset
	 * 
	 * @param ordered_list_of_symbols
	 *            - all the letters (words/symbols) from the file in order
	 * @param table - the hashtable from symbol string to node
	 * 
	 * @return the bytes representing the bit stream of the compressed symbols
	 *            
	 * @throws IOException
	 */
	static byte[] build_compressed_bit_stream( List<String> ordered_list_of_symbols, Hashtable<String, Node> table ) throws IOException
	{
		BitSet bitset = new BitSet();

		if (VERBOSE_PRINT_SYMBOL_BITS)
		{
			System.out.println("\n----------- Compressing  --------------");
			System.out
					.println("Building bit representation of each symbol for " + ordered_list_of_symbols.size() + " symbols");
		}

		int current = 0;
		for(int k = 0; k<ordered_list_of_symbols.size(); k++){
			LinkedList<Integer> bit_pattern = determine_bit_pattern_for_symbol(table.get(ordered_list_of_symbols.get(k)));
			for(int i : bit_pattern){
				bitset.set(current++, i != 0);
			}
		}
		if ( VERBOSE_PRINT_SYMBOL_BITS ) { System.out.println("\n----------- done --------------"); }

		return get_bytes(bitset);
	}

	/**
	 * Constructs a Huffman tree to represent bit codes for each character.
	 * 
	 * This is the method is the HEART of the huffman algorithm.
	 * 
	 * Algorithm:
	 * 
	 *   o) put all the nodes into a priority queu
	 * 
	 *   1) choose the two least frequent symbols (removing from PQ)
	 *   2) combine these in a new huffman node
	 *   3) put this new node back in the PQ
	 *   4) repeat until no nodes in PQ
	 * 
	 * @return the root of the tree
	 * @param nodes the nodes to be built into a tree
	 */
	static Node create_tree( Collection<Node> nodes )
	{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.addAll(nodes);
		int spot = 0;
		while (pq.size() > 1) {
			Node left = pq.poll();
			Node right = pq.poll();
			Node parent = new Node("N_"+spot,left,right);
			left.set_parent(parent);
			right.set_parent(parent);
			pq.offer(parent);
			spot++;
		}
		root = pq.peek();
		 if ( VERBOSE_PRINT_TREE ){
			 try {
				 writeTree(root.createDot());
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
		
		return root;

	}

	private static void writeTree(String tree) throws IOException {
		if(compressing){
			FileWriter fw = new FileWriter("Resources/compress.dot");
			PrintWriter pw = new PrintWriter(fw);
			pw.print(tree);
			pw.close();
		}else{
			FileWriter fw = new FileWriter("Resources/decompress.dot");
			PrintWriter pw = new PrintWriter(fw);
			pw.print(tree);
			pw.close();
		}
	}


	/**
	 * Returns the bit code for a symbol.
	 * 
	 * This is computed by traversing the path from the given leaf node up to the 
	 * root of the tree. 
	 * 
	 *   1) when encountering a left child, a 0 to be pre-appended to the bit code, and
	 *   2) when encountering a right child causes a 1 to be pre-appended.
	 *	
	 * For example: the symbol "A" might return the code "1011101"
	 * 
	 * QUESTION: why do we use a linkedlist as the return type?
	 * 
	 * @param    - symbol to be encoded
	 * @param leaf       - the node in the huffman tree containing the symbol
	 * 
	 */
	private static LinkedList<Integer> determine_bit_pattern_for_symbol( Node leaf )
	{
		LinkedList<Integer> bit_patern = new LinkedList<>();
		Node temp = leaf;
		while(temp.get_parent() != null){
			if(temp == temp.parents_left()){
				bit_patern.addFirst(0);
			}
			if(temp == temp.parents_right()){
				bit_patern.addFirst(1);
			}
			temp = temp.get_parent();
		}
		return bit_patern;
	}

	public static void setVerboseEncodingTree(boolean verboseEncodingTree) {
		VERBOSE_ENCODING_TREE = verboseEncodingTree;
	}

	public static void setVerbosePrintSymbolBits(boolean verbosePrintSymbolBits) {
		VERBOSE_PRINT_SYMBOL_BITS = verbosePrintSymbolBits;
	}

	public static void setVerboseFileSize(boolean verboseFileSize) {
		VERBOSE_FILE_SIZE = verboseFileSize;
	}

	public static void setVerbosePrintTree(boolean verbosePrintTree) {
		VERBOSE_PRINT_TREE = verbosePrintTree;
	}
}
