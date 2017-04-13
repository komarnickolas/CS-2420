package hash_tables;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/6/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Testing {
    Hash_Map<My_String, Integer> hash_map;
    int current = 0;
    public void newMap(int capacity){
        switch (current){
            case 0:
                hash_map = new Hash_Table_Linear_Probing<>(capacity);
                current++;
                break;
            case 1:
                hash_map = new Hash_Table_Quadratic_Probing<>(capacity);
                current++;
                break;
            case 2:
                hash_map = new Hash_Table_Chaining<>(capacity);
                current = 0;
                break;
        }
        try(Stream<String> stream = Files.lines(Paths.get("Resources/names"))){
            Object[] lines = stream.toArray();
            for(Object s : lines){
                String[] current = ((String)s).split("\\s+");
                hash_map.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInsert(){
        hash_map = new Hash_Table_Linear_Probing<>(79);
        try(Stream<String> stream = Files.lines(Paths.get("Resources/names"))){
            Object[] lines = stream.toArray();
            for(int i = 0; i<79; i++){
                String[] current = ((String)lines[i]).split("\\s+");
                hash_map.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        hash_map = new Hash_Table_Quadratic_Probing<>(79);
        try(Stream<String> stream = Files.lines(Paths.get("Resources/names"))){
            Object[] lines = stream.toArray();
            for(int i = 0; i<79; i++){
                String[] current = ((String)lines[i]).split("\\s+");
                hash_map.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        hash_map = new Hash_Table_Chaining<>(79);
        try(Stream<String> stream = Files.lines(Paths.get("Resources/names"))){
            Object[] lines = stream.toArray();
            for(int i = 0; i<79; i++){
                String[] current = ((String)lines[i]).split("\\s+");
                hash_map.insert(new My_String(current[0] + " " + current[1]), Integer.parseInt(current[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFind(){
        newMap(1000);
        assertEquals((Integer) 36, hash_map.find(new My_String("Chrissie Brown")));
        assertEquals((Integer) 36, hash_map.find(new My_String("Kyle Kadel")));
        assertEquals((Integer) 34, hash_map.find(new My_String("Zachary Vinsant")));
        assertEquals((Integer) 45, hash_map.find(new My_String("Moss Elizabeth")));
        assertEquals((Integer) 32, hash_map.find(new My_String("Dean Smail")));
        assertEquals(null, hash_map.find(new My_String("Dean Smai")));
        newMap(1000);
        assertEquals((Integer) 36, hash_map.find(new My_String("Chrissie Brown")));
        assertEquals((Integer) 36, hash_map.find(new My_String("Kyle Kadel")));
        assertEquals((Integer) 34, hash_map.find(new My_String("Zachary Vinsant")));
        assertEquals((Integer) 45, hash_map.find(new My_String("Moss Elizabeth")));
        assertEquals((Integer) 32, hash_map.find(new My_String("Dean Smail")));
        assertEquals(null, hash_map.find(new My_String("Dean Smai")));
        newMap(1000);
        assertEquals((Integer) 36, hash_map.find(new My_String("Chrissie Brown")));
        assertEquals((Integer) 36, hash_map.find(new My_String("Kyle Kadel")));
        assertEquals((Integer) 34, hash_map.find(new My_String("Zachary Vinsant")));
        assertEquals((Integer) 45, hash_map.find(new My_String("Moss Elizabeth")));
        assertEquals((Integer) 32, hash_map.find(new My_String("Dean Smail")));
        assertEquals(null, hash_map.find(new My_String("Dean Smai")));
    }
}
