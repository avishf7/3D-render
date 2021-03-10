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
	 * 
	 * @param point
	 * @return
	 */
	public Vector subtract(Point3D point) {
		return new Vector(x.coord - point.x.coord, y.coord - point.y.coord, z.coord - point.z.coord);
	}

	/**
	 * 
	 * @param vec
	 * @return
	 */
	public Point3D add(Vector vec) {
		return new Point3D(x.coord + vec.getHead().x.coord, y.coord + vec.getHead().y.coord,
				z.coord + vec.getHead().z.coord);
	}

	/**
	 * 
	 * @param point
	 * @return
	 */
	public double distanceSquared(Point3D point) {

		return (x.coord - point.x.coord) * (x.coord - point.x.coord)
				+ (y.coord - point.y.coord) * (y.coord - point.y.coord)
				+ (z.coord - point.z.coord) * (z.coord - point.z.coord);
	}

	/**
	 * 
	 * @param point
	 * @return
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

}
