package cs2420;


import Tests.Test;
import javafx.scene.chart.XYChart;

import java.io.File;
import java.io.IOException;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/22/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class HuffmanTest extends Test {

    String file_name = "";

    public HuffmanTest(String file_name, int MAX, int COUNT) throws IOException {
        super(file_name.split("/")[1],MAX,COUNT);
        this.file_name = file_name;
        setWriter("Resources/TestData/Words As Symbols "+file_name.split("/")[1]+".txt");
        println("Words;Time;CompressedFileSize;InitialFileSize");
    }

    @Override
    protected Integer call() throws Exception {
        HuffmanTreeUsingWords huffman;
        File file = new File(file_name);
        for (int i = 0; i <= getMax(); i+=getIncrement()) {
            updateProgress(i);
            huffman = new HuffmanTreeUsingWords(i);
            huffman.setVerboseEncodingTree(false);
            huffman.setVerboseFileSize(false);
            huffman.setVerbosePrintSymbolBits(false);
            huffman.setVerbosePrintTree(false);
            File compressed = new File("Resources/compressed/"+file_name.split("/")[1] +"/" +file_name.split("/")[1] + "." + huffman.WORD_COUNT + ".huf");
            start();
            huffman.compress_file(file, compressed);
            end();
            println(i+";"+getTotal() +";" + compressed.length() +";"+file.length());
            updateData(new XYChart.Data(i,compressed.length()),"Compressed Size");
            updateData(new XYChart.Data(i,file.length()),"File Size");
        }
        return null;
    }

}
