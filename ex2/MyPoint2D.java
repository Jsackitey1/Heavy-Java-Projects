
public class MyPoint2D {
	static double x;
	static double y;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MyPoint2D(double x, double y) {
		this.x=x;
		this.y=y;
		
	}
	
	public double getDistance(double x,double y) {
		
		return Math.sqrt(Math.pow(this.x-x,2) + Math.pow(this.y-y,2));
	}
	
	public double getDistance(MyPoint2D other) {
		
		return Math.sqrt(Math.pow(this.x-other.getX(),2) + Math.pow(this.y-other.getY(),2));
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s)",MyPoint2D.getX(), MyPoint2D.getY());
	}

	public static double getX() {
		return x;
	}

	public static void setX(int x) {
		MyPoint2D.x = x;
	}

	public static double getY() {
		return y;
	}

	public static void setY(int y) {
		MyPoint2D.y = y;
	}

}
