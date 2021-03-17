/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author Assist
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		assertThrows("Constructed plane with a single vector", IllegalArgumentException.class,
				() -> new Plane(new Point3D(1,1,1),new Point3D(2,2,2),new Point3D(-1,-1,-1)));
		
		assertThrows("Constructed plane When the first two points are identical", IllegalArgumentException.class, 
				() -> new Plane(new Point3D(1,1,1),new Point3D(1,1,1),new Point3D(2,3,4)));
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));				
		double sqrt3 = Math.sqrt(1d / 3);
		Vector normal = pl.getNormal(new Point3D(0, 0, 1));
		assertTrue("Bad normal to plane",
				new Vector(sqrt3, sqrt3, sqrt3).equals(normal) || new Vector(-sqrt3, -sqrt3, -sqrt3).equals(normal));
	}

}
