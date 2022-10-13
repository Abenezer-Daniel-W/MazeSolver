
import java.util.LinkedList;

public class DLLstack<T> implements Stack<T> {

	DoublyLinked<T> stack;

	public DLLstack() {
		stack = new DoublyLinked<T>();
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void push(T v) {
		stack.addFirst(v);

	}

	@Override
	public T pop() {
		return stack.removeFirst();
	}

	@Override
	public T top() {
		return stack.get(0);
	}
}
