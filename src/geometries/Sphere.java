package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry {

	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	private Point3D center;
	private double radius;
	
	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	public Point3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}

	

	
}
