package geometries;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Plane is the basic class representing a Plane in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public class Plane extends Geometry {
	/**
	 * Cylinder constructor receiving three {@link Point3D}.
	 * 
	 * @param p1 A point in the plane
	 * @param p2 A point in the plane
	 * @param p3 A point in the plane
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		if (p1.equals(p2) || p2.equals(p3))
			throw new IllegalArgumentException("A plane must consist of 3 different points");

		Vector v1 = p2.subtract(p1).normalize(), v2 = p3.subtract(p1).normalize();

		if (v1.equals(v2) || v1.equals(v2.scale(-1)))
			throw new IllegalArgumentException("The dots are on the same line");

		this.q0 = p1;
		this.normal = v1.crossProduct(v2).normalize();
		buildBox();
	}

	/**
	 * Cylinder constructor receiving a {@link Point3D} and {@link Vector}
	 * 
	 * @param q0     A point in the plane
	 * @param normal The normal to the plane
	 */
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal;
		buildBox();
	}

	/**
	 * A point in the plane
	 */
	private Point3D q0;
	/**
	 * The normal to the plane
	 */
	private Vector normal;

	@Override
	public void buildBox() {
		double minX = Double.NEGATIVE_INFINITY, minY = minX, minZ = minX, //
				maxX = Double.POSITIVE_INFINITY, maxY = maxX, maxZ = maxX;

		Vector xAxis = new Vector(new Point3D(1, 0, 0));
		Vector yAxis = new Vector(new Point3D(0, 1, 0));
		Vector zAxis = new Vector(new Point3D(0, 0, 1));
		if (xAxis.equals(normal) || xAxis.scale(-1).equals(normal))
			minX = maxX = q0.getX();
		if (yAxis.equals(normal) || yAxis.scale(-1).equals(normal))
			minY = maxY = q0.getY();
		if (zAxis.equals(normal) || zAxis.scale(-1).equals(normal))
			minZ = maxZ = q0.getZ();
		this.box = new WrapBox(minX, minY, minZ, maxX, maxY, maxZ);

	}

	/**
	 * Getter
	 * 
	 * @return q0(a point in the plane)
	 */
	public Point3D getQ0() {
		return q0;
	}

	/**
	 * Getter
	 * 
	 * @return Normal plane
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return normal;
	}

	@Override
	public String toString() {
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		if (this.box != null && !this.box.isIntersect(ray))
			return null;

		try {
			double nQ0MinusP0 = normal.dotProduct(q0.subtract(ray.getP0()));
			double nv = normal.dotProduct(ray.getDir());

			if (isZero(nv))
				return null;

			double t = alignZero(nQ0MinusP0 / nv);
			if (t <= 0)
				return null;
			if (alignZero(t - maxDistance) > 0)
				return null;
			return new LinkedList<GeoPoint>(List.of(new GeoPoint(this, ray.getPoint(t))));

		} catch (IllegalArgumentException e) {
			return null;
		}

	}

}
