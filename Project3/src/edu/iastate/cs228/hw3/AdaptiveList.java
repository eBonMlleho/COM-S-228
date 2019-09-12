package edu.iastate.cs228.hw3;
/**
 **  @author Zhanghao Wen
 *
 *  An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 *
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AdaptiveList<E> implements List<E> {
	public class ListNode // private member of outer class
	{
		public E data; // public members:
		public ListNode link; // used outside the inner class
		public ListNode prev; // used outside the inner class

		public ListNode(E item) {
			data = item;
			link = prev = null;
		}
	}

	public ListNode head; // dummy node made public for testing.
	public ListNode tail; // dummy node made public for testing.
	private int numItems; // number of data items
	private boolean linkedUTD; // true if the linked list is up-to-date.

	public E[] theArray; // the array for storing elements
	private boolean arrayUTD; // true if the array is up-to-date.

	public AdaptiveList() {
		clear();
	}

	@Override
	public void clear() {
		head = new ListNode(null);
		tail = new ListNode(null);
		head.link = tail;
		tail.prev = head;
		numItems = 0;
		linkedUTD = true;
		arrayUTD = false;
		theArray = null;
	}

	/**
	 * 
	 * @return linkedUTD
	 */
	public boolean getlinkedUTD() {
		return linkedUTD;
	}

	/**
	 * 
	 * @return arrayUTD
	 */
	public boolean getarrayUTD() {
		return arrayUTD;
	}

	/**
	 * initial adaptiveList with give collection c
	 * 
	 * @param c
	 */
	public AdaptiveList(Collection<? extends E> c) {

		// this();
		head = new ListNode(null);
		tail = new ListNode(null);
		head.link = tail;
		tail.prev = head;
		numItems = 0;
		linkedUTD = true;
		arrayUTD = false;

		for (E e : c) {
			add(e);

		}

	}

	/**
	 * add first
	 * 
	 * @param obj
	 */
	public void addFirst(E obj) {
		ListNode toAdd = new ListNode(obj);

		link(head, toAdd);

		numItems++;
	}

	/**
	 * // Removes the node from the linked list. // This method should be used to
	 * remove a node from the linked list.
	 * 
	 * @param toRemove
	 */
	private void unlink(ListNode toRemove) {
		if (toRemove == head || toRemove == tail)
			throw new RuntimeException("An attempt to remove head or tail");
		toRemove.prev.link = toRemove.link;
		toRemove.link.prev = toRemove.prev;
	}

	/**
	 * // Inserts new node toAdd right after old node current. // This method should
	 * be used to add a node to the linked list.
	 * 
	 * @param current
	 * @param toAdd
	 */
	private void link(ListNode current, ListNode toAdd) {
		if (current == tail)
			throw new RuntimeException("An attempt to link after tail");
		if (toAdd == head || toAdd == tail)
			throw new RuntimeException("An attempt to add head/tail as a new node");
		toAdd.link = current.link;
		toAdd.link.prev = toAdd;
		toAdd.prev = current;
		current.link = toAdd;
	}

	/**
	 * helper method update array copy elements from linked list
	 */
	private void updateArray() // makes theArray up-to-date.
	{
		if (numItems < 0)
			throw new RuntimeException("numItems is negative: " + numItems);
		if (!linkedUTD)
			throw new RuntimeException("linkedUTD is false");
		// TODO
		for (int i = 0; i < numItems; i++)// {if(E == instanceof toArray()[i]) }
			theArray[i] = (E) toArray()[i];

		arrayUTD = true;
		linkedUTD = false;
	}

	/**
	 * helper method update Linked Copy linked list from array
	 */
	private void updateLinked() // makes the linked list up-to-date.
	{
		if (numItems < 0)
			throw new RuntimeException("numItems is negative: " + numItems);
		if (!arrayUTD)
			throw new RuntimeException("arrayUTD is false");

		if (theArray == null || theArray.length < numItems)
			throw new RuntimeException("theArray is null or shorter");
		// TODO

		head.link = tail;
		tail.prev = head;

		addAll(Arrays.asList(theArray));

		linkedUTD = true;
		arrayUTD = false;
	}

	@Override
	public int size() {
		return numItems; // may need to be revised.
	}

	@Override
	public boolean isEmpty() {
		// TODO
		// if (numItems == 0)
		return true;
		// return false;
	}

	@Override
	public boolean add(E obj) {
		if (linkedUTD == false)
			updateLinked();

		if (head.link == tail) { // add first obj into list
			ListNode toAdd = new ListNode(obj);
			link(head, toAdd);
			numItems++;
			return true;
		} else {
			ListNode toAdd = new ListNode(obj);
			ListNode cur = head;
			while (cur.link != tail)
				cur = cur.link;
			link(cur, toAdd);

			numItems++;
		}
		return true; // may need to be revised.
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (linkedUTD == false)
			updateLinked();

		if (c.isEmpty())
			return false;

		E[] array = (E[]) c.toArray();

		for (int i = 0; i < c.toArray().length; i++) {
			add(array[i]);
			// System.out.println(array[i]);
		}
		return true; // may need to be revised.
	} // addAll 1

	@Override
	public boolean remove(Object obj) {

		if (head.link == tail)
			throw new NoSuchElementException("empty list");
		ListNode toRemove = head.link;
		for (int i = 0; i < size(); i++) {
			if (toRemove.data == obj) {
				unlink(toRemove);
				numItems--;
				return true;
			} else
				toRemove = toRemove.link;
		}

		return false; // may need to be revised.
	}

	private void checkIndex(int pos) // a helper method
	{
		if (pos >= numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkIndex2(int pos) // a helper method
	{
		if (pos > numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkNode(ListNode cur) // a helper method
	{
		if (cur == null || cur == tail)
			throw new RuntimeException("numItems: " + numItems + " is too large");
	}

	private ListNode findNode(int pos) // a helper method
	{
		ListNode cur = head;
		for (int i = 0; i < pos; i++) {
			checkNode(cur);
			cur = cur.link;
		}
		checkNode(cur);
		return cur;
	}

	@Override
	public void add(int pos, E obj) {

		ListNode toAdd = new ListNode(obj);
		checkIndex2(pos);

		if (pos == 0) {
			link(head, toAdd);
			numItems++;
		} else if (pos == size()) {
			add(obj);
		} else {
			ListNode cur = findNode(pos);
			link(cur, toAdd);
			numItems++;
		}

	}

	@Override
	public boolean addAll(int pos, Collection<? extends E> c) {

		if (c.isEmpty())
			return false;
		checkIndex2(pos);
		int i = pos;
		for (E e : c) {
			add(i++, e);
		}
		return true;
	} // addAll 2

	@Override
	public E remove(int pos) {

		checkIndex(pos);
		ListNode toRemove = findNode(pos + 1); // pos doesnt count head(if pos =1, then remove the element after head)
		unlink(toRemove);

		numItems--;
		return toRemove.data; // may need to be revised.
	}

	@Override
	public E get(int pos) {
		// TODO

		checkIndex(pos);
		ListNode cur = findNode(pos + 1);
		return cur.data; // may need to be revised.
	}

	@Override
	public E set(int pos, E obj) {
		// TODO
		if (!arrayUTD) {
			updateArray();
		}
		checkIndex(pos);
		ListNode cur = findNode(pos + 1);
		E tmp = cur.data;
		cur.data = obj;
		return tmp;
	}

	// If the number of elements is at most 1, the method returns false.
	// Otherwise, it reverses the order of the elements in the array
	// without using any additional array, and returns true.
	// Note that if the array is modified, then linkedUTD needs to be set to false.
	public boolean reverse() {
		// TODO
		if (!arrayUTD) {
			updateArray();
		}
		for (int i = 0; i < theArray.length / 2; i++) {
			E temp = theArray[i];
			theArray[i] = theArray[theArray.length - i - 1];
			theArray[theArray.length - i - 1] = temp;
		}
		return true; // may need to be revised.
	}

	@Override
	public boolean contains(Object obj) {
		boolean changed = false;
		ListNode cur = head.link;
		for (int i = 0; i < numItems; i++)
			if (cur.data == obj) {
				changed = true;
			} else {
				cur = cur.link;
			}

		return changed; // may need to be revised.
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO
		if (c == this)
			return true;
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	} // containsAll

	@Override
	public int indexOf(Object obj) {
		// TODO
		if (obj == null)
			return -1;

		ListNode cur;
		int pos = -1;
		for (cur = head; cur != tail; cur = cur.link, pos++)
			if (obj == cur.data || obj.equals(cur.data))
				return pos;
		return -1;

	}

	@Override
	public int lastIndexOf(Object obj) {
		// TODO
		ListIterator<E> iter = listIterator(numItems);
		while (iter.hasPrevious()) {
			E data = iter.previous();
			if (obj == data || obj != null && obj.equals(data))
				return iter.nextIndex();
		}
		return -1;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO
		if (c == null)
			throw new NullPointerException();
		boolean changed = false;

		// for (E e : c)
		// remove(e);

		ListIterator<E> iter = listIterator();
		while (iter.hasNext()) {
			E data = iter.next();
			if (c.contains(data)) {
				remove(data);
				changed = true;
			}
		}

		return changed; // may need to be revised.
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO
		if (c == null)
			throw new NullPointerException();
		boolean changed = false;
		ListIterator<E> iter = listIterator();
		while (iter.hasNext()) {
			E data = iter.next();
			if (!c.contains(data)) {
				iter.remove();
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public Object[] toArray() {
		// TODO
		Object[] arr = new Object[numItems];
		ListIterator<E> iter = listIterator();
		for (int i = 0; i < numItems; i++) {
			arr[i] = iter.next();
			// System.out.println(arr[i]);
		}
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] arr) {
		// TODO
		if (arr.length < numItems)
			arr = Arrays.copyOf(arr, numItems);
		System.arraycopy(toArray(), 0, arr, 0, numItems);
		if (arr.length > numItems)
			arr[numItems] = null;
		return arr;
	}

	@Override
	public List<E> subList(int fromPos, int toPos) {
		throw new UnsupportedOperationException();
	}

	private class AdaptiveListIterator implements ListIterator<E> {
		private int index; // index of next node;
		private ListNode cur; // node at index - 1
		private ListNode last; // node last visited by next() or previous()

		public AdaptiveListIterator() {
			if (!linkedUTD)
				updateLinked();
			// TODO
			cur = head;
			last = null;
			index = 0;

		}

		public AdaptiveListIterator(int pos) {
			if (!linkedUTD)
				updateLinked();
			// TODO

			checkIndex2(pos);
			index = pos;
			last = null;

			cur = pos == 0 ? head : findNode(pos);

		}

		@Override
		public boolean hasNext() {
			// TODO
			// if(cur == head)
			// if (cur == null)
			// return false;
			//
			return cur.link != null;
		}

		@Override
		public E next() {
			// TODO
			if (!hasNext()) {
				System.out.println("we");
				throw new NoSuchElementException();
			}
			if (index >= numItems)
				throw new RuntimeException("index 1");
			index++;
			last = cur = cur.link;
			return cur.data; // cur != null
		}

		@Override
		public boolean hasPrevious() {
			// TODO
			return cur != null; // may need to be revised.
		}

		////////////////// from lecture
		private ListNode lookPrev(ListNode vet) // covered in class
		{
			ListNode tmp;
			if (vet == head)
				return null;
			for (tmp = head; tmp.link != vet; tmp = tmp.link)
				if (tmp.link == null)
					throw new RuntimeException("vet not on list");
			return tmp;
		}

		@Override
		public E previous() {
			// TODO
			if (!hasPrevious())
				throw new NoSuchElementException();
			if (index <= 0)
				throw new RuntimeException("index 2");
			index--;
			last = cur;
			cur = lookPrev(cur);
			return last.data;
		}

		@Override
		public int nextIndex() {
			// TODO
			return index; // may need to be revised.
		}

		@Override
		public int previousIndex() {
			// TODO
			return index - 1; // may need to be revised.
		}

		public void remove() {
			// TODO
			if (last == null) // no previous call
				throw new IllegalStateException();
			if (cur == last) {
				if (index <= 0)
					throw new RuntimeException("index 3");
				index--;
			} // update index if cur is the last node

			if (last == head) {
				if (head == null)
					throw new NoSuchElementException("empty list");
				head = head.link;
				numItems--;
				cur = null;
			} else {
				numItems--;
				if (cur == last)
					cur = lookPrev(cur);
				cur.link = last.link;
			}
			last = null;
		}

		public void add(E obj) {
			// TODO
			if (cur == null) {
				addFirst(obj);
				last = null;
				cur = head;
				index = 1;
				return;
			}
			ListNode toAdd = new ListNode(obj);
			link(cur, toAdd);
			// last = cur;
			last = null;

			cur = toAdd;

			index++;
			numItems++;
		} // add

		@Override
		public void set(E obj) {
			// TODO
			if (last == null)
				throw new IllegalStateException();
			last.data = obj;
		} // set
	} // AdaptiveListIterator

	@Override
	public boolean equals(Object obj) {
		if (!linkedUTD)
			updateLinked();
		if ((obj == null) || !(obj instanceof List<?>))
			return false;
		List<?> list = (List<?>) obj;
		if (list.size() != numItems)
			return false;
		Iterator<?> iter = list.iterator();
		for (ListNode tmp = head.link; tmp != tail; tmp = tmp.link) {
			if (!iter.hasNext())
				return false;
			Object t = iter.next();
			if (!(t == tmp.data || t != null && t.equals(tmp.data)))
				return false;
		}
		if (iter.hasNext())
			return false;
		return true;
	} // equals

	@Override
	public Iterator<E> iterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int pos) {
		checkIndex2(pos);
		return new AdaptiveListIterator(pos);
	}

	// Adopted from the List<E> interface.
	@Override
	public int hashCode() {
		if (!linkedUTD)
			updateLinked();
		int hashCode = 1;
		for (E e : this)
			hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}

	// You should use the toString*() methods to see if your code works as expected.
	@Override
	public String toString() {
		String eol = System.getProperty("line.separator");
		return toStringArray() + eol + toStringLinked();
	}

	public String toStringArray() {
		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent array:" + eol);
		strb.append('[');
		if (theArray != null)
			for (int j = 0; j < theArray.length;) {
				if (theArray[j] != null)
					strb.append(theArray[j].toString());
				else
					strb.append("-");
				j++;
				if (j < theArray.length)
					strb.append(", ");
			}
		strb.append(']');
		return strb.toString();
	}

	public String toStringLinked() {
		return toStringLinked(null);
	}

	// iter can be null.
	public String toStringLinked(ListIterator<E> iter) {
		int cnt = 0;
		int loc = iter == null ? -1 : iter.nextIndex();

		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent linked list:" + eol);
		strb.append('(');
		for (ListNode cur = head.link; cur != tail;) {
			if (cur.data != null) {
				if (loc == cnt) {
					strb.append("| ");
					loc = -1;
				}
				strb.append(cur.data.toString());
				cnt++;

				if (loc == numItems && cnt == numItems) {
					strb.append(" |");
					loc = -1;
				}
			} else
				strb.append("-");

			cur = cur.link;
			if (cur != tail)
				strb.append(", ");
		}
		strb.append(')');
		return strb.toString();
	}
}
