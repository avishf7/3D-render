/**
 * 
 */
package elements;

import java.util.List;



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
	 * 
	 * @param intensity color of the light
	 * @param position  position of the light
	 * @param direction direction of the light
	 */
	public SpotLight(Color intensity, Point3D position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
		double cosA = Util.alignZero(direction.dotProduct(this.getL(p)));
		if (0 > cosA)
			return Color.BLACK;
		return super.getIntensity(p).scale(cosA);
	}

	@Override
	public List<Vector> getLs(Point3D p) {
		return getLs(direction,p);
	}

}
