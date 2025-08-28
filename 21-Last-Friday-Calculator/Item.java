public class Item {
	public String name;
	public String description;
	public boolean isUsable;
	public boolean isCollected;
	public boolean isUsed;

	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		this.isUsable = false;
		this.isCollected = false;
		this.isUsed = false;
	}

	public String toString() {
		return name;
	}

	public String examine() {
		return description;
	}

	public String use() {
		if (!isUsable) {
			return "You can't use the " + name + " right now.";
		}
		if (isUsed) {
			return "You've already used the " + name + ".";
		}
		isUsed = true;
		return "You use the " + name + ".";
	}

	public void collect() {
		isCollected = true;
	}

	public void makeUsable() {
		isUsable = true;
	}
}
