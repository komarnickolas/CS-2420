import cracking.Crack;
import hash_tables.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/6/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Timing {
    private static Collection<String> hashes, names;
    private static boolean I_DID_NOT_STUDY_ALGORITHMS = false;
    private static PrintWriter hash_map_linear_insert_pw, hash_map_quadratic_insert_pw, hash_map_chaining_insert_pw;
    private static PrintWriter hash_map_linear_find_pw, hash_map_quadratic_find_pw, hash_map_chaining_find_pw;
    private static FileWriter hash_map_linear_insert_fw, hash_map_quadratic_insert_fw, hash_map_chaining_insert_fw;
    private static FileWriter hash_map_linear_find_fw, hash_map_quadratic_find_fw, hash_map_chaining_find_fw;
    public static void main(String[] args) throws IOException {
        hash_map_chaining_insert_fw = new FileWriter("Hash Map Chaining Insert.txt");
        hash_map_chaining_insert_pw = new PrintWriter(hash_map_chaining_insert_fw);

        hash_map_linear_insert_fw = new FileWriter("Hash Map Linear Insert.txt");
        hash_map_linear_insert_pw = new PrintWriter(hash_map_linear_insert_fw);

        hash_map_quadratic_insert_fw = new FileWriter("Hash Map Quadratic Insert.txt");
        hash_map_quadratic_insert_pw = new PrintWriter(hash_map_quadratic_insert_fw);


        hash_map_chaining_find_fw = new FileWriter("Hash Map Chaining Find.txt");
        hash_map_chaining_find_pw = new PrintWriter(hash_map_chaining_find_fw);

        hash_map_linear_find_fw = new FileWriter("Hash Map Linear Find.txt");
        hash_map_linear_find_pw = new PrintWriter(hash_map_linear_find_fw);

        hash_map_quadratic_find_fw = new FileWriter("Hash Map Quadratic Find.txt");
        hash_map_quadratic_find_pw = new PrintWriter(hash_map_quadratic_find_fw);

        if (I_DID_NOT_STUDY_ALGORITHMS) {
            names = Crack.read_file_into_array("Resources/names");
        } else {
            names = Crack.read_file_into_hash_set("Resources/names");
        }

        hash_map_linear_insert_pw.println("Size;Time");
        hash_map_chaining_insert_pw.println("Size;Time");
        hash_map_quadratic_insert_pw.println("Size;Time");
        hash_map_linear_find_pw.println("Size;Time");
        hash_map_chaining_find_pw.println("Size;Time");
        hash_map_quadratic_find_pw.println("Size;Time");
        for(int i = 1000; i<=5000; i+=200) {
            Hash_Map<My_String, Integer> hash_map_linear = new Hash_Table_Linear_Probing<>(i);
            Hash_Map<My_String, Integer> hash_map_quadratic = new Hash_Table_Quadratic_Probing<>(i);
            hash_map_linear.set_resize_allowable(false);
            hash_map_quadratic.set_resize_allowable(false);
            try(Stream<String> stream = Files.lines(Paths.get("Resources/names"))){
                Object[] lines = stream.toArray();

                Long start = System.nanoTime();
                for(Object s : lines) {
                    String[] current = ((String)s).split("\\s+");
                    hash_map_linear.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
                }
                Long end = System.nanoTime() - start;
                double seconds = ((double)end / 1000000000);
                hash_map_linear_insert_pw.println(i + ";"+seconds);
                System.out.println(hash_map_linear.toString());

                start = System.nanoTime();
                for(Object s : lines) {
                    String[] current = ((String)s).split("\\s+");
                    hash_map_quadratic.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
                }
                end = System.nanoTime() - start;
                seconds = ((double)end / 1000000000);
                hash_map_quadratic_insert_pw.println(i + ";"+seconds);
                System.out.println(hash_map_quadratic.toString());
                hash_map_linear.reset_stats();
                hash_map_quadratic.reset_stats();
                start = System.nanoTime();
                for(Object s : lines) {
                    String[] current = ((String)s).split("\\s+");
                    hash_map_linear.find(new My_String(current[0] + " " + current[1]));
                }
                end = System.nanoTime() - start;
                seconds = ((double)end / 1000000000);
                hash_map_linear_find_pw.println(i + ";"+seconds);
                System.out.println(hash_map_linear.toString());

                start = System.nanoTime();
                for(Object s : lines) {
                    String[] current = ((String)s).split("\\s+");
                    hash_map_quadratic.find(new My_String(current[0] + " " + current[1]));
                }
                end = System.nanoTime() - start;
                seconds = ((double)end / 1000000000);
                hash_map_quadratic_find_pw.println(i + ";"+seconds);
                System.out.println(hash_map_quadratic.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(int i = 10; i < 1000; i+=10){
            Hash_Map<My_String, Integer> hash_map_chaining = new Hash_Table_Chaining<>(i);
            hash_map_chaining.set_resize_allowable(true);
            try(Stream<String> stream = Files.lines(Paths.get("Resources/names"))){
                Object[] lines = stream.toArray();
                Long start = System.nanoTime();
                for(Object s : lines) {
                    String[] current = ((String)s).split("\\s+");
                    hash_map_chaining.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
                }
                Long end = System.nanoTime() - start;
                double seconds = ((double)end / 1000000000);
                hash_map_chaining_insert_pw.println(i + ";"+seconds);
                System.out.println(hash_map_chaining.toString());
                hash_map_chaining.reset_stats();
                start = System.nanoTime();
                for(Object s : lines) {
                    String[] current = ((String)s).split("\\s+");
                    hash_map_chaining.find(new My_String(current[0] + " " + current[1]));
                }
                end = System.nanoTime() - start;
                seconds = ((double)end / 1000000000);
                hash_map_chaining_find_pw.println(i + ";"+seconds);
                System.out.println(hash_map_chaining.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hash_map_chaining_insert_pw.close();
        hash_map_linear_insert_pw.close();
        hash_map_quadratic_insert_pw.close();
        hash_map_chaining_find_pw.close();
        hash_map_linear_find_pw.close();
        hash_map_quadratic_find_pw.close();
    }
}
