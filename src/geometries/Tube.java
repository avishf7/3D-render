package geometries;

import static primitives.Util.alignZero;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * Class Tube is the basic class representing a tube in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public class Tube extends Geometry {
	/**
	 * Tube constructor receiving the ray and the radius of the tube.
	 * 
	 * @param axisRay A {@link Ray} that representing the The cylinder axis.
	 * @param radius  the radius of the tube.
	 */
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	/**
	 * the Ray of the tube.
	 */
	protected Ray axisRay;
	/**
	 * the radius of the tube.
	 */
	protected double radius;

	/**
	 * Getter
	 * 
	 * @return axisRay
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * Getter
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		double t = point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir());
		Point3D center = axisRay.getP0().add(axisRay.getDir().scale(t));
		return point.subtract(center).normalize();
	}

	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void buildBox() {
		// TODO Auto-generated method stub
		
	}

	
}
