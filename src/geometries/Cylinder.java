package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {

	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}


	private double height;
	
	
	public double getHeight() {
		return height;
	}


	@Override
	public String toString() {
		return "Cylinder [height=" + height + "]";
	}


	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	
}
