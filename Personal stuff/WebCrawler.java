
import java.util.ArrayList;
import java.util.Scanner;

public class WebCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input =new Scanner(System.in);
		System.out.println("Enter a url: ");
		String url=input.nextLine();
		
		crawler(url);
		
	}

	private static void crawler(String startingURL) {
		// TODO Auto-generated method stub
		ArrayList<String> listOfPendingURLs=new ArrayList<>();
		ArrayList<String> listOfVisitedURLs=new ArrayList<>();
		
		listOfPendingURLs.add(startingURL);
		
		while(!listOfPendingURLs.isEmpty()&&listOfVisitedURLs.size()<=100) {
			String url =listOfPendingURLs.remove(0);
			if(!listOfVisitedURLs.contains(url)) {
				listOfVisitedURLs.add(url);
				System.out.println("crawl " + url);
				
				for(String s: getSubURLs(url)) {
					if(!listOfVisitedURLs.contains(s)) {
						listOfPendingURLs.add(s);
					}
				}
				
			}
			
		}
		
		
	}

	private static ArrayList<String> getSubURLs(String urlString) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<>();
		try {
			
			java.net.URL url= new java.net.URL(urlString);
			
			Scanner input=new Scanner(url.openStream());
			int current=0;
			
			while(input.hasNext()) {
				String line=input.nextLine();
				
				current= line.indexOf("https:",current);
				
				while(current>0) {
					int endIndex= line.indexOf("\"",current);
					
					if(endIndex>0) {
						list.add(line.substring(current,endIndex));
						current=line.indexOf("https:",endIndex);
						
					}
					else {
						current=-1;
						
					}
				}
				
			}
			
			
		}
		catch(Exception e) {
			System.out.println("Error: "+ e.getMessage());
			
		}
		return list;
	}

}
