/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Sphere s1 = new Sphere(new Point3D(0, 0, 0),3);				
		assertEquals("Bad normal to Sphere", new Vector(0,0,1), s1.getNormal(new Point3D(0, 0, 3)));
	}

}
