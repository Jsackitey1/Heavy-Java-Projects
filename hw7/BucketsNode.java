import java.util.ArrayList;

public class BucketsNode extends SearchNode {
	public static final int MAX_AMOUNT1=5,MAX_AMOUNT2=3,GOAL_AMOUNT=4;
	public int bucket1=0;
	public int bucket2=0;
	
	

	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return bucket1+bucket2==GOAL_AMOUNT;
	}

	@Override
	public ArrayList<SearchNode> expand() {
		// TODO Auto-generated method stub
		ArrayList<SearchNode> children= new ArrayList<>();
		
		//empty 1
		if(bucket1>0) {
			BucketsNode child=(BucketsNode) childClone();
			child.bucket1=0;
			children.add(child);
			
		}
		
		//empty 2
		if(bucket2>0) {
			BucketsNode child=(BucketsNode) childClone();
			child.bucket2=0;
			children.add(child);
			
		}
		//fill 1
		if(bucket1<MAX_AMOUNT1) {
			BucketsNode child=(BucketsNode) childClone();
			child.bucket1=MAX_AMOUNT1;
			children.add(child);
			
		}
		//fill 2
		if(bucket2<MAX_AMOUNT2) {
			BucketsNode child=(BucketsNode) childClone();
			child.bucket2=MAX_AMOUNT2;
			children.add(child);
			
		}
		//pour 1 to 2
		
		if(bucket1>0&&bucket2<MAX_AMOUNT2) {
			BucketsNode child=(BucketsNode) childClone();
			int pourAmount=Math.min(bucket1, MAX_AMOUNT2-bucket2);
			
			child.bucket1-=pourAmount;
			child.bucket2+=pourAmount;
			children.add(child);
			
		}
		
		//pour 2 to 1
		
		if(bucket2>0&&bucket1 <MAX_AMOUNT1) {
			BucketsNode child=(BucketsNode) childClone();
			int pourAmount=Math.min(bucket2, MAX_AMOUNT1-bucket1);
			
			child.bucket2-=pourAmount;
			child.bucket1+=pourAmount;
			children.add(child);
			
		}
		return children;
	}
	
	public String toString() {
		return String.format("bucket 1: %d, bucket 2: %d", bucket1,bucket2);
	}
	
	

}
