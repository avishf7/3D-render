/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Triangle t1 = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));				
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("Bad normal to triangle", new Vector(sqrt3, sqrt3, sqrt3), t1.getNormal(new Point3D(0, 0, 1)));
	}

}
