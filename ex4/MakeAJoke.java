import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MakeAJoke {
	public String data="";
	
	
	public static void main(java.lang.String[] args) {
		MakeAJoke tryjokes=new MakeAJoke();
		tryjokes.loadJokes("jokes.txt");
		System.out.println(tryjokes.getJoke());
		
		
		
		
		
	}
	
	public MakeAJoke() {
		
	}
	
	public boolean loadJokes(java.lang.String filename) {
		
		try {
			 File file = new File(filename);
		      if (file.exists()) {
		      Scanner input = new Scanner(file);
		      while (input.hasNextLine()) {
		        data += input.nextLine();     
		      } 
		      
		      input.close();
		      
		      
		      return true;
		      }
		      
		    } catch (FileNotFoundException e) 
			{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		return false;
	}
	
	public java.lang.String getJoke(){
		
		String[] mydata= data.split("joke:");
		return mydata[(int)(Math.random()*mydata.length)-1];
	}
	
	public java.lang.String getJoke(int index){
		String[] mydata= data.split("joke:");
		return mydata[index];
	}
	
	
	public void addJoke(java.lang.String joke) {
		this.data += "joke:\n"+ joke;
	}
	
	public int size() {
		return data.split("jokes:").length;
	}
	
	public boolean saveJokes(java.lang.String filename) throws FileNotFoundException{
		try(PrintWriter output=new PrintWriter(filename)) {
			
			for (String joke :data.split("joke:")) {
				output.println("joke:");
				output.print(joke);
			}
			return true;
		}
	}
	
	

}
