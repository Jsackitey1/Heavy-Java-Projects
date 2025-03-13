import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ReadFileFromURL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a url: ");
		
		String URLString= new Scanner(System.in).next();
		try {
			URL url =new URL(URLString);
			int count=0;
			Scanner input= new Scanner(url.openStream());
			while(input.hasNext()) {
				String line=input.nextLine();
				count+=line.length();
				
			}
			System.out.println("The file size is "+ count+" characters");
			
			
		}
		
		catch(MalformedURLException e) {
			System.out.println("invalid url.");
			
		}
		catch(IOException e) {
			System.out.println("i/o errors: no such file exist.");
		}

	}

}
