package primitives;

import java.util.Objects;


/**
 * Class Vector is the basic class representing a vector in the
 * Three-dimensional space.
 * 
 * @author Shai&Avishay
 */
public class Vector {
	/**
	 * Vector constructor receiving coordinates.
	 * 
	 * @param x Coordinate relative to the x-axis
	 * @param y Coordinate relative to the y-axis
	 * @param z Coordinate relative to the z-axis
	 * @throws IllegalArgumentException in case of illegal point(the "zero point").
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D tmp = new Point3D(x, y, z);
		if (tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		head = tmp;
	}

	/**
	 * Vector constructor receiving coordinate values.
	 * 
	 * @param x value of the Coordinate that relative to the x-axis
	 * @param y value of the Coordinate that relative to the x-axis
	 * @param z value of the Coordinate that relative to the x-axis
	 * @throws IllegalArgumentException in case of illegal point(the "zero point").
	 */
	public Vector(double x, double y, double z) {
		Point3D tmp = new Point3D(x, y, z);
		if (tmp.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		head = tmp;
	}

	/**
	 * Vector constructor receiving point3D.
	 * @param other vector head
	 * @throws IllegalArgumentException in case of illegal point(the "zero point").
	 */
	public Vector(Point3D other) {
		if (other.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Invalid vector - (0,0,0)");
		this.head = new Point3D(other.x, other.y, other.z);
	}

	/**
	 * vector head
	 */
	private Point3D head;

	/**
	 * The function calculates an orthogonal vector to "this"
	 * @return a orthogonal vector to "this" vector
	 */
	public Vector getOrthogonal()
	{
		try {
		return new Vector(-head.getZ(), 0 , head.getX()).normalized();
		}
		catch(IllegalArgumentException ex)
		{
			return new Vector(-head.getY(), head.getX() , 0).normalized();
		}
	}
	
	/**
	 * The function performs a vector sum
	 * @param vec vector to add
	 * @return new vector that represent the sum of the two vectors
	 */
	public Vector add(Vector vec) {
		return new Vector(head.add(vec));
	}

	/**
	 * The function performs a vector subtract
	 * @param vec vector to subtract
	 * @return new vector that represent the result of vector1-vector2
	 */
	public Vector subtract(Vector vec) {
		return head.subtract(vec.head);
	}

	/**
	 * The function performs scalar multiplication
	 * @param scalar the scalar for multiply the vector
	 * @return vector that represent the result of scalar multiplication
	 */
	public Vector scale(double scalar) {
		return new Vector(head.x.coord * scalar, head.y.coord * scalar, head.z.coord * scalar);
	}

	/**
	 * The function performs Scleric product
	 * @param vec the vector to do with him the production
	 * @return the result of scleric product
	 */
	public double dotProduct(Vector vec) {
		return (head.x.coord * vec.head.x.coord + head.y.coord * vec.head.y.coord + head.z.coord * vec.head.z.coord);
	}

	/**
	 * The function performs vector product
	 * @param vec the vector to do with him the production
	 * @return vector that represent the result of vector product
	 */
	public Vector crossProduct(Vector vec) {
		return new Vector(head.y.coord * vec.head.z.coord - head.z.coord * vec.head.y.coord,
				head.z.coord * vec.head.x.coord - head.x.coord * vec.head.z.coord,
				head.x.coord * vec.head.y.coord - head.y.coord * vec.head.x.coord);
	}

	/**
	 * calculates the length of the vector squared
	 * @return length of the vector squared
	 */
	public double lengthSquared() {
		return head.distanceSquared(Point3D.ZERO);
	}

	/**
	 * calculates the length of the vector
	 * @return length of the vector
	 */
	public double length() {
		return head.distance(Point3D.ZERO);
	}

	/**
	 * the function normalizes the vector
	 * @return this vector after normalization 
	 */
	public Vector normalize() {
		head = scale(1 / length()).head;
		return this;
	}

	/**
	 * the function create normalized vector that equals to the normalized vector
	 * @return  the new vector 
	 */
	public Vector normalized() {
		return new Vector(head).normalize();
	}

	/**
	 * Getter
	 * @return head
	 */
	public Point3D getHead() {
		return head;
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

	
}
