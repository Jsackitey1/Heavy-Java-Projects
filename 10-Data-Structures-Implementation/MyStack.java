import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class MyStack<E> {
	private MyLinkedList<E> stack = new MyLinkedList<>();

	public MyStack() {
	}

	public boolean isEmpty() {
		return stack.isEmpty(); 
	}

	public E pop() {
		if (stack.isEmpty()) {
			throw new NoSuchElementException("pop: MyStack is empty.");
		}

		
		MyLinkedList<E> tempList = new MyLinkedList<>();
		E lastItem = null;

		// Move all items except the last one to tempList
		while (!stack.isEmpty()) {
			E current = stack.removeHead();
			if (!stack.isEmpty()) {
				tempList.addToTail(current);
			} else {
				lastItem = current; 
			}
		}

		
		while (!tempList.isEmpty()) {
			stack.addToTail(tempList.removeHead());
		}

		return lastItem;
	}

	public E peek() {
		if (stack.isEmpty()) {
			throw new NoSuchElementException("peek: MyStack is empty.");
		}

	
		MyLinkedList<E> tempList = new MyLinkedList<>();
		E lastItem = null;

		
		while (!stack.isEmpty()) {
			E current = stack.removeHead();
			tempList.addToTail(current);
			if (stack.isEmpty()) {
				lastItem = current;
			}
		}		
		while (!tempList.isEmpty()) {
			stack.addToTail(tempList.removeHead());
		}

		return lastItem;
	}

	public void push(E item) {
		stack.addToTail(item);
	}

	//must fix
	@Override
	public String toString() {
	    String s= stack.toString();
	   
	    List<String> list = Arrays.asList(s.substring(1, s.length() - 1).split(", "));
        
        Collections.reverse(list);
        return "[" + String.join(", ", list) + "]";
	}

}
