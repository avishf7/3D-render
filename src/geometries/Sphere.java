package geometries;

import static primitives.Util.alignZero;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Sphere is the basic class representing a sphere in the
 * Three-dimensional space.
 * 
 * @author Shai&Avishay
 */
public class Sphere implements Geometry {
	/**
	 * Sphere constructor receiving the center and the radius of the sphere.
	 * 
	 * @param center A point3D representing the center of the sphere
	 * @param radius The radius of the sphere
	 */
	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	/**
	 * point3D representing the center of the sphere
	 */
	private Point3D center;
	/**
	 * The radius of the sphere
	 */
	private double radius;

	/**
	 * Getter
	 * 
	 * @return center
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * Getter
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return point.subtract(center).normalize();
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		try {
			Vector u = center.subtract(ray.getP0());
			double tM = u.dotProduct(ray.getDir()), 
					d = Math.sqrt(alignZero(u.lengthSquared() - (tM * tM)));

			if (d >= radius)
				return null;

			double tH = Math.sqrt(alignZero(radius * radius - d * d)), t1 = tM + tH, t2 = tM - tH;

			if (t1 > 0 || t2 > 0) {
				LinkedList<Point3D> intsPoints = new LinkedList<Point3D>();
				if (alignZero(t1) > 0)
					intsPoints.add(ray.getPoint(t1));
				if (alignZero(t2) > 0)
					intsPoints.add(ray.getPoint(t2));
				return intsPoints;
			}

			return null;

		//In case the starting point of the ray is in the center of the sphere
		} catch (IllegalArgumentException e) {

			return new LinkedList<Point3D>(List.of(ray.getPoint(radius)));
		}

	}

}
