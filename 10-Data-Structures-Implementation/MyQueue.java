import java.util.NoSuchElementException;

public class MyQueue<E> {

	public MyLinkedList<E> queue = new MyLinkedList<>();

	public MyQueue() {

	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public E dequeue() {
		if (queue.isEmpty()) {
			throw new NoSuchElementException("dequeue: MyQueue is empty");
		}

		else {
			return queue.removeHead();
		}
	}

	public E peek() {
		if (queue.isEmpty()) {
			throw new NoSuchElementException("peek: MyQueue is empty");
		}

		else {
			E item = queue.removeHead();
			queue.addToHead(item);

			return item;
		}

	}

	public void enqueue(E item) {
		queue.addToTail(item);

	}

	public String toString() {
		return queue.toString();
	}

}
