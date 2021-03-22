/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

/**
 * Testing Plane
 * @author Shai&Avishay
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		//TC01: Test Construction of a proper plane
		try {
			new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Plane");
		}
		//TC02: Test the case that all points on the same line 
		assertThrows("Constructed  plane with a single vector", IllegalArgumentException.class,
				() -> new Plane(new Point3D(1,1,1),new Point3D(2,2,2),new Point3D(-1,-1,-1)));
		
		//TC03: Test the case that identical points has been received
		assertThrows("Constructed plane When the first two points are identical", IllegalArgumentException.class, 
				() -> new Plane(new Point3D(1,1,1),new Point3D(1,1,1),new Point3D(2,3,4)));
		
		
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
	public void testFindIntersections() {
	
	}

}
