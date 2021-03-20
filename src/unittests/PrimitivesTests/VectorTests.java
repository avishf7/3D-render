/**
 * 
 */
package unittests.PrimitivesTests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Coordinate;
import primitives.Point3D;

import primitives.Vector;

/**
 * @author Shai&Avishay
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
		// TC01: Zero vector construction
		assertThrows("Constructed a zero vector", IllegalArgumentException.class,
				() -> new Vector(new Coordinate(0), new Coordinate(0), new Coordinate(0)));
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 */
	@Test
	public void testVectorDoubleDoubleDouble() {
		// TC01: Zero vector construction
		assertThrows("Constructed a zero vector", IllegalArgumentException.class, () -> new Vector(0, 0, 0));
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Point3D)}.
	 */
	@Test
	public void testVectorPoint3D() {
		// TC01: Zero vector construction
		assertThrows("Constructed a zero vector", IllegalArgumentException.class,
				() -> new Vector(new Point3D(0, 0, 0)));
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// TC01: Test Vector + Vector
		assertTrue("Vector + Vector does not work correctly", new Vector(-1, -2, -3).equals(v1.add(v2)));

	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		// TC01: Test Vector - Vector
		assertTrue("Vector - Vector does not work correctly", new Vector(-3, -6, -9).equals(v2.subtract(v1)));
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		// TC01: Test if Scale product work correctly
		assertTrue("Scale product does not work correctly", new Vector(2, 4, 6).equals(v1.scale(2)));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		// TC01: Test if dotProduct() result for orthogonal vectors is zero.
		assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
		
		// TC02: Test if dotProduct() result is correct
		assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));

	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		// TC01: Test Cross-Product between parallel vectors
		assertThrows("crossProduct() for parallel vectors does not throw an exception", IllegalArgumentException.class,
				() -> v1.crossProduct(v2));

		Vector vr = v1.crossProduct(v3);
		// TC02: Test Cross-Product vs vector length
		assertTrue("crossProduct() wrong result length", isZero(vr.length() - v1.length() * v3.length()));
		
		// TC03: Test if Cross-Product result is orthogonal to the multiplied vectors
		assertTrue("crossProduct() result is not orthogonal to its operands",
				isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// TC01: Test length Squared
		assertTrue("lengthSquared() wrong value", isZero(new Vector(1, 2, 3).lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// TC01: Test length
		assertTrue("length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector vCopy = new Vector(v1.getHead());
		Vector vCopyNormalize = vCopy.normalize();
		
		// TC01: Test Test if normalize() function does not create a new vector
		assertTrue("normalize() function creates a new vector", vCopy == vCopyNormalize);
		
		// TC02: Test vector normalization vs vector length
		assertTrue("normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		// TC01: Test if normalized() function create a new vector
		Vector u = v1.normalized();
		assertTrue("normalized() function does not create a new vector", u != v1);
	}

}
