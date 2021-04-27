package geometries;
 import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import primitives.*;

/**
* interface Intersectable is interface for working with rays and their intersection with geometric shapes.
* 
* @author Shai&Avishay
*/
public interface Intersectable {
	
	/**
	 * 
	 * @author User
	 *
	 */
	public static class GeoPoint {
	    /**
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof  GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return Objects.equals(geometry, other.geometry) && Objects.equals(point, other.point);
		}

		public Geometry geometry;
	    public Point3D point;
	}

	
	/**
	 * The function receives a {@link Ray} 
	 * and calculates its Intersections with the shape.
	 * @param ray A Ray in three-dimensional space
	 * @return List of The Intersections
	 */
	default List<Point3D> findIntersections(Ray ray) {
	    var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	
	/**
	 * The function receives a {@link Ray} 
	 * and calculates its Intersections with the shape.
	 * @param ray A Ray in three-dimensional space
	 * @return List(<GeoPoint>) of The Intersections points with their shapes
	 */
	List <GeoPoint> findGeoIntersections(Ray ray);
}
