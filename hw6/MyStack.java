import java.util.NoSuchElementException;

public class MyStack <E>{
	
	
	
	public MyLinkedList <E> MyStack=new MyLinkedList<>();
	
	
	public MyStack() {
		
	}
	
	
	public boolean isEmpty() {
		return MyStack.head==null;
	}
	
	public E pop() {
		
		
		
		if(MyStack.isEmpty()) {
			throw new NoSuchElementException("pop: MyStack is empty.");
		}
		
		else {
			MyLinkedListNode<E> tail = MyStack.tail;
			E item= tail.item;
			MyLinkedListNode <E> current = MyStack.head;
			
			
			while (current.next!=item) {
				current=current.next;
				MyStack.tail=current;
			}
			
			return item;
			
			
			}
		
		
		
	}
	
	public E peek() {
		
		E item=null;
		
		if(MyStack.isEmpty()) {
			throw new NoSuchElementException("peek: MyStack is empty.");
		}
		
		else {
			MyLinkedListNode<E> tail = MyStack.tail;
			item= tail.item;
			
			}
		
		return item;
	}
	
	public void push(E item) {
	
		MyStack.addToTail(item);
		
		
	}
	
	public String toString() {
		return MyStack.toString();
		
		
		
		
	}
	
	
	

}
