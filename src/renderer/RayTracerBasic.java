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
	 * 
	 * @param intersection
	 * @param ray
	 * @return
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().nShininess;
		double kd = intersection.geometry.getMaterial().kD, ks = intersection.geometry.getMaterial().kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity)
						.add(calcSpecular(ks, l, n, v, nShininess, lightIntensity)));
			}
		}
		return color;
	}

	/**
	 * 
	 * @param kd
	 * @param l
	 * @param n
	 * @param lightIntensity
	 * @return
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double scale = l.dotProduct(n);
		if (scale < 0)
			scale *= -1;
		return lightIntensity.scale(kd * scale);

	}

	/**
	 * 
	 * @param ks
	 * @param l
	 * @param n
	 * @param v
	 * @param nShininess
	 * @param lightIntensity
	 * @return
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
		double scale = Util.alignZero(v.scale(-1).dotProduct(r));
		if (scale < 0)
			return Color.BLACK;
		return lightIntensity.scale(ks * Math.pow(scale, nShininess));
	}

}
