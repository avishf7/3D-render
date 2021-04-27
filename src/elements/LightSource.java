/**
 * 
 */
package elements;

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
	 * 
	 * @param p
	 * @return
	 */
	public Color getIntensity(Point3D p);
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Vector getL(Point3D p);

}
