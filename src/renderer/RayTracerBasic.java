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
 * @author Shai&Avishay
 *
 */
public class RayTracerBasic extends RayTracerBase {

	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> interPoint = scene.geometries.findIntersections(ray);
		if (interPoint == null)
			return scene.background;
		return calcColor(ray.findClosestPoint(interPoint));

	}

	private Color calcColor(Point3D p) {
		return scene.ambientLight.getIntensity();
	}

}
