/**
 * 
 */
package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic is a class representing ray scanner
 * 
 * @author Shai&Avishay
 *
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * RayTracerBasic constructor receiving {@link scene}.
	 * 
	 * @param scene the photographed scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * The function checks what color the ray coming out towards the scene meets
	 * 
	 * @param ray the ray coming out towards the scene
	 * @return Color The color of the points that the ray meets
	 */
	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> interPoint = scene.geometries.findIntersections(ray);
		if (interPoint == null)
			return scene.background;
		return calcColor(ray.findClosestPoint(interPoint));

	}

	/**
	 * A help function that calculates the color of the scene at a particular point
	 * 
	 * @param p The required point
	 * @return Color the color of the scene at the given point
	 */
	private Color calcColor(Point3D p) {
		return scene.ambientLight.getIntensity();
	}

}
