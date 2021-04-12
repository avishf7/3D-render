package elements;

import primitives.*;

/**
 * Class Camera is a class that represents the point of view of an image viewer
 * 
 * @author Shai&Avishay
 * 
 */
public class Camera {
	/**
	 * @param p0
	 * @param vUp
	 * @param vTo
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		if (!Util.isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("\"up and to\" vectors are not orthogonal");
		vRight = vTo.crossProduct(vUp).normalize();
		this.p0 = p0;
		this.vUp = vUp.normalized();
		this.vTo = vTo.normalized();
		;
	}

	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance (from ViewPlane) to set
	 * @return this camera
	 */
	public Camera setViewPlaneDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * @param distance the distance to set
	 * @return this camera
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.height = height;
		this.width = width;
		return this;
	}

	/**
	 * 
	 * @param nX number of coloumns
	 * @param nY
	 * @param j
	 * @param i
	 * @return
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		Point3D pCenter = p0.add(vTo.scale(distance));
		
		double Ry = height / nY, Rx = width / nX;
		if (nX % 2 == 0)
			pCenter = pCenter.add(vRight.scale(-Rx/2));
		if (nY % 2 == 0)
			pCenter = pCenter.add(vUp.scale(Ry/2));
		double yI = -(i - (nY - 1) / 2) * Ry;
		double xJ = (j - (nX - 1) / 2) * Rx;
		Point3D pIJ = pCenter;
		if (xJ != 0)
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0)
			pIJ = pIJ.add(vUp.scale(yI));
		return new Ray(p0, pIJ.subtract(p0));
	}

}
