
import java.util.LinkedList;
public class DLLQueue<E> implements Queue<E> {
	DoublyLinked<E> queue;
	public DLLQueue() {
		queue = new DoublyLinked<E>();	
	}
	public String toString() {
		return queue.toString();
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		queue.addLast(e);
		// TODO Auto-generated method stub
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return queue.get(0);
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return queue.removeFirst();
	}
	

}
