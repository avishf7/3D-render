package primitives;

import java.util.Objects;

public class Vector {

	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D tmp = new Point3D(x, y, z);
		if (tmp.equals(Point3D.ZERO))
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
		Point3D tmp = new Point3D(x, y, z);
		if (tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		head = tmp;
	}

	public Vector(Point3D other) {
		if (other.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		this.head = other;
	}

	Point3D head;

	public Vector add(Vector vec) {
		return new Vector(head.add(vec));
	}

	public Vector subtract(Vector vec) {
		return head.subtract(vec.head);
	}

	public Vector scale(double scalar) {
		return new Vector(head.x.coord * scalar, head.y.coord * scalar, head.z.coord * scalar);
	}

	public double dotProduct(Vector vec) {
		return (head.x.coord * vec.head.x.coord + head.y.coord * vec.head.y.coord + head.z.coord * vec.head.z.coord);
	}

	public Vector crossProduct(Vector vec) {
		return new Vector(head.y.coord * vec.head.z.coord - head.z.coord * vec.head.y.coord,head.z.coord * vec.head.x.coord - head.x.coord * vec.head.z.coord,head.x.coord * vec.head.y.coord - head.y.coord * vec.head.x.coord);
	}
	
	public double lengthSquared() {
		return head.distanceSquared(Point3D.ZERO);
	}
	
	public double length() {
		return head.distance(Point3D.ZERO);
	}
	
	public Vector normalize() {
		head = scale(1/length()).head;
		return this;
	}
	
	public Vector normalized() {
		return new Vector(head).normalize();
		
		
	}

	@Override
	public String toString() {
		return "Vector [head=" + head + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return Objects.equals(head, other.head);
	}

	public Point3D getHead() {
		return head;
	}
	
	
}
