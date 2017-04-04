package lab10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lab10 {
	public static class Linked_List_2420<T extends Comparable<? super T>> implements  Iterable<T> {
		/* Fill out these methods for the Programming Challange*/
		@Override
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				Node<T> current = dummy;
				Node<T> prev = null;
				@Override
				public boolean hasNext() {
					if(current.next != null){
						return true;
					}
					return false;
				}

				@Override
				public T next() {
					if(hasNext()){
						prev = current;
						current = current.next;
						return current.data;
					}
					return null;
				}
				
				@Override
				public void remove() { // Should run in O(c)
					current = current.next;
					if(prev != null){
						prev.next = current;
					}
				}
			};
		}
		/** This dummy represents the start of the list, but it doesn't CONTAIN the start of the list. 
		 * 
		 * In an empty list, the dummy node is NOT null, but its next pointer is.
		 * [Dummy] -> null 
		 * 
		 * In a list of size one or more, the dummy node is not null, and dummy.next points to the first node in the list.
		 * [Dummy] -> [1] -> null
		 * 
		 * There is no tail reference.
		 */
		Node<T> dummy;
		
		
		public Linked_List_2420() {
			dummy = new Node<T>(null, null);
		}
		
		public void add_first(T data) {
			Node<T> added = new Node<T>(data, null);
			added.next = dummy.next;
			dummy.next = added;
		}

		public void add_last(T data) {
			Node<T> cursor = dummy;
			while(cursor.next != null) {
				cursor = cursor.next;
			}
			Node<T> added = new Node<>(data, null);
			cursor.next = added;
		}

		public void add_middle(int after, T data) {
			Node<T> cursor = dummy;
			int count = after;
			while(cursor.next != null && count >= 0) {
				cursor = cursor.next;
				count--;
			}
			
			Node<T> added = new Node<>(data, null);
			added.next = cursor.next;
			cursor.next = added;
		}

		public void clear() {
			dummy = new Node<>(null, null);
		}

		public boolean contains(T item) {
			Node<T> cursor = dummy.next;
			while(cursor != null) {
				if(item == null) {
					if(cursor.data == item) {
						return true;
					}
				} else if(item.equals(cursor.data)) {
					return true;
				}
				cursor = cursor.next;
			}
			return false;
		}

		public T get_first() throws NoSuchElementException {
			if(dummy.next == null) {
				throw new NoSuchElementException();
			}
			return dummy.next.data;
		}

		public T get_last() throws NoSuchElementException {
			if(dummy.next == null) {
				throw new NoSuchElementException();
			}
			
			Node<T> cursor = dummy;
			while(cursor.next != null) {
				cursor = cursor.next;
			}
			return cursor.data;
		}

		public T remove_first() throws NoSuchElementException {
			if(dummy.next == null) {
				throw new NoSuchElementException();
			}
			T data = dummy.next.data;
			remove_node_via_parent(dummy);
			return data;
		}

		public T remove_last() throws NoSuchElementException {
			if(dummy.next == null) {
				throw new NoSuchElementException();
			}
			
			Node<T> cursor = dummy;
			while(cursor.next.next != null) {
				cursor = cursor.next;
			}
			T temp = cursor.next.data;
			cursor.next = null;
			return temp;
		}
		
		public void remove_node_via_parent(Node<T> parent) {
			parent.next = parent.next.next;
		}

		public int size() {
			return dummy.next == null ? 0  : dummy.next.length();
		}
		
		public ArrayList<T> to_ArrayList() {
			ArrayList<T> list = new ArrayList<>();
			Node<T> cursor = dummy.next;
			while(cursor != null) {
				list.add(cursor.data);
				cursor = cursor.next;
			}
			return list;
		}
		
		  public String toString() {
			  if(dummy.next == null) {
				  return "empty";
			  }
			  return dummy.next.toString();
		  }
		
		protected class Node<N> {
			N data;
			Node<N> next;

			Node(N the_data, Node<N> after_me) {
				data = the_data;
				next = after_me;
			}

			int length() {
				return next == null ? 1 : 1 + next.length();
			}
			
			public String toString() {
				StringBuilder sb = new StringBuilder();
				toString(sb, this);
				return sb.toString();
			}
			
			public void toString(StringBuilder sb, Node<N> node) {
				if(node != null) {
					sb.append("[" + node.data + "]--> ");
					toString(sb, node.next);
				} else {
					sb.append("null");
				}
			}
		}
	}
	
	/************************************************************************************
	 * **********************          ARRAY LIST         ****************************
	 ************************************************************************************/
	public static class Array_List_2420 implements Iterable<Integer> {
		/** FILL OUT THIS METHOD FOR THE PROGRAMMING CHALLENGE**/
		@Override
		public Iterator<Integer> iterator() {
			return new Iterator<Integer>() {
				int current = start;
				@Override
				public boolean hasNext() {
					current++;
					if(start > end) {
						if (current >= start) {
							if(current > data.length){
								if(end-1 < 0){
									current = start;
								}else {
									current = end - 1;
								}
							}
							return true;
						}
					}else{
						if(current > data.length){
							current = start;
						}else{
							current++;
						}
						return true;
					}
					return false;
				}

				@Override
				public Integer next() {
					if(hasNext()){
						return data[current];
					}
					return null;
				}
				
				//This is more difficult. Test and verify other methods before starting on this one.
				@Override
				public void remove() {
					data[current] = 0;
					size--;
				}
			};
		}
		
		/**
		 * The backing store for the data in the list. All data is held between start and end, with the possibility that the data 'wraps' back to the front.
		 * NULLS DON'T MEAN ANYTHING. If the size of list is decremented, the old values are not replaced with nulls.
		 */
		int[] data = new int[1024];
		
		/**
		 * Will point to the index of where the first item in the list is;
		 */
		int start = 0;
		
		/**
		 * Points to the index AFTER the last element.
		 * This means the actually last element exists at end -1
		 */
		int end = 0;
		
		/**
		 * Keeps track of the number of elements in the list.
		 */
		int size = 0;
		
		public void add_first(Integer data) {
			checkSize();
			start = wrapIdx(start -1);
			this.data[start] = data;
			size++;
		}

		public void add_last(Integer data) {
			this.data[end] = data;
			end = wrapIdx(end +1);
			size++;
		}

		public void add_middle(int after, Integer data) {
			int insertionIdx = convertOffsetToIndex(after+1);
			if(after >= size) {
				after = size;
				insertionIdx = convertOffsetToIndex(after);
			}
			addAt(insertionIdx, data);
		}
		
		private void checkSize() {
			if(size == data.length) {
				growArray();
			}
		}
		
		private void addAt(int insertionIdx, int value) {
			checkSize();
			for(int idx = wrapIdx(end-1); idx != insertionIdx; idx = wrapIdx(--idx)) {
				data[wrapIdx(idx+1)] = data[idx];
			}
			data[insertionIdx] = value;
			end = wrapIdx(end +1);
			size++;
		}
		
		private void growArray() {
			int[] newData = new int[data.length<<1];
			for(int idx = 0; idx<data.length; idx++) {
				newData[idx] = data[wrapIdx(idx+start)];
			}
			start = 0;
			end = data.length;
			data = newData;
		}

		private int wrapIdx(int idx) {
			if(idx < 0) {
				return idx + data.length;
			} else {
				return idx % data.length;
			}
		}
		
		private int convertOffsetToIndex(int offset) {
			return wrapIdx(start + offset);
		}

		public void clear() {
			start = end = 0;
			size = 1;
		}

		public boolean contains(Integer item) {
			for(int offset = 0; offset < size; offset++) {
				if(data[convertOffsetToIndex(offset)] == item) {
					return true;
				}
			}
			return false;
		}

		public Integer get_first() throws NoSuchElementException {
			elementCheck();
			return data[start];
		}

		public Integer get_last() throws NoSuchElementException {
			elementCheck();
			return data[wrapIdx(end-1)];
		}

		public Integer remove_first() throws NoSuchElementException {
			elementCheck();
			int removedValue = data[start];
			start = wrapIdx(start + 1);
			size--;
			return removedValue;
		}

		public Integer remove_last() throws NoSuchElementException {
			elementCheck();
			end = wrapIdx(end - 1);
			size--;
			return data[end];
		}
		
		private void elementCheck() {
			if(size == 0) {
				throw new NoSuchElementException();
			}
		}

		public int size() {
			return size;
		}

		public ArrayList<Integer> to_ArrayList() {
			ArrayList<Integer> list = new ArrayList<>();
			for(int offset = 0; offset < size; offset ++) {
				list.add(data[convertOffsetToIndex(offset)]);
			}
			return list;
		}
	}
}
