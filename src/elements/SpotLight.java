/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * Class SpotLight is a class that represents the SpotLight on a scene
 * 
 * @author Shai&Avishay
 *
 */
public class SpotLight extends PointLight {

	/**
	 * direction of the light
	 */
	private Vector direction;

	/**
	 * CTOR
	 * @param intensity color of the light
	 * @param position position of the light
	 * @param kC Discount factor
	 * @param kL Discount factor
	 * @param kQ Discount factor
	 * @param direction direction of the light
	 */
	public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
		super(intensity, position, kC, kL, kQ);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
		double cosA = Util.alignZero(direction.dotProduct(this.getL(p)));
		if (0 > cosA)
			return Color.BLACK;
		return super.getIntensity(p).scale(cosA);
	}

}
