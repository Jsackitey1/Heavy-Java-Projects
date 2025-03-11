import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearcher extends Searcher{

	@Override
	public boolean search(SearchNode node) {
		//initialize search variables
		goalNode=null;
		nodeCount=0;
		
		Stack<SearchNode> stack= new Stack<>();
		stack.push(node);
		
		
		while(!stack.isEmpty()) {
			//if the queue is empty, return false
			if(stack.isEmpty()) {
				return false;
			}
			
			//Get the node from the front of the queue and count it in our node count
			node=stack.pop();
			nodeCount+=1;
			
			//if it is a goal node, store it and return true(success)
			if(node.isGoal()) {
				goalNode=node;
				return true;
			}
			
			//otherwise put its children into the back of the queue.
			
			for(SearchNode child : node.expand()) {
				stack.push(child);
			}
		}
		return false;
	}
	
	
	public DepthFirstSearcher() {
		
	}
	
	
	

}
