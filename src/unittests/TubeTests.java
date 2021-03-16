/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author User
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Tube t1 = new Tube(new Ray(Point3D.ZERO,new Vector(0, 0, 3)),2);				
		assertEquals("Bad normal to Tube", new Vector(1,0,0), t1.getNormal(new Point3D(2, 0, 3)));
	}

}
