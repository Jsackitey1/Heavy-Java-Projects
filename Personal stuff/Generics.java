import java.util.ArrayList;

public class Generics<E> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ArrayList<String> list=new ArrayList<>();
		String[] list= {"january","february", "March"};
		
		//System.out.println(list.toString());
		
		Generics.print(list);
		
		
		

	}
	
	public static <E> void print(E[] list){
		for(int i=0;i<list.length;i++) {
			System.out.print(list[i] +" ");
		}
		System.out.println();
		
		
	}
	
	public static <E extends Comparable<E>> void sort(E[] list) {
		
	}

}
