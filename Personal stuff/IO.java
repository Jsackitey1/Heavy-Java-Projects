import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class IO {
	
	public static void main(String[] args)throws IOException {
		File myfile= new File("scores.txt");		
		//PrintWriter output=new PrintWriter(myfile);
		Scanner input =new Scanner(myfile);
		while (input.hasNext()) {
			String firstname=input.next();
			String lastname=input.next();
			int score=input.nextInt();
			System.out.println(firstname + " " +lastname+ " "+score);
			
		}
		input.close();
		
		
		
		//woutput.close();
		
		
	
		
	}

}
