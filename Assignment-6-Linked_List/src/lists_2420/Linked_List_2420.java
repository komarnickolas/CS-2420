package lists_2420;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/17/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Linked_List_2420<Type> implements List_2420<Type> {
    Node<Type> head, tail, current;
    int size;

    /**
     * Creates a new Linked_List with a size of 0
     */
    public Linked_List_2420(){
        tail = new Node<Type>(null,null);
        head = new Node<Type>(null,tail);
        current = head;
        size = 0;
    }

    @Override
    public void add_first(Type data) {
        if(size() == 0){
            tail.data = data;
        }else if(size() == 1){
            head.data = data;
        }else{
            Node<Type> temp = new Node<Type>(data, head);
            head = temp;
        }
        current = head;
        size++;
    }

    @Override
    public void add_last(Type data) {
        if(size() == 0){
            head.data = data;
        }else if(size() == 1){
            tail.data = data;
        }else{
            Node<Type> temp = new Node<Type>(data,null);
            tail.next = temp;
            tail = tail.next;
        }
        size++;
    }

    @Override
    public void add_middle(int after, Type data) {
        current = head;
        if(after >= size){
            add_last(data);
        }else{
            for(int i = 0; i<after; i++){
                current = current.next;
            }
            Node<Type> temp = new Node<>(data,current.next);
            current.next = temp;
        }
        size++;
    }

    @Override
    public void clear() {
        head.data = null;
        head.next = null;
        current = head;
    }

    @Override
    public boolean contains(Type item) {
        current = head;
        while(current != null){
            if(current.data == item){
                return true;
            }else{
                current = current.next;
            }
        }
        current = head;
        return false;
    }

    @Override
    public boolean contains_recursive(Type item) {
        if(current.data != item){
            if(current.next == null){
                return false;
            }else {
                current = current.next;
                return contains_recursive(item);
            }
        }else{
            return true;
        }
    }

    @Override
    public Type get_first() throws NoSuchElementException {
        if(head.data == null){
            throw new NoSuchElementException();
        }
        current = head;
        return head.data;
    }

    @Override
    public Type get_last() throws NoSuchElementException {
        if(size() == 0){
            throw new NoSuchElementException();
        }
        current = tail;
        return tail.data;
    }

    @Override
    public Type remove_first() throws NoSuchElementException {
        Type first = head.data;
        head = head.next;
        size--;
        return first;
    }

    @Override
    public Type remove_last() throws NoSuchElementException {
        Type last = tail.data;
        while(current.next.next != null){
            current = current.next;
        }
        tail = current;
        tail.next = null;
        size--;
        return last;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    @Override
    public int compute_size_recursive() {
        return head.length();
    }

    @Override
    public ArrayList<Type> to_ArrayList_post_recurse() {
        return head.to_ArrayList_post_recursive();
    }

    @Override
    public ArrayList<Type> to_ArrayList() {
        ArrayList<Type> temp = new ArrayList<>();
        while(current.next != null){
            temp.add(current.data);
            current = current.next;
        }
        return temp;
    }
    /**
     *
     *
     * Creates a string that describes the contents of the list, starting with the size in
     * parentheses for example, a list of the nubmers 0, 1, 2, 3 would print as:
     *
     * "(4) [0]--> [1]--> [2]--> [3]--> null"
     *
     * an empty list should simply return the string "empty"
     *
     * WARNING: the syntax must be exact.  "open parenthesis, size, close parenthesis, space,
     *          open square bracket, data, close square bracket, hyphen, hyphen, greater than space,
     *          ... null"
     *
     * @return a string representation of this chain of nodes
     */
    public String toString(){
        if(size() == 0){
            return "empty";
        }else {
            String array = "(" + size() +") ["+ head.data+"]--> ";
            Node<Type> current = head;
            while(current.next != null){
                current = current.next;
                array += "[" + current.data + "]--> ";
            }
            array += "null";
            return array;
        }
    }

    /**
     *
     * Pictorially, a node is:
     *
     *  data  next
     *  ----------
     *  | 5  |---+--->
     *  ----------
     *
     *  Note, while a 5 is used above any "Type" could be contained in the node
     */
    private static class Node<Type>{
        Type data;
        Node<Type> next;

        /**
         * Creates a new node in a linked list
         * @param the_data - data in the node
         * @param after_me - node that comes after this one
         */
        public Node(Type the_data, Node<Type> after_me){
            this.data = the_data;
            this.next = after_me;
        }

        /**
         *
         * This function must be written recursively.
         *
         * @return the length of this "chain of nodes", including self.
         *
         *  Note: 1) it doesn't matter if something (or multiple somethings) points to this node,
         *        2) if this node doesn't point at anything, then the size would be 1.
         */
        public int length(){
            if(next != null) {
                return 1 + next.length();
            }else{
                return 1;
            }
        }

        /**
         * recursive determine if the item is in this node or the nodes after
         *
         * @param item
         *            - needle
         * @return true if item in chain
         */
        public boolean contains_recursive( Type item ){
            if(this.data == item){
                return true;
            }else if(next == null){
                return false;
            }else{
                return next.contains_recursive(item);
            }
        }


        /**
         * This function must be written recursively (using a helper method, with the
         * arraylist as a parameter, to do the recursion)
         *
         * Create an array list containing the data from this node and all nodes after it.
         *
         * In the helper method, add the data to the array list after the recursive call,
         * thus "reversing" the list.
         *
         */
        public ArrayList<Type> to_ArrayList_post_recursive(){
            ArrayList<Type> temp = new ArrayList<>();
            temp.add(data);
            if(next != null) {
                return next.toArrayList(temp);
            }else{
                return temp;
            }
        }

        private ArrayList<Type> toArrayList(ArrayList<Type> list){
            list.add(data);
            return list;
        }

        /**
         *
         *
         * Creates a string that describes the current node and all following nodes, for
         * example, a list of the nubmers 0, 1, 2, 3 would print as:
         *
         * "[0]--> [1]--> [2]--> [3]--> null"
         *
         * WARNING: the syntax must be exact.
         *          "open square bracket, data, close square bracket, hyphen, hyphen, greater than space,
         *          ... null"
         *
         * @return a string representation of this chain of nodes
         */
        public String toString(){
            if(next != null) {
                return "[" + data + "]--> " + next.toString();
            }else{
                return "["+data+"]--> null";
            }
        }
    }
}
