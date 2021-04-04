package primitives;

import java.util.Objects;


/**
 * Class Ray is the basic class representing a Ray in the
 * Three-dimensional space.
 * 
 * @author Shai&Avishay
 */
public class Ray {

	/**
	 * Ray constructor receiving {@link Point3D} and {@link Vector}.
	 * @param p0 starting point
	 * @param dir Direction vector
	 */
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalized();
	}

	/**
	 * starting point
	 */
	private Point3D p0;
	/**
	 * Direction vector
	 */
	private Vector dir;

	
	/**
	 * The function moves the starting point of the ray in the direction
	 * of the ray vector at a distance of the given scalar
	 * 
	 * @param t scalar
	 * @return the point after movement
	 */
	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);
	}

	/**
	 * Getter
	 * @return starting point
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * Getter
	 * @return Direction vector
	 */
	public Vector getDir() {
		return dir;
	}

}
