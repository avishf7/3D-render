/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTests {

	/**
	 * Test method for
	 * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct concave quadrangular with vertices in correct order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct polygon");
		}

		// TC02: Wrong vertices order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0), new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
			fail("Constructed a polygon with wrong order of vertices");
		} catch (IllegalArgumentException e) {
		}

		// TC03: Not in the same plane
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 2, 2));
			fail("Constructed a polygon with vertices that are not in the same plane");
		} catch (IllegalArgumentException e) {
		}

		// TC04: Concave quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
			fail("Constructed a concave polygon");
		} catch (IllegalArgumentException e) {
		}

		// =============== Boundary Values Tests ==================

		// TC10: Vertex on a side of a quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
			fail("Constructed a polygon with vertix on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC11: Last point = first point
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC12: Colocated points
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 1, 0));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
				new Point3D(-1, 1, 1));
		double sqrt3 = Math.sqrt(1d / 3);
		Vector normal = pl.getNormal(new Point3D(0, 0, 1));
		assertTrue("Bad normal to polygon",
				new Vector(sqrt3, sqrt3, sqrt3).equals(normal) || new Vector(-sqrt3, -sqrt3, -sqrt3).equals(normal));
	}

	/**
	 * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Polygon pl = new Polygon(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(1, 1, 0),
				new Point3D(0, 1, 0));

		// ============ Equivalence Partitions Tests ==============

		// TC01:Inside polygon
		Point3D p1 = new Point3D(0.5, 0.25, 0);
		List<Point3D> result = pl.findIntersections(new Ray(new Point3D(0.5, 0.25, 1), new Vector(0, 0, -1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Bad intsersection point", p1, result.get(0));

		// TC02:Outside against edge
		assertNull("Ray's line out of polygon",
				pl.findIntersections(new Ray(new Point3D(2, 0.25, 1), new Vector(0, 0, -1))));

		// TC03:Outside against vertex
		assertNull("Ray's line out of polygon",
				pl.findIntersections(new Ray(new Point3D(-1, -0.5, 1), new Vector(0, 0, -1))));

		// =============== Boundary Values Tests ==================

		// TC11:On edge
		assertNull("Ray's line out of polygon",
				pl.findIntersections(new Ray(new Point3D(0.5, 0, 1), new Vector(0, 0, -1))));

		// TC12:In vertex
		assertNull("Ray's line out of polygon",
				pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 0, -1))));

		// TC13:On edge's continuation
		assertNull("Ray's line out of polygon",
				pl.findIntersections(new Ray(new Point3D(2, 0, 1), new Vector(0, 0, -1))));
	}

	/**
	 * Test method for
	 * {@link geometries.Polygon#findGeoIntersections(primitives.Ray, double)}.
	 */
	@Test
	public void testFindGeoIntersections() {
		Polygon pl = new Polygon(new Point3D(0, 1, 0), new Point3D(1, 1, 0), new Point3D(1, 1, 1),
				new Point3D(0, 1, 1));
		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray intersects the polygon in the range
		List<GeoPoint> result = pl.findGeoIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(0, 1, 0.5)), 2);
		assertEquals("The Ray should Intersect with polygon in the range", 1, result.size());

		// TC02: Ray intersects the polygon in out of the range
		result = pl.findGeoIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(0, 1, 0.5)), 1);
		assertNull("The Ray should not Intersect with polygon", result);

		// =============== Boundary Values Tests ==================
		// TC01: Ray intersects the polygon exactly at the edge of the range
		result = pl.findGeoIntersections(new Ray(new Point3D(0.5, 0, 0.5), new Vector(0, 1, 0)), 1);
		assertEquals("The Ray should Intersect with polygon in the range", 1, result.size());
	}

}
