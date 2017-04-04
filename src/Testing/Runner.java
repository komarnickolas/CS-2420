package Testing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by komar on 12/20/2016.
 */
public class Runner {
    String filename = "";

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.run();
        writeFile( "output.graph", test.series);
    }

    public Runner(String filename) throws IOException {
        this.filename = filename;
        Test test = new Test();
        writeFile(filename + ".graph", test.series);
    }

    private static boolean writeFile(String outputFile, HashMap<String, ArrayList<String>> map) throws IOException {
        try {
            FileWriter fw = new FileWriter(outputFile);
            PrintWriter pr = new PrintWriter(fw);
            pr.println(map.size());
            pr.print("AnagramUtil ");
            pr.print("Size ");
            pr.print("Time(S) ");
            pr.print("AnagramUtil ");
            pr.println();
            map.forEach((s, l) -> {
                pr.print(s);
                pr.println();
                for(String string : l){
                    pr.print(string);
                    pr.println();
                }
                pr.print("-----------------------");
                pr.println();
            });
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
