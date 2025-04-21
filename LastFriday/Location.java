import java.util.HashMap;

public class Location {
	public String name ;
	public String description;
	
	public HashMap<String,Location>exits= new HashMap<>();
	
	public Location(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
	public String toString() {
		StringBuilder sb= new StringBuilder();
		
		sb.append(name);
		
		sb.append("/n"+ description);
		
		if(!exits.isEmpty()) {
			sb.append("/nPossible exits: ");
			for (String direction : exits.keySet()) {
				sb.append(direction+ " "); 
			}
			
		}
		
		return sb.toString();
		
		
	}

}
