import java.util.NoSuchElementException;

public class MyQueue <E>{
	
	public MyLinkedList<E> MyQueue=new MyLinkedList<>();
	
	public MyQueue() {
		
	}
	 
	public boolean isEmpty() {
		return MyQueue.isEmpty();
	}
	
	public E dequeue() {
		if(MyQueue.isEmpty()) {
			throw new NoSuchElementException("dequeue: MyQueue is empty");
		}
		
		else {
			MyLinkedListNode<E> mhead=MyQueue.head;
			MyLinkedListNode<E> currenthead=mhead.next;
			MyQueue.head= currenthead;
			return mhead.item;
		}
	}
	
	public E  peek() {
		if(MyQueue.isEmpty()) {
			throw new NoSuchElementException("peek: MyQueue is empty");
		}
		
		else {
			return MyQueue.head.item;
		}
		
	}
	
	public void enqueue(E item) {
		MyQueue.addToTail(item);
		 
	}
	
	public String toString() {
		return MyQueue.toString();
	}
	
	

}
