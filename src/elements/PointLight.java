/**
 * 
 */
package elements;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * Class PointLight is a class that represents the PointLight on a scene
 * 
 * @author Shai&Avishay
 *
 */
public class PointLight extends Light implements LightSource {
	/**
	 * the radius of the light source
	 */
	public double radius;

	/**
	 * number of columns and rows
	 */
	private int xAndY;// not sure

	/*
	 * /** Approximate number of light beams that reach to a point
	 * 
	 * private int beamsNum;
	 */

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
	 * 
	 * @param intensity color of the light
	 * @param position  position of the light
	 * @param kC        Discount factor
	 * @param kL        Discount factor
	 * @param kQ        Discount factor
	 */
	public PointLight(Color intensity, Point3D position) {
		super(intensity);
		this.position = position;
		this.kC = 1;
		this.kL = 0;
		this.kQ = 0;
		this.radius = 0;
		this.xAndY = 1;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param radius the radius to set
	 */
	public PointLight setRadius(int radius) {
		this.radius = radius;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param beamsNum the beamsNum to set
	 */
	public PointLight setBeamsNum(int beamsNum) {
		this.xAndY = (int) Math.sqrt(beamsNum);
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param kC the kC to set
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param kL the kL to set
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
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
	public List<Vector> getLs(Point3D p) {
		return getLs(getL(p), p);
	}

	/**
	 * Help function for producing a target area and calculating the light beams
	 * coming from it to the point
	 * 
	 * @param vCenter
	 * @return
	 */
	protected List<Vector> getLs(Vector vCenter, Point3D p) {

		LinkedList<Vector> beams = new LinkedList<>(List.of(getL(p)));
		Point3D position1;
		if (radius == 0||xAndY==1)
			return beams;

		Vector vUp = vCenter.getOrthogonal();
		Vector vRight = vCenter.crossProduct(vUp).normalize();
		double interval = (2 * radius) / xAndY;

		// ****If the center is on the grid — move it to the nearest upper left
		// pixel****
		if (xAndY % 2 == 0) {
			position1=new Point3D(position);
			position1 = position1.add(vRight.scale(-interval / 2));
			position1 = position1.add(vUp.scale(interval / 2));
		}
		else
			position1=new Point3D(position);
		// ******************************************************************************

		// ---------------------Find the center point of the first
		// pixel--------------------

		double yI = -(0 - (xAndY - 1) / 2) * interval;
		double xJ = (0 - (xAndY - 1) / 2) * interval;
		Point3D pp =new Point3D(position1);

		// ********The conditions Prevent a situation that creates a zero
		// vector*********
		// ******(When the desired pixel center is on one of the axes of the
		// plane)******
		if (xJ != 0)
			pp = pp.add(vRight.scale(xJ));
		if (yI != 0)
			pp = pp.add(vUp.scale(yI));
		// ******************************************************************************

		// -----------------------------------------------------------------------------------

		for (int i = 0; i < xAndY; i++,pp = pp.add(vUp.scale(-interval))) {
			Point3D pp1=new Point3D(pp);
			for (int j = 0; j < xAndY; j++, pp1 = pp1.add(vRight.scale(interval))) {
				Point3D pp2=new Point3D(pp1);
				Random rand = new Random();
				double movementR = 0;
				double movementU = 0;

				movementR = rand.nextDouble() * interval - interval / 2;
				movementU = rand.nextDouble() * interval - interval / 2;

				// ********The conditions Prevent a situation that creates a zero vector
				// ***************(When the desired pixel is on one of the axes of the
				// plane)******
				if (!Util.isZero(movementR))
					pp2 = pp2.add(vRight.scale(movementR));
				if (!Util.isZero(movementU))
					pp2 = pp2.add(vUp.scale(movementU));
				// ******************************************************************************

				double distance;
				if ((distance = position.distance(pp2)) > radius) {
					Vector revVector = position.subtract(pp2).normalize();
					pp2 = pp2.add(revVector.scale(distance - radius));
				}
				
				beams.add(p.subtract(pp2).normalize());

			}
		}

		return beams;
	}

	@Override
	public double getDistance(Point3D point) {
		return position.distance(point);
	}

}
