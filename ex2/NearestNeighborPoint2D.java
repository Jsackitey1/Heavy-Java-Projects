import java.util.ArrayList;

public class NearestNeighborPoint2D {

	private ArrayList<MyPoint2D> points; 

	public NearestNeighborPoint2D() {
		points = new ArrayList<>();
	}

	public void add(MyPoint2D point) {
		if (point != null) {
			points.add(point);
		}
	}

	public MyPoint2D getNearestNeighbor(MyPoint2D point) {
		if (points.isEmpty() || point == null) {
			return null;
		}
		

		MyPoint2D nearestDist = points.get(0);
		double minDistance = point.getDistance(nearestDist);

		for (MyPoint2D p : points) {
			double distance = point.getDistance(p);
			if (distance < minDistance) {
				minDistance = distance;
				nearestDist = p;
			}
		}

		return nearestDist;
	}

	public MyPoint2D getNearestNeighbor(double x, double y) {
		if (points.isEmpty()) {
			return null;
		}

		MyPoint2D tempPoint = new MyPoint2D(x, y);
		
		return getNearestNeighbor(tempPoint);
	}

	

	

}
