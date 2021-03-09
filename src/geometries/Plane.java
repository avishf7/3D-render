package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {

	public Plane(Point3D p1,Point3D p2,Point3D p3) {
		this.q0 = p1;
		this.normal = null;
	}
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal;
	}
	
	private Point3D q0;
	private Vector normal;
	
	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}
	
	public Point3D getQ0() {
		return q0;
	}
	
	public Vector getNormal() {
		return normal;
	}
	
	@Override
	public String toString() {
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}
	
	
	

}
