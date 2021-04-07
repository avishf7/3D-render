/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import java.awt.Polygon;

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
	 * Test method for
	 * {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		// =============== Boundary Values Tests ==================
		
		// TC01:empty collection
		Geometries geo = new Geometries();
		assertNull("There is no shapes", geo.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));

		geo = new Geometries(new Plane(new Point3D(3, 3, 0), new Point3D(1, -1, 0), new Point3D(2, 4, 0)),
				new Sphere(new Point3D(1, 0, 0), 1d),
				new Triangle(new Point3D(1, 1, 0), new Point3D(2, 0, 0), new Point3D(1, 0, 0)),
				new geometries.Polygon(new Point3D(1, 1, 0), new Point3D(1, 0, 0), new Point3D(1.5, -0.2, 0),new Point3D(2, 0, 0)));

		// TC02: No shape intersects
		assertNull("The Ray should not Intersect with the shapes",
				geo.findIntsersections(new Ray(new Point3D(0, 0, 2), new Vector(1, 0, 0))));

		// TC03: only one shape intersects
		assertEquals("The Ray should Intersect with one shape",
				geo.findIntsersections(new Ray(new Point3D(5, 0, 2), new Vector(0, 0, -1))).size(),1);

		// TC04: all shapes intersects
		assertEquals("The Ray should Intersect with all shapes",
				geo.findIntsersections(new Ray(new Point3D(1.5, 0.3, 2), new Vector(0, 0, -1))).size(),5);

		// ============ Equivalence Partitions Tests ==============
		
		// TC11: Some (but not all) shapes intersect
		assertEquals("The Ray should Intersect with some (not all) shapes",
				geo.findIntsersections(new Ray(new Point3D(0.5, 0, 2), new Vector(0, 0, -1))).size(),3);
		
	}

}
