import java.util.ArrayList;

public abstract class SearchNode implements Cloneable{
	
	public SearchNode parent=null;//the node that led to this node
	
	public int depth=0; //number of operations to get to this node
	
	public SearchNode() {
		
	}
	
	public abstract boolean isGoal();//Is this a goal node?
	
	//what are the children of this node?
	
	public abstract ArrayList<SearchNode> expand();
	
	public Object clone() {
		//make a shallow copy of this object
		try {
			return super.clone();
		}
		catch(CloneNotSupportedException e) {
			throw new RuntimeException("This class does not implement cloneable.");
		}
	}
	
	
	public SearchNode childClone() {
		SearchNode child=(SearchNode)clone();
		child.parent=this;
		child.depth=depth+1;
		return child;
	}
	
	
	

}
