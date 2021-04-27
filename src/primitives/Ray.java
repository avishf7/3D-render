package primitives;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

/**
 * Class Ray is the basic class representing a Ray in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public class Ray {

	/**
	 * Ray constructor receiving {@link Point3D} and {@link Vector}.
	 * 
	 * @param p0  starting point
	 * @param dir Direction vector
	 */
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalized();
	}

	/**
	 * starting point
	 */
	private Point3D p0;
	/**
	 * Direction vector
	 */
	private Vector dir;

	/**
	 * The function moves the starting point of the ray in the direction of the ray
	 * vector at a distance of the given scalar
	 * 
	 * @param t scalar
	 * @return the point after movement
	 */
	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	/**
	 * The function finds the point closest to the ray head from a collection of points
	 * @param points collection of points
	 * @return Point3D The closest point to the head of the ray
	 */
	public Point3D findClosestPoint(List<Point3D> points) {

		if (points == null || points.size() == 0)
			return null;
		Point3D closest = points.get(0);
		double distance = p0.distance(closest);
		for (Point3D point : points) {
			double newDist = point.distance(p0);
			if (newDist < distance) {
				distance = newDist;
				closest = point;
			}
		}
		return closest;
	}
	
	public GeoPoint getClosestGeoPoint(List<GeoPoint> points) {
		if (points == null || points.size() == 0)
			return null;
		GeoPoint closest = points.get(0);
		double distance = p0.distance(closest.point);
		for (GeoPoint gpoint : points) {
			double newDist = gpoint.point.distance(p0);
			if (newDist < distance) {
				distance = newDist;
				closest = gpoint;
			}
		}
		return closest;
	
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);
	}

	/**
	 * Getter
	 * 
	 * @return starting point
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * Getter
	 * 
	 * @return Direction vector
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}

}
