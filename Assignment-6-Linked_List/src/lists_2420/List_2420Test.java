package lists_2420;

import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/17/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class List_2420Test {

    boolean list = true;
    List_2420 test_list;
    /**
     * allow changing between linked and array lists
     */
    public List_2420 new_list() {
        if(list) {
            list = false;
            Linked_List_2420 linkedList = new Linked_List_2420();
            for(int i = 0; i<5; i++){
                linkedList.add_first(i);
            }
            for(int i = 5; i<10; i++){
                linkedList.add_last(i);
            }
            return linkedList;
        }else{
            list = true;
            Array_List_2420 arrayList = new Array_List_2420();
            for(int i = 0; i<5; i++){
                arrayList.add_first(i);
            }
            for(int i = 5; i<10; i++){
                arrayList.add_last(i);
            }
            return arrayList;
        }
    }

    @Test
    public void testContains(){
        test_list = new_list();
        if (test_list instanceof Linked_List_2420){
            Linked_List_2420 linked_list = (Linked_List_2420)test_list;
            assertTrue(linked_list.contains(0));
            assertFalse(linked_list.contains(100));
            assertTrue(linked_list.contains_recursive(0));
            assertFalse(linked_list.contains_recursive(100));
        }
        test_list = new_list();
        if (test_list instanceof Array_List_2420){
            Array_List_2420 array_list = (Array_List_2420)test_list;
            assertTrue(array_list.contains(0));
            assertFalse(array_list.contains(100));
            assertTrue(array_list.contains_recursive(0));
            assertFalse(array_list.contains_recursive(1000));
        }
    }

    @Test
    public void testRemove(){
        test_list = new_list();
        if (test_list instanceof Linked_List_2420){
            Linked_List_2420 linked_list = (Linked_List_2420)test_list;
            assertEquals(4, linked_list.remove_first());
            assertEquals(9, linked_list.remove_last());
        }
        test_list = new_list();
        if (test_list instanceof Array_List_2420){
            Array_List_2420 array_list = (Array_List_2420)test_list;
            assertEquals(4, (int) array_list.remove_first());
            assertEquals(9,(int) array_list.remove_last());
        }
    }

    @Test
    public void testAddMiddle(){
        test_list = new_list();
        if(test_list instanceof Linked_List_2420){
            Linked_List_2420 linked_list = (Linked_List_2420)test_list;
            linked_list.add_middle(5,100);
        }
        test_list = new_list();
        if(test_list instanceof Array_List_2420){
            Array_List_2420 array_list = (Array_List_2420)test_list;
            array_list.add_middle(5,100);
        }
    }


    @Test
    public void testReverse(){
        test_list = new_list();
        if(test_list instanceof Linked_List_2420){
            Linked_List_2420 linked_list = (Linked_List_2420)test_list;
            linked_list.reverse();
        }
        test_list = new_list();
        if(test_list instanceof Array_List_2420){
            Array_List_2420 array_list = (Array_List_2420)test_list;
            array_list.reverse();
        }
    }
}
