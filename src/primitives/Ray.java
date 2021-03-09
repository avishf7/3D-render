package primitives;

import java.util.Objects;

public class Ray {

	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalized();
	}
	Point3D p0;
	Vector dir;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);
	}

	public Point3D getP0() {
		return p0;
	}

	public Vector getDir() {
		return dir;
	}
	
}