package lab10;

import lab10.Lab10.Array_List_2420;
import lab10.Lab10.Linked_List_2420;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class ExampleTester {
	
	@Test
	public void simpleLinkedListTest() {
		Linked_List_2420<Integer> linkedList = new Linked_List_2420<>();
		linkedList.add_first(1);
		
		Iterator<Integer> iterator = linkedList.iterator();
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("Wrong number!", 1, (int)iterator.next());
		
		iterator.remove();
		Assert.assertEquals("Size should be zero after removing element", 0, linkedList.size());
	}
	
	@Test
	public void simpleArrayListTest() {
		Array_List_2420 arrayList = new Array_List_2420();
		for(int i = 0; i<100; i++) {
			arrayList.add_first(1);
		}
		Iterator<Integer> iterator = arrayList.iterator();
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("Wrong number!", 1, (int)iterator.next());

		for(int i = 0; i<100; i++) {
			iterator.remove();
		}
		Assert.assertEquals("Size should be zero after removing element", 0, arrayList.size());
	}
	
}
