package primitives;

public class Vector {

	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D tmp = new Point3D(x,y,z);
		if(tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		head = tmp;
	}
/**
 * 
 * @param x
 * @param y
 * @param z
 */
	public Vector(double x, double y, double z) {
		Point3D tmp = new Point3D(x,y,z);
		if(tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		head = tmp;
	}

	public Vector(Point3D other) {
		if(other.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		this.head = other;
	}

	Point3D head;
}
