/**
 * 
 */
package elements;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Interface LightSource is an interface that represents a light source on scene
 * 
 * @author Shai&Avishay
 *
 */
public interface LightSource {
	/**
	 * The function receives a point and calculates the effect of light at that
	 * point
	 * 
	 * @param p the point
	 * @return the color of the light on the point that received
	 */
	public Color getIntensity(Point3D p);

	/**
	 * the function receives a point and calculates the vector from the light source
	 * to the point
	 * 
	 * @param p the point
	 * @return the vector from the light source to the point
	 */
	public Vector getL(Point3D p);

	/**
	 * 
	 * the function receives a point and calculates the distance from the light
	 * source to the point
	 * 
	 * @param point the point
	 * @return the distance from the light source to the point
	 */
	double getDistance(Point3D point);

}
