import java.util.NoSuchElementException;

public class MyLinkedListTest {

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		java.util.Random random = new java.util.Random();
		int trials = 20;
		for (int i = 0; i < trials; i++) {
			int op = random.nextInt(3);
			if (op == 0) { // remove head
				try {
					int item = list.removeHead();
					System.out.println("removeHead() --> " + item);
				}
				catch (NoSuchElementException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (op == 1) { // add to head
				int item = random.nextInt(trials);
				list.addToHead(item);
				System.out.printf("addToHead(%d)\n", item);
			}
			else { // add to tail
				int item = random.nextInt(trials);
				list.addToTail(item);
				System.out.printf("addToTail(%d)\n", item);
			}
			System.out.println(list);
		}
	}


}