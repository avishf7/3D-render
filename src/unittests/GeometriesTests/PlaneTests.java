/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Polygon;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Plane
 * 
 * @author Shai&Avishay
 *
 */
public class PlaneTests {

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		// TC01: Test Construction of a proper plane
		try {
			new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Plane");
		}
		// TC02: Test the case that all points on the same line
		assertThrows("Constructed  plane with a single vector", IllegalArgumentException.class,
				() -> new Plane(new Point3D(1, 1, 1), new Point3D(2, 2, 2), new Point3D(-1, -1, -1)));

		// TC03: Test the case that identical points has been received
		assertThrows("Constructed plane When the first two points are identical", IllegalArgumentException.class,
				() -> new Plane(new Point3D(1, 1, 1), new Point3D(1, 1, 1), new Point3D(2, 3, 4)));

	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Test that the returned vector is indeed normal to the plane
		Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		Vector normal = pl.getNormal(new Point3D(0, 0, 1));
		assertTrue("Bad normal to plane",
				new Vector(sqrt3, sqrt3, sqrt3).equals(normal) || new Vector(-sqrt3, -sqrt3, -sqrt3).equals(normal));
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane pl = new Plane(new Point3D(0, 1, 0), new Point3D(1, 1, 0), new Point3D(0, 1, 1));
		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray intersects the plane(1 point)
		Point3D p1 = new Point3D(2, 1, 0);
		List<Point3D> result = pl.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Wrong Intersections points", p1, result.get(0));

		// TC02: Ray does not intersect the plane(0 points)
		assertNull("The Ray should not Intersect with plane",
				pl.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(1, 1, 0))));

		// =============== Boundary Values Tests ==================
		// ****Ray is parallel to the plane

		// TC11: Ray included in the plane
		assertNull("The Ray should not Intersect with plane",
				pl.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));

		// TC12: Ray not included in the plane
		assertNull("The Ray should not Intersect with plane",
				pl.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(1, 0, 0))));

		// ****Ray orthogonal to the plane
		// TC13: Ray starts before the plane
		p1 = new Point3D(1, 1, 0);
		result = pl.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Wrong Intersections points", p1, result.get(0));

		// TC14: Ray starts in the plane
		assertNull("The Ray should not Intersect with plane",
				pl.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))));

		// TC15: Ray starts after the plane
		assertNull("The Ray should not Intersect with plane",
				pl.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))));

		// ****Ray is neither orthogonal nor parallel to the plane
		// TC16:Begins at the plane
		assertNull("The Ray should not Intersect with plane",
				pl.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0))));

		// TC17:Begins in the same point which appears as reference point in the plane
		// (Q)
		assertNull("The Ray should not Intersect with plane",
				pl.findIntersections(new Ray(pl.getQ0(), new Vector(1, 1, 0))));

	}

	/**
	 * Test method for
	 * {@link geometries.Plane#findGeoIntersections(primitives.Ray, double)}.
	 */
	@Test
	public void testFindGeoIntersections() {
		Plane pl = new Plane(new Point3D(0, 1, 0), new Point3D(1, 1, 0), new Point3D(0, 1, 1));
		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray intersects the plane in the range
		List<GeoPoint> result = pl.findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 1, 0)), 2);
		assertEquals("The Ray should Intersect with plane in the range", 1, result.size());

		// TC02: Ray intersects the plane in out of the range
		result = pl.findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 1, 0)), 1);
		assertNull("The Ray should not Intersect with plane", result);
		
		// =============== Boundary Values Tests ==================
		// TC01: Ray intersects the plane exactly at the edge of the range
		result = pl.findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)), 1);
		assertEquals("The Ray should Intersect with plane in the range", 1, result.size());
	}

}
