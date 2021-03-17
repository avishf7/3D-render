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
 * @author Shai&Avishay
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Sphere s1 = new Sphere(new Point3D(0, 0, 0),3);				
		
		Vector normal = s1.getNormal(new Point3D(0, 0, 3));
		assertTrue("Bad normal to Tube", new Vector(0, 0, 1).equals(normal) || new Vector(0, 0, -1).equals(normal));
	}

}
