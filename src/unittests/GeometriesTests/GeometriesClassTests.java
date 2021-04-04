/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author User
 *
 */
public class GeometriesClassTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		// =============== Boundary Values Tests ==================
		//TC01:empty collection
		Geometries geo=new Geometries();
		assertNull("The Ray should not Intersect with the shapes",
				geo.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));
		
		geo=new Geometries(new Plane(new Point3D(3, 3, 0), new Point3D(1, -1, 0), new Point3D(2, 4, 0)));
	}

}
