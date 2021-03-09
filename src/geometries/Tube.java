package geometries;

import primitives.*;



public class Tube implements Geometry {
	
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	protected Ray axisRay; 
	protected double radius;
		
	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	public Ray getAxisRay() {
		return axisRay;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
	}



	
}
