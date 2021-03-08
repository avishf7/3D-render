package primitives;

public class Point3D {

	static Point3D ZERO = new Point3D(0, 0, 0);

	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	Coordinate x;
	Coordinate y;
	Coordinate z;

	public Vector subtract (Point3D point) {//**********
		return new Vector();
	}
	
	public double distanceSquared(Point3D point) {

		return (x.coord - point.x.coord) * (x.coord - point.x.coord)
				+ (y.coord - point.y.coord) * (y.coord - point.y.coord)
				+ (z.coord - point.z.coord) * (z.coord - point.z.coord);
	}

	public double distance(Point3D point) {
		return Math.sqrt(distanceSquared(point));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		result = prime * result + ((z == null) ? 0 : z.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		if (z == null) {
			if (other.z != null)
				return false;
		} else if (!z.equals(other.z))
			return false;
		return true;
	}
}
