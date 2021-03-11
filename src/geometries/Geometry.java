package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * interface Geometry is interface for geometric shape in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public interface Geometry {
	/**
	 * The function receives a {@link Point3D} 
	 * and calculates The normal (vertical) vector to the geometric shape at the received point.
	 * 
	 * @param point {@link Point3D} on the geometric body
	 * @return A {@link Vector} that represent The normal (vertical) vector to the geometric shape at the received point.
	 */
	Vector getNormal(Point3D point);

}
