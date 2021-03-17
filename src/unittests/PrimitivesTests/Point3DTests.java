/**
 * 
 */
package unittests.PrimitivesTests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author  Assist
 *
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		assertTrue("Point - Point does not work correctly", new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(new Point3D(1, 2, 3))));
	}

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		assertTrue("Point + Vector does not work correctly", Point3D.ZERO.equals(new Point3D(1, 2, 3).add(new Vector(-1, -2, -3))));
	}

}
