/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Assist
 *
 */
public class VectorTests {
	
	private Vector v1 = new Vector(1, 2, 3);
    private Vector v2 = new Vector(-2, -4, -6);
    private Vector v3 = new Vector(0, 3, -2);
	/**
	 * Test method for
	 * {@link primitives.Vector#Vector(primitives.Coordinate, primitives.Coordinate, primitives.Coordinate)}.
	 */
	@Test
	public void testVectorCoordinateCoordinateCoordinate() {
		try { // test zero vector
			new Vector(new Coordinate(0), new Coordinate(0), new Coordinate(0));
			fail("Constructed a zero vector");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 */
	@Test
	public void testVectorDoubleDoubleDouble() {
		try { // test zero vector
			new Vector(0, 0, 0);
			fail("Constructed a zero vector");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Point3D)}.
	 */
	@Test
	public void testVectorPoint3D() {
		try { // test zero vector
			new Vector(new Point3D(0, 0, 0));
			fail("Constructed a zero vector");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		
        assertTrue("Vector + Vector does not work correctly",new Vector(-1, -2, -3).equals(v1.add(v2)));
        
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		assertTrue("Vector - Vector does not work correctly",new Vector(-3, -6,-9).equals(v2.subtract(v1)));
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		assertTrue("Scale product does not work correctly",new Vector(2, 4, 6).equals(v1.scale(2)));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		// test Dot-Product
        assertTrue("dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
        assertTrue("dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
       
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		// test Cross-Product
        try { // test zero vector
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
        
        Vector vr = v1.crossProduct(v3);
        assertTrue("crossProduct() wrong result length",isZero(vr.length() - v1.length() * v3.length()));
        assertTrue("crossProduct() result is not orthogonal to its operands",isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {

		// test length Squared
		assertTrue("lengthSquared() wrong value",isZero(new Vector(1, 2, 3).lengthSquared()- 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// test length
		assertTrue("length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		// test vector normalization vs vector length and cross-product
        Vector vCopy = new Vector(v1.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertTrue("normalize() function creates a new vector",vCopy == vCopyNormalize);
        assertTrue("normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));  
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector u = v1.normalized();
		assertTrue("normalizated() function does not create a new vector",u != v1);  
	}

}

