import cracking.Crack;
import hash_tables.*;

import javax.security.auth.kerberos.KeyTab;
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

    private static Collection<String> hashes, names;
    private static boolean I_DID_NOT_STUDY_ALGORITHMS = false;

    public static void main(String[] args) {
        if (I_DID_NOT_STUDY_ALGORITHMS) {
            hashes = Crack.read_file_into_array("Resources/hashwords_long");
            names = Crack.read_file_into_array("Resources/names");
        } else {
            hashes = Crack.read_file_into_hash_set("Resources/hashwords_long");
            names = Crack.read_file_into_hash_set("Resources/names");
        }
//        ArrayList<ArrayList<String>> lists = Crack.multi_thread_brute_force_attack(5, hashes);
//        for (ArrayList<String> list : lists) {
//            for (String s : list) {
//                System.out.println(s);
//            }
//        }
        Hash_Map<My_String, Integer> hash_map = new Hash_Table_Chaining<>(10);
        print(hash_map);
        hash_map = new Hash_Table_Linear_Probing<>(10);
        print(hash_map);
        hash_map = new Hash_Table_Quadratic_Probing<>(10);
        print(hash_map);
    }

    private static void print(Hash_Map<My_String, Integer> hash_map){
        hash_map.set_resize_allowable(true);
        for(String s : names){
            String[] current = s.split("\\s+");
            hash_map.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
        }
        hash_map.find(new My_String("Bettye Dickinson"));
        System.out.println(hash_map.toString());
    }
}
