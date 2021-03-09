package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {

	public Plane(Point3D p1,Point3D p2,Point3D p3) {
		this.q0 = p1;
		//TODO in next stage
	}
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal;
	}
	Point3D q0;
	Vector normal;
	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
