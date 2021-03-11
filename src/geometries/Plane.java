package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class Plane is the basic class representing a Plane in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public class Plane implements Geometry {
	/**
	 * Cylinder constructor receiving three {@link Point3D}.
	 * @param p1 A point in the plane
	 * @param p2 A point in the plane
	 * @param p3 A point in the plane
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this.q0 = p1;
		this.normal = null;
	}

	/**
	 * Cylinder constructor receiving a {@link Point3D} and {@link Vector}
	 * @param q0 A point in the plane
	 * @param normal The normal to the plane
	 */
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal;
	}

	/**
	 * A point in the plane
	 */
	private Point3D q0;
	/**
	 * The normal to the plane
	 */
	private Vector normal;

	/**
	 * Getter
	 * @return q0(a point in the plane)
	 */
	public Point3D getQ0() {
		return q0;
	}

	/**
	 * Getter
	 * @return Normal plane
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}
	
	@Override
	public String toString() {
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}

}
