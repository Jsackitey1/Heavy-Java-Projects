import java.util.ArrayList;
import java.util.Comparator;

public class MahFravrit <E>{
	private  Comparator <E> fravriter;
	private ArrayList<E> items=new ArrayList<>();
	
	public MahFravrit(Comparator <E> fravriter) {
		
		this.fravriter=fravriter;
		
	}
	
	public boolean isEmpty() {
		return items.size()==0 ;
	}
	
	public void add(E item) {
		items.add(item);
		
	}
	
	
	public boolean remove(E item) {
		if(items.contains(item)) {
		items.remove(items.indexOf(item));
		return true;
		}
		
		else {
			return false;
		}
		
	}
	
	public E getFravrit() {
		
		E fav = null;
		int j;
		
		for(int i=0;i<items.size()-1;i++) {
			j = fravriter.compare(items.get(i), items.get(i++));
			if(j>0) {
				fav=items.get(i);
				
			}
			
			if(j<0) {
				fav=items.get(i++);
			}
			
			if(j==0) {
				fav=items.get(i);
			}
				
			
		}
		return fav;
		
		
	}
	
	
	
}
