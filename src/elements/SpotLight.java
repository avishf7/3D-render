/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * @author Shai&Avishay
 *
 */
public class SpotLight extends PointLight {

	

	private Vector direction;
	
	/**
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param direction
	 */
	public SpotLight(Color intensity, Point3D position,Vector direction, double kC, double kL, double kQ ) {
		super(intensity, position, kC, kL, kQ);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
		double cosA=Util.alignZero(direction.dotProduct(this.getL(p)));
		if(0>cosA)
			return Color.BLACK;
		return super.getIntensity(p).scale(cosA);
	}

}
