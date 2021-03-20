/**
 * 
 */
package unittests.GeometriesTests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;

import primitives.Point3D;
import primitives.Vector;

/**
 * Testing Sphere
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
		
		// TC01: Test that the returned vector is indeed normal to the sphere at a certain point
		Sphere s1 = new Sphere(new Point3D(0, 0, 0),3);				
		
		Vector normal = s1.getNormal(new Point3D(0, 0, 3));
		assertTrue("Bad normal to sphere", new Vector(0, 0, 1).equals(normal) || new Vector(0, 0, -1).equals(normal));
	}

}
