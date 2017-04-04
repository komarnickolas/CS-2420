import cracking.Crack;
import hash_tables.Hash_Map;
import hash_tables.Hash_Table_Linear_Probing;
import hash_tables.String_Hash_Functor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/1/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Main{

    private static Collection<String> hashes;
    private static boolean I_DID_NOT_STUDY_ALGORITHMS = false;

    public static void main(String[] args) {
        if (I_DID_NOT_STUDY_ALGORITHMS) {
            hashes = Crack.read_file_into_array("Resources/hashwords_long");
        } else {
            hashes = Crack.read_file_into_hash_set("Resources/hashwords_long");
        }
        ArrayList<ArrayList<String>> lists = Crack.multi_thread_brute_force_attack(3, hashes);
        for (ArrayList<String> list : lists) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}
