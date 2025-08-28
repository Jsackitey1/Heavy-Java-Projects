import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearcher extends Searcher {

	@Override
	public boolean search(SearchNode node) {
		//initialize search variables
		goalNode=null;
		nodeCount=0;
		
		Queue<SearchNode> queue=new LinkedList<>();
		queue.offer(node);
		
		
		while(true) {
			//if the queue is empty, return false
			if(queue.isEmpty()) {
				return false;
			}
			
			//Get the node from the front of the queue and count it in our node count
			node=queue.poll();
			nodeCount+=1;
			
			//if it is a goal node, store it and return true(success)
			if(node.isGoal()) {
				goalNode=node;
				return true;
			}
			
			//otherwise put its children into the back of the queue.
			
			for(SearchNode child : node.expand()) {
				queue.offer(child);
			}
		}
	}

}
