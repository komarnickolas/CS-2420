package lists_2420;

import java.util.*;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/17/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Array_List_2420 implements List_2420<Integer> {
    Integer[] array = new Integer[1024];
    Integer start,end, size;

    public Array_List_2420(){
        size = 0;
    }

    /**
     * Doubles array size
     */
    private void expand(){
        Integer[] temp = new Integer[array.length*2];
        for(int i = 0; i<array.length; i++){
            temp[i] = array[i];
        }
        array = temp;
    }

    @Override
    public void add_first(Integer data) {
        check_if_full();
        if(empty()){
            array[0] = data;
        }else{
            Integer[] temp = new Integer[array.length];
            temp[0] = data;
            for(int i = 1; i<array.length; i++){
                temp[i] = array[i-1];
            }
            array = temp;
        }
        size++;
    }

    @Override
    public void add_last(Integer data) {
        check_if_full();
        array[size()] = data;
        size++;
    }

    @Override
    public void add_middle(int after, Integer data) {
        check_if_full();
        if(empty()){
            array[0] = data;
        }
        Integer[] temp = new Integer[array.length];
        int spot = 0;
        for(int i = 0; i<=after; i++) {
            temp[spot] = array[i];
            spot++;
        }
        temp[spot] = data;
        spot++;
        for (int i = spot - 1; i<size(); i++){
            temp[spot] = array[i];
            spot++;
        }
        size++;
        array = temp;
    }

    @Override
    public void clear() {
        size = 0;
        array = new Integer[1024];
    }

    @Override
    public Integer get_first() throws NoSuchElementException {
        if(empty()){
            throw new NoSuchElementException();
        }
        start = array[0];
        return array[0];
    }

    @Override
    public Integer get_last() throws NoSuchElementException {
        if(empty()){
            throw new NoSuchElementException();
        }
        end = array[size()-1];
        return array[size()-1];
    }

    @Override
    public Integer remove_first() throws NoSuchElementException {
        if(empty()){
            throw new NoSuchElementException();
        }
        Integer first = array[0];
        Integer[] temp = new Integer[array.length];
        for(int i = 1; i<size(); i++){
            temp[i-1] = array[i];
        }
        array = temp;
        size--;
        return first;
    }

    @Override
    public Integer remove_last() throws NoSuchElementException {
        if(empty()){
            throw new NoSuchElementException();
        }
        Integer last = array[size() - 1];
        array[size()-1] = null;
        size--;
        return last;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void reverse() {
       for(int i = 0; i<size()/2; i++){
           Integer temp = array[i];
           array[i] = array[size() - i - 1];
           array[size() - i - 1] = temp;
       }
    }

    @Override
    public int compute_size_recursive() {
        return compute_size_recursive(0);
    }

    public int compute_size_recursive(int current){
        if(array[current] == null){
            return 1;
        }else{
            return 1 + compute_size_recursive(current++);
        }
    }

    @Override
    public ArrayList<Integer> to_ArrayList_post_recurse() {
        return null;
    }

    @Override
    public ArrayList<Integer> to_ArrayList() {
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0; i<size(); i++){
            temp.add(array[i]);
        }
        return temp;
    }

    @Override
    public boolean contains_recursive(Integer item) {
        return contains_recursive(item,0);
    }

    private boolean contains_recursive(Integer item, int current){
        if(array[current] == item){
            return true;
        }else if(current == size() - 1) {
            return false;
        }else{
            return contains_recursive(item,current+1);
        }
    }

    @Override
    public boolean contains(Integer item) {
        for(int i = 0; i<size(); i++){
            if(array[i] == item){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if array is empty
     * @return - true if empty, false if not
     */
    private boolean empty(){
        return size() == 0;
    }

    /**
     * Checks if backing array is full, if it is expand the array
     */
    private void check_if_full(){
        if(size() == array.length){
            expand();
        }
    }
}
