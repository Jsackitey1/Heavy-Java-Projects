
public class MyPoint2D {
	
	private double x;
	private double y;

	
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
		return String.format("(%g, %g)",this.x, this.y);
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
