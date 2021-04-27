package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface Geometry is interface for geometric shape in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public abstract class Geometry implements Intersectable {

	protected Color emmission = Color.BLACK;

	/**
	 * @return the emmission
	 */
	public Color getEmmission() {
		return emmission;
	}

	/**
	 * @param emmission the emmission to set
	 */
	public Geometry setEmmission(Color emmission) {
		this.emmission = emmission;
		return this;
	}

	/**
	 * The function receives a {@link Point3D} and calculates The normal (vertical)
	 * vector to the geometric shape at the received point.
	 * 
	 * @param point {@link Point3D} on the geometric body
	 * @return A {@link Vector} that represent The normal (vertical) vector to the
	 *         geometric shape at the received point.
	 */
	public abstract Vector getNormal(Point3D point);

}
