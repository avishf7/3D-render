/**
 * 
 */
package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;

/**
 * RayTracerBasic is a class representing ray scanner
 * 
 * @author Shai&Avishay
 *
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * 
	 */
	private static final double DELTA = 0.1;
	/**
	 * 
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * 
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;

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
		List<GeoPoint> interPoint = scene.geometries.findGeoIntersections(ray);
		if (interPoint == null)
			return scene.background;
		return calcColor(ray.getClosestGeoPoint(interPoint), ray);

	}

	/**
	 * A help function that calculates the color of the scene at a particular point
	 * 
	 * @param p The required point
	 * @return Color the color of the scene at the given point
	 */
	private Color calcColor(GeoPoint p, Ray ray) {
		return scene.ambientLight.getIntensity().add(p.geometry.getEmmission()).add(calcLocalEffects(p, ray));
	}

	/**
	 * A help function that calculates the effect of the light sources on the color
	 * of the Point
	 * 
	 * @param intersection point to calculate its color
	 * @param ray          the ray coming out towards the scene
	 * @return color that represent the effect of the light sources on the color of
	 *         the Point
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {

		// check whether the light and point of view are from different directions of
		// the body
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;

		// Calculate the effect all light sources have on the point
		int nShininess = intersection.geometry.getMaterial().nShininess;
		double kd = intersection.geometry.getMaterial().kD, ks = intersection.geometry.getMaterial().kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				if (unShaded(l, n, intersection, lightSource)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity)
							.add(calcSpecular(ks, l, n, v, nShininess, lightIntensity)));
				}
			}
		}
		return color;
	}

	/**
	 * A help function calculates the effect of the light source with the diffusion
	 * 
	 * @param kd             the coefficient
	 * @param l              The vector from the light source to the point
	 * @param n              The normal in the point
	 * @param lightIntensity Original light intensity
	 * @return The color that reaches the point after the effect of diffusion
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double scale = l.dotProduct(n);
		if (scale < 0)
			scale *= -1;
		return lightIntensity.scale(kd * scale);

	}

	/**
	 * A help function calculates spectacular effect
	 * 
	 * @param ks             the coefficient
	 * @param l              The vector from the light source to the point
	 * @param n              The normal in the point
	 * @param v              the direction of the scanned ray
	 * @param nShininess     Spectacular exponent
	 * @param lightIntensity Original light intensity
	 * @return The color that reaches the point after the spectacular effect
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
		double scale = Util.alignZero(v.scale(-1).dotProduct(r));
		if (scale < 0)
			return Color.BLACK;
		return lightIntensity.scale(ks * Math.pow(scale, nShininess));
	}

	/**
	 * 
	 * @param l
	 * @param n
	 * @param gp
	 * @param light
	 * @return
	 */
	private boolean unShaded(Vector l, Vector n, GeoPoint gp, LightSource light) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point3D point = gp.point.add(delta);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
		return intersections == null;
	}

}
