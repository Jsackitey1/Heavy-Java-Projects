import java.util.NoSuchElementException;

public class MyLinkedList <E>{
	MyLinkedListNode<E> head= null,tail=null;
	

	public E removeHead() {
		
		if(head==null) {
			throw new NoSuchElementException("MyLinkedLIst is empty");
		}
		
		E item = head.item;
		
		head=head.next;
		
		if(head==null) {
			tail=null;
		}
		
		return item;
		
	}
	
	public boolean isEmpty() {
		return head==null;
	}

	public void addToHead(E item) {
		// TODO Auto-generated method stub
		MyLinkedListNode<E> newNode=new MyLinkedListNode<E>(item,head);
		head=newNode;
		
		if(tail==null) {
			tail=newNode;
		}
	}

	public void addToTail(E item) {
		// TODO Auto-generated method stub
		MyLinkedListNode<E> newNode=new MyLinkedListNode<E>(item,null);
		if(tail==null) {
			head=newNode;
		}
		else {
			tail.next=newNode;
		}
		tail=newNode;	
		
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder("[");
		MyLinkedListNode<E> current=head;
		while(current!=null) {
			sb.append(current.item);
			if(current.next!=null) {
				sb.append(", ");
			}
			current= current.next;
		}
		sb.append("]");
		return sb.toString();
		
	}
	
	
	

}
