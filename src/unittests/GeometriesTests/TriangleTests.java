/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Triangle
 * 
 * @author Shai&Avishay
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Test that the returned vector is indeed normal to the triangle at a
		// certain point
		Triangle t1 = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		Vector normal = t1.getNormal(new Point3D(0, 0, 1));
		assertTrue("Bad normal to triangle",
				new Vector(sqrt3, sqrt3, sqrt3).equals(normal) || new Vector(-sqrt3, -sqrt3, -sqrt3).equals(normal));
	}

	/**
	 * Test method for {@link geometries.Polygon#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle triangle = new Triangle(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(1, 1, 0));

		// ============ Equivalence Partitions Tests ==============

		// TC01:Inside triangle
		Point3D p1 = new Point3D(0.5, 0.25, 0);
		List<Point3D> result = triangle.findIntsersections(new Ray(new Point3D(0.5, 0.25, 1), new Vector(0, 0, -1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Bad intsersection point", p1, result.get(0));

		// TC02:Outside against edge
		assertNull("Ray's line out of Triangle",
				triangle.findIntsersections(new Ray(new Point3D(2, 0.25, 1), new Vector(0, 0, -1))));

		// TC03:Outside against vertex
		assertNull("Ray's line out of Triangle",
				triangle.findIntsersections(new Ray(new Point3D(-1, -0.5, 1), new Vector(0, 0, -1))));

		// =============== Boundary Values Tests ==================

		// TC11:On edge
		assertNull("Ray's line out of Triangle",
				triangle.findIntsersections(new Ray(new Point3D(0.5, 0.5, 1), new Vector(0, 0, -1))));

		// TC12:In vertex
		assertNull("Ray's line out of Triangle",
				triangle.findIntsersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 0, -1))));

		// TC13:On edge's continuation
		assertNull("Ray's line out of Triangle",
				triangle.findIntsersections(new Ray(new Point3D(2, 2, 1), new Vector(0, 0, -1))));
	}
}
