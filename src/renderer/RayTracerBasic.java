/**
 * 
 */
package renderer;

import java.util.List;

import elements.LightSource;
import elements.SourceArea;
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
	 * Limit the amount of recursive calls to calcColor
	 * 
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * The minimum value of consideration of the coefficient
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;
	/**
	 * The initial value of coefficient
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
	 * The function receives a Ray and calculates its closest Intersection point
	 * with the shape(s)
	 * 
	 * @param ray Ray in three-dimensional space
	 * @param isAccleration TODO
	 * @return the closest Intersection point or null if there are not Intersection
	 *         points
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		return ray.getClosestGeoPoint(scene.geometries.findGeoIntersections(ray,Double.POSITIVE_INFINITY));
	}

	/**
	 * The function calculates color at a point By calling the recursive calcColor
	 * function
	 * 
	 * @param geopoint the point to calculate its color
	 * @param ray      the ray coming out towards the scene
	 * @return The color of the points that the ray meets
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * Recursive function that calculates color at a point while creating sub-rays
	 * for transparency and reflection
	 * 
	 * @param p     the point to calculate its color
	 * @param ray   the ray coming out towards the scene
	 * @param level the current level of recursion
	 * @param k     the current coefficient
	 * @return the color in the point
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
	 * @param k            the current coefficient
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
			// The effect of the shadow
			List<Vector> ls;

			// Create or get a creation of shadow rays
			if (lightSource instanceof SourceArea) {
				ls = ((SourceArea) lightSource).getLs(intersection.point);
			} else
				ls = List.of(lightSource.getL(intersection.point));

			double ktr = transparency(lightSource, ls, n, nv, intersection);

			if (ktr * k > MIN_CALC_COLOR_K) {
				Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);// Consider the shade
				if (nl * nv > 0) // sign(nl) != sing(nv)
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));

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
	 * A function that calculates the shadow of the shapes between the light source
	 * and the point — taking into account their transparency
	 * 
	 * @param ls       the light source
	 * @param lList    vectors from the light source to the point
	 * @param n        the normal at the point
	 * @param nv       The angle between the ray from the camera and the normal at
	 *                 the point
	 * @param geoPoint point to calculate its color
	 * @return double-value that represents coefficient to the intensity of light
	 *         (effect of shadow)
	 */
	private double transparency(LightSource ls, List<Vector> lList, Vector n, double nv, GeoPoint geoPoint) {
		double sum = 0;
		Material material = geoPoint.geometry.getMaterial();
		for (Vector l : lList) {

			double nl = Util.alignZero(n.dotProduct(l));
			double ktr = 1.0;
			if (nl * nv <= 0) // sign(nl) != sing(nv)
				ktr *= material.kT;

			Vector lightDirection = l.scale(-1); // from point to light source
			Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
			List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
					ls.getDistance(geoPoint.point));
			if (intersections != null)
				for (GeoPoint gp : intersections) {
					ktr *= gp.geometry.getMaterial().kT;
					if (ktr < MIN_CALC_COLOR_K)
						ktr = 0.0;
				}

			sum += ktr;

		}
		return sum / lList.size();
	}

	/**
	 * A help function that calculates the effect of the Transparency and reflection
	 * on the color of the Point
	 * 
	 * @param geopoint point to calculate its color
	 * @param ray      the ray coming out towards the scene
	 * @param level    the current level
	 * @param k        the current coefficient
	 * @return color that represent the effect of the Transparency and reflection on
	 *         the color of the Point
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {// Calculating the effect of reflection
			Ray reflectedRay = constructReflectedRay(geopoint, geopoint.geometry.getNormal(geopoint.point), ray);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
			else {
				color = color.add(scene.background).scale(kr);
			}

		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {// Calculating the effect of transparency
			Ray refractedRay = constructRefractedRay(geopoint, geopoint.geometry.getNormal(geopoint.point), ray);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
			else {
				color = color.add(scene.background).scale(kt);
			}
		}
		return color;
	}

	/**
	 * The function builds a sub-ray for the purpose of simulating a reflection
	 * effect
	 * 
	 * @param point  point to calculate its color
	 * @param normal the normal at the the point
	 * @param inRay  the ray coming out towards the scene
	 * @return Ray that That simulates reflection
	 */
	private Ray constructReflectedRay(GeoPoint point, Vector normal, Ray inRay) {

		Vector r = inRay.getDir().subtract(normal.scale(inRay.getDir().dotProduct(normal) * 2));
		return new Ray(point.point, r, normal);
	}

	/**
	 * The function builds a sub-ray for the purpose of simulating a transparency
	 * effect
	 * 
	 * @param point  point to calculate its color
	 * @param normal the normal at the the point
	 * @param inRay  the ray coming out towards the scene
	 * @return Ray that That simulates transparency
	 */
	private Ray constructRefractedRay(GeoPoint point, Vector normal, Ray inRay) {
		return new Ray(point.point, inRay.getDir(), normal);
	}

	/**
	 * A function that checks whether a point is shaded or not (with respect to a
	 * particular light source)
	 * 
	 * @param l     vector from light source to point
	 * @param n     the normal at the point
	 * @param gp    The point being checked
	 * @param light the light source
	 * @return Binary Answer - Is the point shaded or not
	 */
	private boolean unShaded(Vector l, Vector normal, GeoPoint gp, LightSource light) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection, normal);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
		if (intersections == null)// If there is no shape between the point and the light source
			return true;
		for (GeoPoint p : intersections) {
			if (p.geometry.getMaterial().kT == 0)// If there is a shape that is not transparent between the point and
													// the light source
				return false;
		}
		return true;

	}

}