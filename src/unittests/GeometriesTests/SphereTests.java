/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Sphere;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Sphere
 * 
 * @author Shai&Avishay
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Test that the returned vector is indeed normal to the sphere at a
		// certain point
		Sphere s1 = new Sphere(new Point3D(0, 0, 0), 3);

		Vector normal = s1.getNormal(new Point3D(0, 0, 3));
		assertTrue("Bad normal to sphere", new Vector(0, 0, 1).equals(normal) || new Vector(0, 0, -1).equals(normal));
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<Point3D> result = sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(p1, p2), result);

		// TC03: Ray starts inside the sphere (1 point)
		p1 = new Point3D(1.5, 0.8660254037844386, 0);
		result = sphere.findIntsersections(new Ray(new Point3D(1.5, 0, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", p1, result.get(0));
		

		// TC04: Ray starts after the sphere (0 points)
		assertNull("Ray starts after the sphere",
				sphere.findIntsersections(new Ray(new Point3D(1.5, 2, 0), new Vector(0, 1, 0))));

		// =============== Boundary Values Tests ==================

		// **** Group: Ray's line crosses the sphere (but not the center)
		
		// TC11: Ray starts at sphere and goes inside (1 points)
		p1 = new Point3D(1, 1, 0);
		result = sphere.findIntsersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", p1, result.get(0));
		

		// TC12: Ray starts at sphere and goes outside (0 points)
		assertNull("Ray starts after the sphere",
				sphere.findIntsersections(new Ray(new Point3D(2, 0, 0), new Vector(1, -1, 0))));
		
		// **** Group: Ray's line goes through the center
		
		// TC13: Ray starts before the sphere (2 points)
		 p1 = new Point3D(1,-1,0);
	    p2 = new Point3D(1,1,0);
		 result = sphere.findIntsersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(p1, p2), result);
		
		// TC14: Ray starts at sphere and goes inside (1 points)
		p1 = new Point3D(1, -1, 0);
		result = sphere.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(0, -1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", p1, result.get(0));
		
		// TC15: Ray starts inside (1 points)
		p1 = new Point3D(1, 1, 0);
		result = sphere.findIntsersections(new Ray(new Point3D(1, 0.5, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", p1, result.get(0));
		
		// TC16: Ray starts at the center (1 points)
		p1 = new Point3D(2, 0, 0);
		result = sphere.findIntsersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", p1, result.get(0));
		
		// TC17: Ray starts at sphere and goes outside (0 points)
		assertNull("Ray starts after the sphere",
				sphere.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))));
		
		// TC18: Ray starts after sphere (0 points)
		assertNull("Ray starts after the sphere",
				sphere.findIntsersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))));

		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		
		// TC19: Ray starts before the tangent point
		assertNull("Ray's line out of sphere",
				sphere.findIntsersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))));

		// TC20: Ray starts at the tangent point
		assertNull("Ray's line out of sphere",
				sphere.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));
		
		// TC21: Ray starts after the tangent point
		assertNull("Ray's line out of sphere",
				sphere.findIntsersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))));

		// **** Group: Special cases
		
		// TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
		// center line
		assertNull("Ray's line out of sphere",
				sphere.findIntsersections(new Ray(new Point3D(1, 2, 0), new Vector(1, 0, 0))));

	}

}
