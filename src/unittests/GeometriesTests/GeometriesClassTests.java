/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
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
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		// =============== Boundary Values Tests ==================

		// TC01:empty collection
		Geometries geo = new Geometries();
		assertNull("There is no shapes", geo.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));

		geo = new Geometries(new Plane(new Point3D(3, 3, 0), new Point3D(1, -1, 0), new Point3D(2, 4, 0)),
				new Sphere(new Point3D(1, 0, 0), 1d),
				new Triangle(new Point3D(1, 1, 0), new Point3D(2, 0, 0), new Point3D(1, 0, 0)), new Polygon(
						new Point3D(1, 1, 0), new Point3D(1, 0, 0), new Point3D(1.5, -0.2, 0), new Point3D(2, 0, 0)));

		// TC02: No shape intersects
		assertNull("The Ray should not Intersect with the shapes",
				geo.findIntersections(new Ray(new Point3D(0, 0, 2), new Vector(1, 0, 0))));

		// TC03: only one shape intersects
		assertEquals("The Ray should Intersect with one shape",
				geo.findIntersections(new Ray(new Point3D(5, 0, 2), new Vector(0, 0, -1))).size(), 1);

		// TC04: all shapes intersects
		assertEquals("The Ray should Intersect with all shapes",
				geo.findIntersections(new Ray(new Point3D(1.5, 0.3, 2), new Vector(0, 0, -1))).size(), 5);

		// ============ Equivalence Partitions Tests ==============

		// TC11: Some (but not all) shapes intersect
		assertEquals("The Ray should Intersect with some (not all) shapes",
				geo.findIntersections(new Ray(new Point3D(0.5, 0, 2), new Vector(0, 0, -1))).size(), 3);

	}

	/**
	 * Test method for
	 * {@link geometries.Geometries#findGeoIntersections(primitives.Ray, double)}.
	 */
	@Test
	public void testFindGeoIntersections() {
		Geometries geo = new Geometries(new Plane(new Point3D(3, 3, 0), new Point3D(1, -1, 0), new Point3D(2, 4, 0)),
				new Sphere(new Point3D(1, 0, 0), 1d),
				new Triangle(new Point3D(1, 1, 0), new Point3D(2, 0, 0), new Point3D(1, 0, 0)), new Polygon(
						new Point3D(1, 1, 0), new Point3D(1, 0, 0), new Point3D(1.5, -0.2, 0), new Point3D(2, 0, 0)));
		// =============== Boundary Values Tests ==================
		
		// TC02: No shape in range
		assertNull("The Ray should not Intersect with the shapes",
				geo.findGeoIntersections(new Ray(new Point3D(1.5, 0.3, 2), new Vector(0, 0, -1)), 1));

		// TC02: all shapes in range
		assertEquals("The Ray should Intersect all shapes",
				geo.findGeoIntersections(new Ray(new Point3D(1.5, 0.3, 2), new Vector(0, 0, -1)), 3).size(), 5);

		// ============ Equivalence Partitions Tests ==============
		
		// TC02: Some (but not all) shapes intersect in range
		assertEquals("The Ray should Intersect some (but not all) shapes",
				geo.findGeoIntersections(new Ray(new Point3D(1.5, 0.3, 2), new Vector(0, 0, -1)), 2).size(), 4);
	}

}
