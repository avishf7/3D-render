/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import elements.*;
import geometries.*;
import primitives.Point3D;
import primitives.Vector;

import org.junit.Test;

/**
 * @author User
 *
 */
public class CameraGeomtryIntegrationTests {

	@Test
	public void SphereIntegrationTests() {

		// TC01: Sphere in front of the view plane (2 points)
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
		cam.setViewPlaneDistance(1);
		cam.setViewPlaneSize(3, 3);
		Sphere sph = new Sphere(new Point3D(0, 0, -3), 1);
		assertEquals("bad Integration - Sphere in front of the camera", countIntersections(cam, sph), 2);

		// TC02: Sphere between view plane and camera (18 points)
		cam = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0));
		cam.setViewPlaneDistance(1);
		cam.setViewPlaneSize(3, 3);
		sph = new Sphere(new Point3D(0, 0, -2.5), 2.5);
		assertEquals("bad Integration - Sphere between view plane and camera", countIntersections(cam, sph), 18);

		// TC03: Sphere between view plane and camera (10 points)
		sph = new Sphere(new Point3D(0, 0, -2), 2);
		assertEquals("bad Integration - Sphere in front of the camera", countIntersections(cam, sph), 10);

		// TC04: camera and view plane inside the sphere (9 points)
		sph = new Sphere(new Point3D(0, 0, -2), 4);
		assertEquals("bad Integration - Sphere in front of the camera", countIntersections(cam, sph), 9);

	}

	@Test
	public void PlaneIntegrationTests() {
		// TC01: 3X3 Corner (0,0)

	}

	@Test
	public void TriangleIntegrationTests() {
		// TC01: 3X3 Corner (0,0)

	}

	private int countIntersections(Camera cam, Intersectable shape) {
		int count = 0;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				List<Point3D> intsPoints = shape.findIntersections(cam.constructRayThroughPixel(3, 3, i, j));
				if (intsPoints != null)
					count += intsPoints.size();
			}

		return count;
	}
}
