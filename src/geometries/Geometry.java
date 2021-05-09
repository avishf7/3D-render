package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * class Geometry is class for geometric shape in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public abstract class Geometry implements Intersectable {

	protected Color emmission = Color.BLACK;
	private Material material = new Material();


	/**
	 * Getter
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Builder pattern Setter
	 * @param material the material to set
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * Getter
	 * @return the emmission
	 */
	public Color getEmmission() {
		return emmission;
	}

	/**
	 * Builder pattern Setter
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
