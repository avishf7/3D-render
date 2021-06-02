package geometries;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import primitives.*;

/**
 * class Intersectable is an abstract class for working with rays and their
 * intersection with geometric shapes.
 * 
 * @author Shai&Avishay
 */
public abstract class Intersectable {

	/**
	 * help box that wraps the Intersectable for finding intersections with the
	 * Intersectable object
	 */
	protected WrapBox box;

	/**
	 * 
	 */
	abstract void setBox();
	
	/**
	 * Class GeoPoint is the basic class that represents a point of shape with her
	 * shape
	 * 
	 * @author Shai&Avishay
	 *
	 */
	public static class GeoPoint {
		/**
		 * CTOR
		 * 
		 * @param geometry the shape that the point belong
		 * @param point    the point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return Objects.equals(geometry, other.geometry) && Objects.equals(point, other.point);
		}

		public Geometry geometry;
		public Point3D point;
	}

	/**
	 * The function receives a {@link Ray} and calculates its Intersections with the
	 * shape.
	 * 
	 * @param ray A Ray in three-dimensional space
	 * @return List of The Intersections
	 */
	public List<Point3D> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	/**
	 * The function receives a {@link Ray} and calculates its Intersections with the
	 * shape.
	 * 
	 * @param ray A Ray in three-dimensional space
	 * @return List(<GeoPoint>) of The Intersections points with their shapes
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * The function receives a {@link Ray} and range(number) and calculates ray's
	 * Intersections with the shape in the range.
	 * 
	 * @param ray         A Ray in three-dimensional space
	 * @param maxDistance Intersection range
	 * @return List(<GeoPoint>) of The Intersections points in the range with their
	 *         shapes
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance){
		return findGeoIntersections(ray, maxDistance, false);
	}
	
	/**
	 * The function receives a {@link Ray} and range(number) and calculates ray's
	 * Intersections with the shape in the range,
	 * With an option to accelerate performance.
	 * 
	 * @param ray         A Ray in three-dimensional space
	 * @param maxDistance Intersection range
	 * @param isAccelerated Determines whether to accelerate performance
	 * @return List(<GeoPoint>) of The Intersections points in the range with their
	 *         shapes
	 */
	public abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance, boolean isAccelerated);
}
