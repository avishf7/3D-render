/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class PointLight is a class that represents the PointLight on a scene
 * 
 * @author Shai&Avishay
 *
 */
public class PointLight extends Light implements LightSource {

	/**
	 * position of the light
	 */
	private Point3D position;
	
	/**
	 * Discount factors
	 */
	private double kC, kL, kQ;

	/**
	 * CTOR
	 * @param intensity color of the light
	 * @param position position of the light
	 * @param kC Discount factor
	 * @param kL Discount factor
	 * @param kQ Discount factor
	 */
	public PointLight(Color intensity, Point3D position) {
		super(intensity);
		this.position = position;
		this.kC = 1;
		this.kL = 0;
		this.kQ = 0;
	}

	/**
	 * Builder pattern Setter
	 * @param kC the kC to set
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * @param kL the kL to set
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * @param kQ the kQ to set
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double dist = p.distance(position);
		double denominator = kC + kL * dist + kQ * dist * dist;
		return this.intensity.scale(1 / denominator);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

	@Override
	public double getDistance(Point3D point) {
		return position.distance(point);
	}

}
