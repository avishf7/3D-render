/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Tube
 * @author Shai&Avishay
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		
		// TC01: Test that the returned vector is indeed normal to the tube at a certain point
		Tube t1 = new Tube(new Ray(Point3D.ZERO, new Vector(0, 0, 3)), 2);
		Vector normal = t1.getNormal(new Point3D(2, 0, 3));
		assertTrue("Bad normal to Tube", new Vector(1, 0, 0).equals(normal) || new Vector(-1, 0, 0).equals(normal));
		
		// =============== Boundary Values Tests ==================
		
		// TC02: The point is perpendicular to the head
		assertThrows(
				"Normal calculation when the point perpendicular to the head of the fund does not throw an Exception(vector zero)",
				IllegalArgumentException.class, () -> t1.getNormal(new Point3D(1, 0, 0)));

	}
}
