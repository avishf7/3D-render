
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author Shai&Avishay
 *
 */
public class DirectionalLight extends Light implements LightSource {

	private Vector direction;
	
	public DirectionalLight(Color intensity,Vector dir) {
		super(intensity);
		direction = dir;
	}

	@Override
	public Color getIntensity(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getL(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}
