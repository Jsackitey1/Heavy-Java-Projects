import java.util.Arrays;
import java.util.Scanner;

public class PerfectShuffler {

	private int[] data;
	
	public PerfectShuffler() {
		
		this.data= new int [52];
		
		for (int i=0;i<52;i++) {
			this.data[i]=i;
			
		}
	}
	
	public int getIndexAt(int i) {
		return this.data[i];	
	}
	
	public void inShuffle() {
		
		int[] inshuffled = new int[52];
	    int mid = data.length / 2; 
	    int track = 0;
	    
	    for (int i = 0, j = mid; i < mid && j < data.length; i++, j++) {
	        inshuffled[track] = data[j];
	        track++;
	        inshuffled[track] = data[i]; 
	        track++;
	    }
	    
	    this.data= inshuffled;
		
	}
	
	public void outShuffle() {
		
		int[] outshuffled = new int[data.length];
	    int mid = data.length / 2; 
	    int track = 0;
	    
	    for (int i = 0, j = mid; i < mid && j < data.length; i++, j++) {
	        outshuffled[track] = data[i];
	        track++;
	        outshuffled[track] = data[j]; 
	        track++;
	    }
	    
	    this.data=outshuffled;
		
	}
	
	public java.lang.String toString(){
		String newdata=Arrays.toString(data);
		return newdata.substring(1,newdata.length()-1);
	}
	
	
	public static void main(String[] args) {

        PerfectShuffler shuffler = new PerfectShuffler();

        System.out.println(shuffler);

        Scanner input = new Scanner(System.in);

       // System.out.print("Enter \"i\" for inShuffle and \"o\" for outShuffle: ");

       
        while (input.hasNextLine()) {

            String command = input.nextLine().trim();

            if (command.equals("i")) {

                shuffler.inShuffle();

                System.out.println(shuffler);

            } else if (command.equals("o")) {

                shuffler.outShuffle();

                System.out.println(shuffler);

            } else if (command.isEmpty()) {

                break;

           
        }        
        }
        input.close();
    }

	

}
