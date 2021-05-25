/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setViewPlaneDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50), 50) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKD(0.4).setKS(0.3).setnShininess(100).setkT(0.3)),
				new Sphere(new Point3D(0, 0, -50), 25) //
						.setEmmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCam(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setViewPlaneDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-950, -900, -1000), 400) //
						.setEmmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setnShininess(20).setkT(0.5)),
				new Sphere(new Point3D(-950, -900, -1000), 200) //
						.setEmmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setnShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
								.setEmmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setkR(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
								.setEmmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setkR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setViewPlaneDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)), //
				new Sphere(new Point3D(60, 50, -50), 30) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKD(0.2).setKS(0.2).setnShininess(30).setkT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCam(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere
	 * producing a shading
	 */
	@Test
	public void SpherePolygonsWithReflectionPolygonMirror() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setViewPlaneDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Polygon(new Point3D(-5, 120, -250), new Point3D(-5, 5, -290), new Point3D(-120, 5, -250),
						new Point3D(-120, 120, -210))//
								.setMaterial(new Material().setKS(0.8).setKD(0.2).setnShininess(100).setkR(1.0)), //
				new Polygon(new Point3D(85, -15, -60), new Point3D(85, -85, -60), new Point3D(15, -85, -60),
						new Point3D(15, -15, -60))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.9)), //
				new Polygon(new Point3D(85, -15, -140), new Point3D(85, -85, -140), new Point3D(15, -85, -140),
						new Point3D(15, -15, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.5)), //
				new Polygon(new Point3D(85, -15, -60), new Point3D(85, -85, -60), new Point3D(85, -85, -140),
						new Point3D(85, -15, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.5)), //
				new Polygon(new Point3D(85, -15, -60), new Point3D(15, -15, -60), new Point3D(15, -15, -140),
						new Point3D(85, -15, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.5)), //
				new Polygon(new Point3D(15, -15, -60), new Point3D(15, -85, -60), new Point3D(15, -85, -140),
						new Point3D(15, -15, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.5)), //
				new Polygon(new Point3D(15, -85, -60), new Point3D(85, -85, -60), new Point3D(85, -85, -140),
						new Point3D(15, -85, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.5)), //
				new Sphere(new Point3D(50, -50, -100), 30) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(300, 400, 400), new Point3D(0, 200, -300), new Vector(-1, -1, 2)) //
						.setKl(1E-5).setKq(1.5E-7));//

		Render render = new Render() //
				.setImageWriter(new ImageWriter("SpherePolygonsWithReflectionPolygonMirror", 600, 600)) //
				.setCam(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere
	 * producing a shading
	 */
	@Test
	public void Sphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setViewPlaneDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Plane(new Point3D(50, -50, -100).add(new Vector(0, -1, -2).normalized().scale(30)),
						new Vector(0, 1, 2)).setEmmission(new Color(java.awt.Color.DARK_GRAY))
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)),
				new Sphere(new Point3D(50, -50, -100), 30) //
						.setEmmission(new Color(java.awt.Color.GRAY)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)) //
		);
		scene.lights.add( //
				new PointLight(new Color(java.awt.Color.WHITE).reduce(4), new Point3D(100, -25, -25)/*
																									 * , new Vector(-1,
																									 * 0, -2)
																									 */) //
						.setKl(1E-5).setKq(1.5E-7).setRadius(3).setBeamsNum(400));//

		Render render = new Render() //
				.setImageWriter(new ImageWriter("Sphere", 600, 600)) //
				.setCam(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

}