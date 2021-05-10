
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class DirectionalLight is a class that represents the DirectionalLight on a
 * scene
 * 
 * @author Shai&Avishay
 *
 */
public class DirectionalLight extends Light implements LightSource {

	/**
	 * direction of the light
	 */
	private Vector direction;

	/**
	 * CTOR
	 * 
	 * @param intensity color of the light
	 * @param dir       direction of the light
	 */
	public DirectionalLight(Color intensity, Vector dir) {
		super(intensity);
		direction = dir.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
		return intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return direction;
	}

	@Override
	public double getDistance(Point3D point) {
		return Double.POSITIVE_INFINITY;
	}

}
