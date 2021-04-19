/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import elements.*;
import geometries.*;
import primitives.*;

import org.junit.Test;

/**
 * 
 * Testing Integration between camera and Intersectable shapes
 * 
 * @author Shai&Avishay
 *
 */
public class CameraGeomtryIntegrationTests {

	/**
	 * Test method for Intersections of rays that coming out of a {@link Camera}
	 * with {@link Sphere}.
	 */
	@Test
	public void SphereIntegrationTests() {

		// TC01: Sphere at front of the view plane (2 points)
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
		cam.setViewPlaneDistance(1);
		cam.setViewPlaneSize(3, 3);
		Sphere sph = new Sphere(new Point3D(0, 0, -3), 1);
		assertEquals("bad Integration - Sphere at front of the view plane", countIntersections(cam, sph), 2);

		// TC02: Sphere between view plane and camera (18 points)
		cam = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0));
		cam.setViewPlaneDistance(1);
		cam.setViewPlaneSize(3, 3);
		sph = new Sphere(new Point3D(0, 0, -2.5), 2.5);
		assertEquals("bad Integration - Sphere between view plane and camera", countIntersections(cam, sph), 18);

		// TC03: Sphere between view plane and camera (10 points)
		sph = new Sphere(new Point3D(0, 0, -2), 2);
		assertEquals("bad Integration - Sphere in front of the camera", countIntersections(cam, sph), 10);

		// TC04: Camera and view plane inside the sphere (9 points)
		sph = new Sphere(new Point3D(0, 0, -2), 4);
		assertEquals("bad Integration - Sphere in front of the camera", countIntersections(cam, sph), 9);

		// TC05: Sphere behind the camera (0 points)
		sph = new Sphere(new Point3D(0, 0, 1), 0.5);
		assertEquals("bad Integration - Sphere behind the camera", countIntersections(cam, sph), 0);

	}

	/**
	 * Test method for Intersections of rays that coming out of a camera with
	 * {@link Plane}.
	 */
	@Test
	public void PlaneIntegrationTests() {
		// TC01: Plane parallel to the view plane (9 points)
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
		cam.setViewPlaneDistance(1);
		cam.setViewPlaneSize(3, 3);
		Plane pl = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, 0, 1)));
		assertEquals("bad Integration - Plane parallel to the view plane", countIntersections(cam, pl), 9);

		// TC02: Plane is tilted at the top a little down (9 points)
		pl = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, -0.5, 1)));
		assertEquals("bad Integration - Plane is tilted at the top a little down", countIntersections(cam, pl), 9);

		// TC03: Plane is parallel to all the rays coming out from the bottom of the
		// view plane (6 points)
		pl = new Plane(new Point3D(0, 0, -5), new Vector(new Point3D(0, -1, 1)));
		assertEquals(
				"bad Integration - Plane is parallel to all the rays coming out from the bottom of the view plane",
				countIntersections(cam, pl), 6);

	}

	/**
	 * Test method for Intersections of rays that coming out of a camera with
	 * {@link Triangle}.
	 */
	@Test
	public void TriangleIntegrationTests() {
		// TC01: A triangle located in front of the central pixel (1 points)
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
		cam.setViewPlaneDistance(1);
		cam.setViewPlaneSize(3, 3);
		Triangle tr = new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		assertEquals(
				"bad Integration - A triangle located in front of the central pixel",
				countIntersections(cam, tr), 1);

		// TC01: A triangle located opposite the central pixel and the central upper pixel (2 points)
		tr = new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		assertEquals(
				"bad Integration - A triangle located opposite the central pixel and the central upper pixel",
				countIntersections(cam, tr), 2);

	}

	
	/**
	 * The function receives a {@link Camera} and {@link Intersectable} and count
	 * the Intersections of rays that coming out of the camera with the shape.
	 * 
	 * @param cam   Camera with view plane.
	 * @param shape Intersectable shape.
	 * @return number of intersections.
	 */
	private int countIntersections(Camera cam, Intersectable shape) {
		int count = 0;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				List<Point3D> intsPoints = shape.findIntersections(cam.constructRayThroughPixel(3, 3, j, i));
				if (intsPoints != null)
					count += intsPoints.size();
			}

		return count;
	}
}
