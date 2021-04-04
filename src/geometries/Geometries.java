/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

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
		shapes=new LinkedList<Intersectable>(); 
	}
	public Geometries(Intersectable... geometries) {
		shapes=List.of(geometries);
	}
	
	public void add(Intersectable... geometries) {
		shapes.addAll(List.of(geometries));
	}
	
	
	
	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
