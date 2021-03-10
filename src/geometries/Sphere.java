package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class Sphere is the basic class representing a sphere in the
 * Three-dimensional space. 
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
	 * getter
	 * 
	 * @return center
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * getter
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}

}
