package geometries;
 import java.util.List;

import primitives.*;

/**
* interface Intersectable is interface for working with rays and their intersection with geometric shapes.
* 
* @author Shai&Avishay
*/
public interface Intersectable {
	/**
	 * The function receives a {@link Ray} 
	 * and calculates its Intersections with the shape.
	 * @param ray A Ray in three-dimensional space
	 * @return List of The Intersections
	 */
	List <Point3D> findIntersections(Ray ray);
}
