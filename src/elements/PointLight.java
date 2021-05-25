/**
 * 
 */
package elements;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
	 * the radius of the light source
	 */
	private double radius;

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
		
		if (radius == 0)
			return beams;
		
		Vector vUp = vCenter.getOrthogonal();
		Vector vRight = vCenter.crossProduct(vUp);

		double interval = (2 * radius) / xAndY;

		// ****If the center is on the grid — move it to the nearest upper left
		// pixel****
		if (xAndY % 2 == 0) {
			position = position.add(vRight.scale(-interval / 2));
			position = position.add(vUp.scale(interval / 2));
		}
		// ******************************************************************************

		// ---------------------Find the center point of the first
		// pixel--------------------

		double yI = -(0 - (xAndY - 1) / 2) * interval;
		double xJ = (0 - (xAndY - 1) / 2) * interval;
		Point3D pIJ = position;

		// ********The conditions Prevent a situation that creates a zero
		// vector*********
		// ******(When the desired pixel center is on one of the axes of the
		// plane)******
		if (xJ != 0)
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0)
			pIJ = pIJ.add(vUp.scale(yI));
		// ******************************************************************************

		// -----------------------------------------------------------------------------------

		for (int i = 0; i < xAndY; i++, pIJ = pIJ.add(vUp.scale(-interval)))
			for (int j = 0; j < xAndY; j++, pIJ = pIJ.add(vRight.scale(interval))) {
				Random rand = new Random();
				double movementR = 0;
				double movementU = 0;

				do {
					// Reducing the INTERVAL range to reduce the chance of recreating a point
					// outside the circle
					if (movementR != 0 && movementR != 0) {
						movementR = rand.nextDouble() * movementR - movementR / 2;
						movementU = rand.nextDouble() * movementU - movementU / 2;
					}
					// In the first case or in case there was no movement
					else {
						movementR = rand.nextDouble() * interval - interval / 2;
						movementU = rand.nextDouble() * interval - interval / 2;
					}

					// ********The conditions Prevent a situation that creates a zero vector
					// ***************(When the desired pixel is on one of the axes of the
					// plane)******
					if (movementR != 0)
						pIJ = pIJ.add(vRight.scale(movementR));
					if (movementU != 0)
						pIJ = pIJ.add(vUp.scale(movementU));
					// ******************************************************************************
				} while (position.distance(pIJ) >= radius);

				beams.add(pIJ.subtract(p));
			}

		return beams;
	}

	@Override
	public double getDistance(Point3D point) {
		return position.distance(point);
	}

}
