import java.util.NoSuchElementException;

public class DoublyLinked<T> {
	private Node header;
	private Node trailer;
	private int size;

	public DoublyLinked() {
		this.header = new Node(null, trailer, null);
		this.trailer = new Node(null, null, header);
		this.header.next = trailer;
		this.size = 0;
	}

	// return the size of the list, not including sentinel nodes
	public int size() {
		return this.size;
	}

	// return true of list is empty, return false otherwise
	public boolean isEmpty() {
		return size == 0;
	}

	// add a new node with given value right after the sentinel header node
	public void addFirst(T value) {
		addBetween(value, header, header.next);
	}

	// add a new node with given value right before the sentinel trailer node
	public void addLast(T value) {
		addBetween(value, trailer.prev, trailer);
	}
	public T get(int index) {
		Node current = header.next;
		for(int i =0; i<index; i++) {
			current = current.next;
		}
		return current.getValue();
	}

	// conveniently can be used by both addFirst and addLast to simplify code
	private void addBetween(T value, Node first, Node second) {
		// first check:
		if (first == null || second == null) {
			throw new IllegalArgumentException("DLL: null nodes passed to addBetween method");
		}
		if (first.next != second || second.prev != first) {
			throw new IllegalArgumentException("DLL: cannot place node between two nonconsecutive nodes");
		}
		Node newNode = new Node(value, second, first);
		first.next = newNode;
		second.prev = newNode;
		this.size++;
	}
	public int search(T value) {
		Node current = header.next;
		for(int i = 0; i< size; i++) {
			if(current.getValue() == value) {
				return i;
			}
			else {
				current = current.next;
			}
		}
		return -1;
	}
	// return String representing contents of list
	// elements are separated by spaces
	public String toString() {
		String result = "";
		Node current = header.next;
		while (current != trailer) {
			result += current + " ";
			current = current.next;
		}
		return result;
	}
	public T removeFirst() {
		return removeBetween(header, header.next.next);	
		
	}
	public T removeLast() {
		return removeBetween(trailer.prev.prev, trailer);	
	}
	private T removeBetween(Node node1, Node node2)
	{
		//check if either is null
		if (node1 ==  null || node2 == null)
		{
			throw new IllegalArgumentException("Must have valid parameters");
		}
		//Check for an empty list
		if (header.next ==  trailer)
		{
			throw new NoSuchElementException("Cannot delete from an empty list");
		}
		//check that given nodes are 1 apart
		if(node1.next.next != node2)
		{
			throw new IllegalArgumentException("The nodes must have a single node betwen them");
		}
		T valueToReturn = null;
		//TODO: Your code goes here
		valueToReturn = node1.next.getValue();
		node1.next = node2;
		node2.prev = node1;
		size--;
		return valueToReturn;
		
	}



	// inner class for storing nodes
	public class Node {
		private T value;
		private Node next;
		private Node prev;

		public Node(T v, Node n, Node p) {
			this.value = v;
			this.next = n;
			this.prev = p;
		}

		public T getValue() {
			return value;
		}
		public String toString()
		{
			return value.toString();
		}

	}

}