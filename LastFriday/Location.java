import java.util.ArrayList;
import java.util.HashMap;

public class Location {
	public String name;
	public String description;
	public boolean isVisited = false;
	public boolean isExplored = false;
	public boolean isMoved = false;

	public HashMap<String, Location> exits = new HashMap<>();
	public ArrayList<Item> items = new ArrayList<>();
	public ArrayList<Item> hiddenItems = new ArrayList<>();

	public Location(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public void addHiddenItem(Item item) {
		hiddenItems.add(item);
	}

	public void revealHiddenItem(String itemName) {
		for (int i = 0; i < hiddenItems.size(); i++) {
			if (hiddenItems.get(i).name.equalsIgnoreCase(itemName)) {
				items.add(hiddenItems.remove(i));
				return;
			}
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(name);

		if (!isVisited) {
			sb.append("\n" + description);
			isVisited = true;
		}
		if (!exits.isEmpty()) {
			sb.append("\nPossible exits: ");
			for (String direction : exits.keySet()) {
				sb.append(direction + " ");
			}

		}

		if (!items.isEmpty()) {

			sb.append("\nYou see: ");
			for (Item item : items) {
				sb.append(item + " ");
			}
		}

		return sb.toString();

	}

	public String examine() {
		StringBuilder sb = new StringBuilder();
		sb.append("You examine the " + name + " carefully.\n");

		if (!isExplored) {
			isExplored = true;
			switch (name.toLowerCase()) {
				case "bed":
					sb.append("The bed looks normal. You might be able to lift it to look underneath.");
					break;
				case "desk & drawer":
					sb.append(
							"The drawer is locked with a 3-digit combination lock. The right combo is somewhere in the room — perhaps hidden in plain sight(243).");
					break;
				case "bookshelf":
					sb.append(
							"The bookshelf is filled with books. One titled 'Hidden Spaces' catches your attention. There's also a book titled 'Room of Riddles'.");
					break;
				case "closet":
					sb.append("Inside the closet, you notice a jacket hanging.");
					break;
				case "painting":
					sb.append("The painting seems loose. You can move it aside.");
					break;
				case "vent":
					sb.append("The vent is sealed with screws.");
					break;
				case "carpet":
					sb.append("The carpet seems to have a slight bulge in one corner.");
					break;
			}
		} else {
			sb.append("You've already examined this area thoroughly.");
		}

		return sb.toString();
	}

	public String liftBed() {
		if (name.toLowerCase().equals("bed")) {
			revealHiddenItem("coat hanger");
			return "You lift the bed and find a coat hanger, bent out of shape. It might be good for lock-picking or reaching tight spaces.";
		}
		return "There's nothing to lift here.";
	}

	public String pullOutBooks() {
		if (name.toLowerCase().equals("bookshelf")) {
			return "You pull out the book titled 'Hidden Spaces' and hear a click. A low clunk sound reveals the shelf can slide. Behind it is a narrow opening, but it's too dark to enter safely.";
		}
		return "There are no books to pull out here.";
	}

	public String movePainting() {
		if (name.toLowerCase().equals("painting") && !isMoved) {
			isMoved = true;
			revealHiddenItem("screwdriver");
			return "You move the painting aside and find a mini-safe with no lock, just screws. Inside is a journal entry from a parent revealing they once hid in a crawlspace behind the bookshelf as a child.";
		}
		return "There's nothing to move here.";
	}

	public String searchJacket() {
		if (name.toLowerCase().equals("closet")) {
			revealHiddenItem("batteries");
			return "You search inside the jacket and find AA batteries.";
		}
		return "There's nothing to search here.";
	}

	public String liftCarpet() {
		if (name.toLowerCase().equals("carpet")) {
			return "You lift the carpet and find a trapdoor. It's sealed tight. You try using the coat hanger and screwdriver, but no luck. It's rusted shut and jammed.";
		}
		return "There's nothing to lift here.";
	}

}
