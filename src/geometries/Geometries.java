/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import primitives.Point3D;
import primitives.Ray;

/**
 * Class Geometries is class for works with several geometric shapes.
 * 
 * @author Shai&Avishay
 *
 */

public class Geometries implements Intersectable {

	List<Intersectable> shapes;

	/**
	 * 
	 */
	public Geometries() {
		shapes = new LinkedList<Intersectable>();
	}

	public Geometries(Intersectable... geometries) {
		shapes = List.of(geometries);
	}

	public void add(Intersectable... geometries) {
		shapes.addAll(List.of(geometries));
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		List<Point3D> intsPoints = null, result;

		for (Intersectable sh : shapes) {
			result = sh.findIntersections(ray);
			if (result != null)
				if (intsPoints == null)
					intsPoints = result;
				else
					(intsPoints).addAll(result);
		}
		
		
		return intsPoints;
		
		/*if(intsPoints == null)
			return null;
		
		return intsPoints.stream().distinct().collect(Collectors.toList());*/
	}

}