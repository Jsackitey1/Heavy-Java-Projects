import java.util.Scanner;
import java.util.ArrayList;

public class IFGame {

	public Scanner in = new Scanner(System.in);

	public boolean echoOn = false;
	public Location location;
	Location inventory = new Location("inventory", "Your inventory");
	public boolean gameWon = false;
	public boolean drawerUnlocked = false;
	public boolean bookshelfMoved = false;
	public boolean ventOpened = false;

	public static void main(String[] args) {
		new IFGame(args.length > 0 && args[0].equals("-e"));

	}

	public IFGame(boolean echoOn) {

		this.echoOn = echoOn;
		initialize();
		play();
	}

	private void initialize() {
		// Create locations
		Location bed = new Location("Bed", "Your bed, where you woke up. The sheets are rumpled.");
		Location desk = new Location("Desk & Drawer",
				"A wooden desk with a locked drawer.\nThe right combo is somewhere in the room, perhaps hidden in plain sight");
		Location bookshelf = new Location("Bookshelf", "A tall bookshelf filled with books.");
		Location carpet = new Location("Carpet", "A thick carpet covers the floor.");
		Location closet = new Location("Closet", "A walk-in closet with clothes hanging.");
		Location painting = new Location("Painting", "A large painting hangs on the wall.");
		Location vent = new Location("Vent", "A ventilation grate in the wall.");

		// Bed connections
		bed.exits.put("s", desk);
		bed.exits.put("se", bookshelf); 
		bed.exits.put("sw", closet); 

		// Desk connections
		desk.exits.put("n", bed); 
		desk.exits.put("e", bookshelf); 
		desk.exits.put("s", carpet); 

		// Bookshelf connections
		bookshelf.exits.put("nw", bed); 
		bookshelf.exits.put("w", desk); 
		bookshelf.exits.put("s", painting); 

		// Carpet connections
		carpet.exits.put("n", desk); 
		carpet.exits.put("e", bookshelf); 

		// Closet connections
		closet.exits.put("ne", bed); 
		closet.exits.put("e", desk); 

		// Painting connections
		painting.exits.put("n", bookshelf); 
		painting.exits.put("w", vent); 

		// Vent connections
		vent.exits.put("e", painting); 

		
		// Bed items
		Item coatHanger = new Item("coat hanger",
				"A bent metal coat hanger. Might be useful for lock-picking or reaching tight spaces.");
		coatHanger.makeUsable();
		bed.addHiddenItem(coatHanger);

		// Desk items
		Item journal = new Item("journal", "A journal with notes about the room's history.");
		Item flashlight = new Item("flashlight", "A flashlight without batteries.");
		desk.addHiddenItem(journal);
		desk.addHiddenItem(flashlight);

		// Bookshelf items
		Item roomOfRiddles = new Item("Room of Riddles", "A book with the code '243' underlined.");
		Item hiddenSpaces = new Item("Hidden Spaces", "A book that seems to be loose in the shelf.");
		bookshelf.items.add(roomOfRiddles);
		bookshelf.items.add(hiddenSpaces);

		// Closet items
		Item jacket = new Item("jacket", "A jacket hanging in the closet.");
		Item batteries = new Item("batteries", "AA batteries that might fit the flashlight.");
		closet.items.add(jacket);
		closet.addHiddenItem(batteries);

		// Painting items
		Item miniSafe = new Item("mini-safe", "A small safe with screws instead of a lock.");
		Item journalEntry = new Item("journal entry", "A note about a hidden crawlspace behind the bookshelf.");
		Item screwdriver = new Item("screwdriver", "A small screwdriver for removing screws.");
		screwdriver.makeUsable();
		painting.addHiddenItem(miniSafe);
		painting.addHiddenItem(journalEntry);
		painting.addHiddenItem(screwdriver);

		// Vent items
		Item ventGrate = new Item("vent grate", "A metal grate sealed with screws.");
		vent.items.add(ventGrate);

		// Carpet items
		Item trapdoor = new Item("trapdoor", "A sealed trapdoor under the carpet.");
		carpet.addHiddenItem(trapdoor);

		location = bed;
	}

	private void play() {
		System.out.println("Escape the Bedroom - No Door, No Window");
		System.out.println("\nYou awaken in a random bedroom on the second floor a hotel.");
		System.out.println("Something feels off — the door is locked from the outside.");
		System.out.println("The window overlooks a steep backyard ravine, making any attempt to jump suicidal.");
		System.out.println("No one answers your calls for help.");
		System.out.println("\nYou begin to explore. There has to be a way out.");

		while (!gameWon) {
			System.out.println("\n" + location);
			System.out.print("> ");
			String command = in.nextLine().toLowerCase().trim();

			if (echoOn) {
				System.out.println(command);
			}

			// quit command
			if (command.equals("quit")) {
				System.exit(0);
			}
			else if (command.equals("restart")) {
				initialize();
				continue;
			}
			else if (command.equals("inventory") || command.equals("i")) {
				showInventory();
			}
			else if (command.equals("look")) {
				location.isVisited = false;
			}
			else if (command.equals("examine") || command.equals("x")) {
				System.out.println(location.examine());
			}
			else if (command.startsWith("examine ") || command.startsWith("x ")) {
				String object = command.substring(command.indexOf(" ") + 1);
				examineObject(object);
			}
			else if (command.startsWith("take ") || command.startsWith("get ")) {
				String itemName = command.substring(command.indexOf(" ") + 1);
				takeItem(itemName);
			}
			else if (command.startsWith("drop ")) {
				String itemName = command.substring(command.indexOf(" ") + 1);
				dropItem(itemName);
			}
			else if (command.equals("unlock drawer")) {
				unlockDrawer();
			} else if (command.equals("lift bed")) {
				System.out.println(location.liftBed());
			} else if (command.equals("pull out books")) {
				System.out.println(location.pullOutBooks());
				if (location.name.equals("Bookshelf")) {
					bookshelfMoved = true;
				}
			} else if (command.equals("move bookshelf")) {
				if (location.name.equals("Bookshelf") && bookshelfMoved) {
					System.out.println(
							"You slide the bookshelf aside, revealing a narrow opening. It's too dark to enter safely.");
					boolean hasFlashlight = false;
					for (Item item : inventory.items) {
						if (item.name.equals("flashlight")) {
							hasFlashlight = true;
							break;
						}
					}
					if (hasFlashlight) {
						System.out.println(
								"You use your flashlight to light the path. Inside is a vent opening... but it's sealed with screws.");
					}
				} else {
					System.out.println("There's nothing to move here.");
				}
			} else if (command.equals("move painting")) {
				System.out.println(location.movePainting());
			} else if (command.equals("search jacket")) {
				System.out.println(location.searchJacket());
			} else if (command.equals("lift carpet")) {
				System.out.println(location.liftCarpet());
			} else if (command.equals("open vent")) {
				openVent();
			} else if (command.equals("escape")) {
				tryEscape();
			} else if (command.startsWith("use ")) {
				String itemName = command.substring(4);
				useItem(itemName);
			} else if (command.startsWith("use ") && command.contains(" with ")) {
				// use with command
				String[] parts = command.split(" with ");
				String item1 = parts[0].substring(4).trim();
				String item2 = parts[1].trim();
				useItemWith(item1, item2);
			}
			else {
				

				if (command.startsWith("go ")) {
					command = command.substring(3);
				}


				switch (command) {
					case "n":
					case "north":
						command = "n";
						break;
					case "s":
					case "south":
						command = "s";
						break;
					case "e":
					case "east":
						command = "e";
						break;
					case "w":
					case "west":
						command = "w";
						break;
					case "u":
					case "up":
						command = "u";
						break;
					case "d":
					case "down":
						command = "d";
						break;
				}

				Location nextLocation = location.exits.get(command);
				if (nextLocation == null) {
					System.out.println("You can't go that way.");
				} else {
					location = nextLocation;
				}
			}
		}
	}

	private void examineObject(String object) {
		// Check if object is in inventory
		for (Item item : inventory.items) {
			if (item.name.equalsIgnoreCase(object)) {
				System.out.println(item.examine());
				return;
			}
		}
		for (Item item : location.items) {
			if (item.name.equalsIgnoreCase(object)) {
				System.out.println(item.examine());
				return;
			}
		}
		System.out.println("You don't see that here.");
	}

	private void dropItem(String itemName) {
		for (Item item : inventory.items) {
			if (item.name.equalsIgnoreCase(itemName)) {
				inventory.items.remove(item);
				location.items.add(item);
				System.out.println("You drop the " + itemName + ".");
				return;
			}
		}
		System.out.println("You don't have that item.");
	}

	private void useItemWith(String item1, String item2) {
		Item firstItem = null;
		Item secondItem = null;

		for (Item item : inventory.items) {
			if (item.name.equalsIgnoreCase(item1)) {
				firstItem = item;
			}
			if (item.name.equalsIgnoreCase(item2)) {
				secondItem = item;
			}
		}

		if (firstItem == null) {
			System.out.println("You don't have the " + item1 + ".");
			return;
		}
		if (secondItem == null) {
			System.out.println("You don't have the " + item2 + ".");
			return;
		}

		if (firstItem.name.equals("batteries") && secondItem.name.equals("flashlight")) {
			secondItem.makeUsable();
			System.out.println("You insert the batteries into the flashlight. It works now!");
			inventory.items.remove(firstItem);
		} else if (firstItem.name.equals("screwdriver") && secondItem.name.equals("vent grate")) {
			if (location.name.equals("Vent") && !ventOpened) {
				ventOpened = true;
				System.out.println(
						"You use the screwdriver to remove the vent screws. The vent opens, revealing a vertical shaft!");
			}
		} else {
			System.out.println("You can't use those items together.");
		}
	}

	private void unlockDrawer() {
		if (location.name.equals("Desk & Drawer") && !drawerUnlocked) {
			// Check if player has coat hanger
			boolean hasCoatHanger = false;
			for (Item item : inventory.items) {
				if (item.name.equals("coat hanger")) {
					hasCoatHanger = true;
					break;
				}
			}

			if (!hasCoatHanger) {
				System.out.println("The drawer is locked tight. You need something to help pry it open slightly.");
				return;
			}

			System.out.println("You use the coat hanger to pry the drawer slightly open.");
			System.out.println("Enter the 3-digit combination: ");
			String combo = in.nextLine().trim();

			if (combo.equals("243")) {
				drawerUnlocked = true;
				System.out.println(
						"The drawer clicks open! Inside you find a journal with notes and a flashlight without batteries.");
				location.revealHiddenItem("journal");
				location.revealHiddenItem("flashlight");
			} else {
				System.out.println("The combination doesn't work.");
			}
		} else {
			System.out.println("There's nothing to unlock here.");
		}
	}

	private void openVent() {
		if (location.name.equals("Vent") && !ventOpened) {
			boolean hasScrewdriver = false;
			for (Item item : inventory.items) {
				if (item.name.equals("screwdriver")) {
					hasScrewdriver = true;
					break;
				}
			}
			if (hasScrewdriver) {
				ventOpened = true;
				System.out.println(
						"You use the screwdriver to remove the vent screws. The vent opens, revealing a vertical shaft!");
			} else {
				System.out.println("You need something to remove the screws.");
			}
		} else {
			System.out.println("There's nothing to open here.");
		}
	}

	private void tryEscape() {
		if (location.name.equals("Vent") && ventOpened) {
			System.out.println(
					"You crawl into the vent and slide down the shaft. You tumble into the pantry and stumble upright.");
			System.out.println(
					"You're free. You hear no voices. The house is eerily quiet. Was it a prank? A mistake? You'll figure that out later.");
			System.out.println("For now, you've won your freedom — the clever way.");
			gameWon = true;
		} else {
			System.out.println("You can't escape from here.");
		}
	}

	private void takeItem(String itemName) {
		for (Item item : location.items) {
			if (item.name.equalsIgnoreCase(itemName)) {
				inventory.items.add(item);
				location.items.remove(item);
				item.collect();
				System.out.println("You take the " + itemName + ".");
				return;
			}
		}
		System.out.println("You don't see that here.");
	}

	private void useItem(String itemName) {
		for (Item item : inventory.items) {
			if (item.name.equalsIgnoreCase(itemName)) {
				if (itemName.equals("batteries")) {
					// Check if player has flashlight
					boolean hasFlashlight = false;
					for (Item invItem : inventory.items) {
						if (invItem.name.equals("flashlight")) {
							hasFlashlight = true;
							invItem.makeUsable();
							System.out.println("You insert the batteries into the flashlight. It works now!");
							inventory.items.remove(item); // Remove batteries after use
							return;
						}
					}
					if (!hasFlashlight) {
						System.out.println("You need a flashlight to use these batteries.");
						return;
					}
				} else if (itemName.equals("screwdriver")) {
					if (location.name.equals("Vent") && !ventOpened) {
						ventOpened = true;
						System.out.println(
								"You use the screwdriver to remove the vent screws. The vent opens, revealing a vertical shaft that leads to an old dumbwaiter-like chute used for laundry.");
						return;
					} else if (location.name.equals("Carpet")) {
						System.out.println(
								"You try to use the screwdriver on the trapdoor, but it's rusted shut and jammed.");
						return;
					}
				}
				System.out.println(item.use());
				return;
			}
		}
		System.out.println("You don't have that item.");
	}

	private void showInventory() {
		if (inventory.items.isEmpty()) {
			System.out.println("Your inventory is empty.");
		} else {
			System.out.println("Inventory:");
			for (Item item : inventory.items) {
				System.out.println("- " + item);
			}
		}
	}
}
