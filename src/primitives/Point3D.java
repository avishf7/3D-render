package primitives;

import java.util.Objects;

/**
 * Class Point3D is the basic class representing a point in the
 * Three-dimensional space.
 * 
 * @author Shai&Avishay
 */
public class Point3D {
	/**
	 * A constant static variable that represents the "zero point"
	 */
	public static final Point3D ZERO = new Point3D(0, 0, 0);

	/**
	 * Point3D constructor receiving coordinate values.
	 * 
	 * @param x value of the Coordinate that relative to the x-axis
	 * @param y value of the Coordinate that relative to the y-axis
	 * @param z value of the Coordinate that relative to the z-axis
	 */
	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/**
	 * Point3D constructor receiving coordinates.
	 * 
	 * @param x Coordinate relative to the x-axis
	 * @param y Coordinate relative to the y-axis
	 * @param z Coordinate relative to the z-axis
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Coordinate relative to the x-axis
	 */
	final Coordinate x;
	/**
	 * Coordinate relative to the y-axis
	 */
	final Coordinate y;
	/**
	 * Coordinate relative to the z-axis
	 */
	final Coordinate z;

	/**
	 * The function performs a vector subtract
	 * 
	 * @param point A point with which subtract
	 * @return new vector that represent the result of vector1(represent by "this
	 *         point") - vector2(represent by Point received)
	 */
	public Vector subtract(Point3D point) {
		return new Vector(x.coord - point.x.coord, y.coord - point.y.coord, z.coord - point.z.coord);
	}

	/**
	 * The function performs a points sum
	 * 
	 * @param vec A vector representing a point for subtraction
	 * @return New point that represent the result of the subtraction
	 */
	public Point3D add(Vector vec) {
		Point3D other = vec.getHead();
		return new Point3D(x.coord + other.x.coord, y.coord + other.y.coord, z.coord + other.z.coord);
	}

	/**
	 * Calculates the distance between two points squared
	 * 
	 * @param point The other point
	 * @return The distance between two points squared
	 */
	public double distanceSquared(Point3D point) {

		return (x.coord - point.x.coord) * (x.coord - point.x.coord)
				+ (y.coord - point.y.coord) * (y.coord - point.y.coord)
				+ (z.coord - point.z.coord) * (z.coord - point.z.coord);
	}

	/**
	 * Calculates the distance between two points
	 * 
	 * @param point The other point
	 * @return The distance between two points squared
	 */
	public double distance(Point3D point) {
		return Math.sqrt(distanceSquared(point));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(z, other.z);
	}

	@Override
	public String toString() {
		return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	/**
	 * Getter
	 * 
	 * @return x coordinate value
	 */
	public double getX() {
		return x.coord;
	}

	/**
	 * Getter
	 * 
	 * @return y coordinate value
	 */
	public double getY() {
		return y.coord;
	}

	/**
	 * Getter
	 * 
	 * @return z coordinate value
	 */
	public double getZ() {
		return z.coord;
	}

}
