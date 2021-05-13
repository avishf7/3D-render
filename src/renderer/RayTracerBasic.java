/**
 * 
 */
package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
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
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * 
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;
	/**
	 * 
	 */
	private static final double INITIAL_K = 1.0;

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
		GeoPoint interPoint = findClosestIntersection(ray);
		if (interPoint == null)
			return scene.background;
		return calcColor(interPoint, ray);

	}

	/**
	 * A help function that calculates the color of the scene at a particular point
	 * 
	 * @param p The required point
	 * @return Color the color of the scene at the given point
	 */
	private Color calcColor(GeoPoint p, Ray ray, int level, double k) {
		Color color = p.geometry.getEmmission().add(calcLocalEffects(p, ray, k));
		return 1 == level ? color : color.add(calcGlobalEffects(p, ray, level, k));

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
	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {

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
				double ktr = transparency(lightSource, l, n, intersection);
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));

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
	private boolean unShaded(Vector l, Vector normal, GeoPoint gp, LightSource light) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection, normal);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
		if (intersections == null)
			return true;
		for (GeoPoint p : intersections) {
			if (p.geometry.getMaterial().kT == 0)
				return false;
		}
		return true;

	}

	/**
	 * The function receives a Ray and calculates its closest Intersection point
	 * with the shape(s)
	 * 
	 * @param ray Ray in three-dimensional space
	 * @return the closest Intersection point or null if there are not Intersection
	 *         points
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		return ray.getClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
	}

	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * 
	 * @param geopoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(geopoint.geometry.getNormal(geopoint.point), geopoint, ray);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
			else
				color = color.add(scene.background);
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(geopoint.geometry.getNormal(geopoint.point), geopoint, ray);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
			else
				color = color.add(scene.background);
		}
		return color;
	}

	/**
	 * 
	 * @param normal
	 * @param point
	 * @param inRay
	 * @return
	 */
	private Ray constructReflectedRay(Vector normal, GeoPoint point, Ray inRay) {

		Vector r = inRay.getDir().subtract(normal.scale(inRay.getDir().dotProduct(normal) * 2));
		return new Ray(point.point, r, normal);
	}

	private Ray constructRefractedRay(Vector normal, GeoPoint point, Ray inRay) {
		return new Ray(point.point, inRay.getDir(), normal);
	}

	/**
	 * 
	 * @param ls
	 * @param l
	 * @param n
	 * @param geoPoint
	 * @return
	 */
	private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(geoPoint.point));
		if (intersections == null)
			return 1.0;
		double ktr = 1.0;
		for (GeoPoint gp : intersections) {
			ktr *= gp.geometry.getMaterial().kT;
			if (ktr < MIN_CALC_COLOR_K)
				return 0.0;
		}

		return ktr;
	}
}
