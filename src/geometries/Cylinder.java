package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder is the basic class representing a Cylinder in the
 * Three-dimensional space.
 * 
 * @author Shai&Avishay
 */
public class Cylinder extends Tube {

	/**
	 * Cylinder constructor receiving a ray, radius and height of the cylinder.
	 * 
	 * @param axisRay A {@link Ray} that representing the The cylinder axis
	 * @param radius  The cylinder radius
	 * @param height  The cylinder height
	 */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}

	/**
	 * The cylinder height
	 */
	private double height;

	/**
	 * Getter
	 * @return cylinder height
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "Cylinder [height=" + height + "]";
	}

	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

}