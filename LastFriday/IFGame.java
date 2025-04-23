import java.util.Scanner;

public class IFGame {
	
	public Scanner in =new Scanner (System.in);
	
	public boolean echoOn=false;
	public Location location;
	
	

	public static void main(String[] args) {
		new IFGame(args.length >0 &&  args[0].equals("-e"));
		
		
	}


	public IFGame(boolean echoOn) {
		
		this.echoOn = echoOn;
		initialize();
		play();
	}


	private void initialize() {
		Location servo=new Location("Servo", "Most popular dinning center on campus");
		Location cub= new Location("CUB", "The sun shines  on the newly renovated dinning center on Gettysburg college campus.");
		Location stine = new Location("Stine Hall", "Home of 115 first year students.\nNamed for former college trustee Charles Stine.");
		
		servo.exits.put("s", cub);
		servo.exits.put("se", stine);
		cub.exits.put("n", servo);
		cub.exits.put("e", stine);
		stine.exits.put("nw", servo);
		stine.exits.put("w", cub);
		location =servo;
		Item sq=new Item("Squirrel", "You are a very hungry pet of Gettysburg");
		servo.items.add(sq);
		
		Item acorn =new Item("acorn","It's just an ...acorn");
		
		stine.items.add(acorn);
		
		
	}


	private void play() {
		while(true) {
			System.out.println(location);
			System.out.println("? ");
			String command= in.nextLine();
			if(echoOn) {
				System.out.println(command);
			}
			if(command.equals("quit")) {
				System.exit(0);
			}
			else if(command.equals("look")){
				location.isVisited=false;
				
			}
			
			else {
				// move is our default for now, but an error 
				//for an unrecognized command should be your default
				//eventual default
				
				Location nextLocation= location.exits.get(command);
				if (nextLocation==null) {
					System.out.println("You can't go that way.");
				}
				
				else {
					location=nextLocation;
				}
			}
		}
		
		
	}

}
