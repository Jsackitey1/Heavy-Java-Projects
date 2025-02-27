import java.util.ArrayList;
import java.util.Comparator;

public class MahFravrit<E> {
	private Comparator<E> fravriter;
	private ArrayList<E> items = new ArrayList<>();

	public MahFravrit(Comparator<E> fravriter) {

		this.fravriter = fravriter;

	}

	public boolean isEmpty() {
		return items.size() == 0;
	}

	public void add(E item) {
		items.add(item);

	}

	public boolean remove(E item) {
		
		if (items.contains(item)) {
			items.remove(items.indexOf(item));
			return true;
		}

		else {
			return false;
		}

	}

	public E getFravrit() {
		if (items.isEmpty()) {
			return null;
		}


		E fav = items.get(0);

	
		for (int i = 1; i < items.size(); i++) {
			E current = items.get(i);
			if (fravriter.compare(current, fav) > 0) {
				fav = current;
			}
		}

		items.remove(fav);
		return fav;
	}
	
	public static void main(String[] args) { 
		// In this first example, we use random integers and prefer larger integers as fravrits (favorites).
		int size = 20;
		
		MahFravrit<Integer> intFravrit = new MahFravrit<Integer>(new Comparator<Integer>() 
		{
			public int compare(Integer v1, Integer v2) {
				return v1 - v2;
			}
		});
		// Add random integers, possibly with repetition.
		for (int i = 0; i < size; i++) {
			int item = (int) (size * Math.random());
			System.out.print(item + " ");
			intFravrit.add(item);
		}
		System.out.println();
		// Successively, remove integers, greatest to least as we've defined our preference with the Comparator above.
		for (int i = 0; i < size; i++) 
			System.out.print(intFravrit.getFravrit() + " ");
		System.out.println();

		// In this next example, we use Strings with book names and prefer later Strings lexicographically, 
		// except for "Gersberms", which are MOST preferred because "ERMAHGERD! GERSBERMS ... MAH FRAVRIT BERKS!" 
		// http://knowyourmeme.com/memes/ermahgerd
		MahFravrit<String> berkFravrit = new MahFravrit<String>(new Comparator<String>() 
		{
			public int compare(String v1, String v2) {
				if (v1.equals("Gersberms"))
					return 1;
				else if (v2.equals("Gersberms"))
					return -1;
				else
					return v1.compareTo(v2);
			}
		});
		berkFravrit.add("The Hobbit");
		berkFravrit.add("Gersberms");
		berkFravrit.add("Diary of a Wimpy Kid");
		System.out.println(berkFravrit.getFravrit());
		System.out.println(berkFravrit.getFravrit());
		System.out.println(berkFravrit.getFravrit());
	}


}
