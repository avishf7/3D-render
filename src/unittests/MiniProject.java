/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * @author Shai&Avishay
 *
 */
public class MiniProject {

	private Scene scene = new Scene("Mini Project");

	/*
	 * @Test public void threeDModel() { Camera camera = new Camera(new Point3D(0,
	 * 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
	 * .setViewPlaneSize(200, 200).setViewPlaneDistance(1000);
	 * 
	 * scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),
	 * 0.15));
	 * 
	 * scene.geometries.add( // new Polygon(new Point3D(-5, 120, -250), new
	 * Point3D(-5, 5, -290), new Point3D(-120, 5, -250), new Point3D(-120, 120,
	 * -210))// .setMaterial(new
	 * Material().setKS(0.8).setKD(0.2).setnShininess(100).setkR(1.0)), // new
	 * Plane(new Point3D(85, -85, -140), new Point3D(15, -85, -140), new Point3D(50,
	 * -50, -220))// .setEmmission(new Color(java.awt.Color.DARK_GRAY))//
	 * .setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)), new
	 * Polygon(new Point3D(85, -15, -60), new Point3D(85, -85, -60), new Point3D(85,
	 * -85, -140), new Point3D(85, -15, -140))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Polygon(new Point3D(85, -15, -60), new Point3D(15, -15, -60), new Point3D(15,
	 * -15, -140), new Point3D(85, -15, -140))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Polygon(new Point3D(15, -15, -60), new Point3D(15, -85, -60), new Point3D(15,
	 * -85, -140), new Point3D(15, -15, -140))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Polygon(new Point3D(15, -85, -60), new Point3D(85, -85, -60), new Point3D(85,
	 * -85, -140), new Point3D(15, -85, -140))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(85, -85, -60), new Point3D(15, -85, -60), new
	 * Point3D(50, -50, -80))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(85, -15, -60), new Point3D(15, -15, -60), new
	 * Point3D(50, -50, -80))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(85, -15, -60), new Point3D(85, -85, -60), new
	 * Point3D(50, -50, -80))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(15, -85, -60), new Point3D(15, -15, -60), new
	 * Point3D(50, -50, -80))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(85, -85, -140), new Point3D(15, -85, -140), new
	 * Point3D(50, -50, -220)) .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(85, -15, -140), new Point3D(15, -15, -140), new
	 * Point3D(50, -50, -220)) .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(85, -15, -140), new Point3D(85, -85, -140), new
	 * Point3D(50, -50, -220)) .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Triangle(new Point3D(15, -85, -140), new Point3D(15, -15, -140), new
	 * Point3D(50, -50, -220)) .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), // new
	 * Sphere(new Point3D(50, -50, -100), 20) // .setEmmission(new
	 * Color(java.awt.Color.BLUE)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkR(1)), // new
	 * Sphere(new Point3D(50, -50, -150), 10) // .setEmmission(new
	 * Color(java.awt.Color.PINK)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30)), // new Sphere(new
	 * Point3D(50, -50, -190), 5) // .setEmmission(new Color(java.awt.Color.WHITE))
	 * // .setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30)) // );
	 * scene.lights.add(new DirectionalLight(new
	 * Color(java.awt.Color.YELLOW).reduce(8), new Vector(0, 0, -1))); scene.lights
	 * .add(new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(100, -90,
	 * -140), new Vector(-1, 1, 0))
	 * .setKl(0.0001).setKq(0.00099).setRadius(20).setBeamsNum(400)); scene.lights
	 * .add(new PointLight(new Color(java.awt.Color.YELLOW).reduce(4).reduce(2), new
	 * Point3D(-200, -50, -100))
	 * .setKl(0.0000001).setKq(0.0000000001).setRadius(20).setBeamsNum(400));
	 * 
	 * Render render = new Render() // .setImageWriter(new ImageWriter(scene.name,
	 * 600, 600)) // .setCam(camera) // .setRayTracer(new RayTracerBasic(scene))//
	 * .setMultithreading(3) // .setDebugPrint(); render.renderImage();
	 * render.writeToImage(); }
	 */

	/*
	 * @Test public void threeDModel1() {
	 * 
	 * Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1),
	 * new Vector(0, 1, 0)) // .setViewPlaneSize(150, 150) //
	 * .setViewPlaneDistance(1000);
	 * 
	 * scene.geometries.add( // new Geometries(new Sphere(new Point3D(0, -10, -90),
	 * 50) // .setEmmission(new Color(java.awt.Color.BLACK)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(100).setkT(0.6)), // new
	 * Sphere(new Point3D(0, -20, -110), 40) // .setEmmission(new
	 * Color(java.awt.Color.PINK)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(100)// .setkT(0.5).setkR(1)),
	 * // new Sphere(new Point3D(0, -40, -160), 30) // .setEmmission(new
	 * Color(java.awt.Color.ORANGE)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(100).setkT(0.4)), // new
	 * Sphere(new Point3D(0, -20, -70), 15) // .setEmmission(new
	 * Color(java.awt.Color.BLACK)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(200))), new Geometries(new
	 * Sphere(new Point3D(0, -60, -200), 20) // .setEmmission(new
	 * Color(java.awt.Color.RED)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(100).setkT(0.3)), // new
	 * Sphere(new Point3D(0, -80, -230), 10) // .setEmmission(new
	 * Color(java.awt.Color.RED)) // .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(100).setkR(1))), // new
	 * Geometries( new Triangle(new Point3D(100, 100, -120), new Point3D(100, -100,
	 * -110), new Point3D(0, -75, -1000))// .setEmmission(new
	 * Color(java.awt.Color.DARK_GRAY))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30)), // new Triangle(new
	 * Point3D(100, 100, -120), new Point3D(-100, 100, -120), new Point3D(0, -75,
	 * -1000))// .setEmmission(new Color(java.awt.Color.DARK_GRAY))//
	 * .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.3)), // new
	 * Triangle(new Point3D(100, -100, -110), new Point3D(-100, -100, -110), new
	 * Point3D(0, -75, -1000))// .setEmmission(new
	 * Color(java.awt.Color.DARK_GRAY))// .setMaterial(new
	 * Material().setKD(0.5).setKS(0.5).setnShininess(30)), // new Triangle(new
	 * Point3D(-100, -100, -110), new Point3D(-100, 100, -120), new Point3D(0, -75,
	 * -1000))// .setEmmission(new Color(java.awt.Color.DARK_GRAY))//
	 * .setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30))) //
	 * 
	 * ); scene.lights.add(new DirectionalLight(new Color(300, 400, 0).reduce(4),
	 * new Vector(0, 1, -1))); scene.lights.add(new PointLight(new Color(200, 300,
	 * 0), new Point3D(-100, 100, -120)).setKl(0.00001)
	 * .setKq(0.0000099).setRadius(20).setBeamsNum(400)); scene.lights.add(new
	 * SpotLight(new Color(150, 500, 0), new Point3D(-50, -50, 0), new Vector(1, 1,
	 * -10)) .setKl(0.0000001).setKq(0.0000000001).setRadius(20).setBeamsNum(400));
	 * 
	 * Render render = new Render() // .setImageWriter(new ImageWriter(scene.name +
	 * "1", 500, 500)) // .setCam(camera1) // .setRayTracer(new
	 * RayTracerBasic(scene).setAccelerated(true))// .setMultithreading(3) //
	 * .setDebugPrint(); render.renderImage(); render.writeToImage(); }
	 */

	@Test
	public void TempleTest() {

		 Camera camera1 = new Camera(new Point3D(-370, -1100, 350), new Vector(0.4, 1,
		 -0.4), new Vector(0, 0.4, 1)) //
		//Camera camera1 = new Camera(new Point3D(0, -900, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setViewPlaneSize(200, 200) //
				.setViewPlaneDistance(1000);

		for (int i = -48; i < 48; i += 32) {
			scene.geometries.add(

					new Polygon(new Point3D(i, -200, 20), new Point3D(i, -200, -4), // שורה עליונה מרובעים
							new Point3D(i + 32, -200, -4), new Point3D(i + 32, -200, 20))
									.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
									.setEmmission(new Color(255, 228, 196)));
		}
		scene.geometries.add(

				new Polygon(new Point3D(-48, -200, -4), new Point3D(-48, -200, -40), // שמאל תחתון
						new Point3D(-16, -200, -40), new Point3D(-16, -200, -4))
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
								.setEmmission(new Color(255, 228, 196)),

				new Polygon(new Point3D(48, -200, -4), new Point3D(48, -200, -40), // ימין תחתון
						new Point3D(16, -200, -40), new Point3D(16, -200, -4))
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
								.setEmmission(new Color(255, 228, 196)));

		for (int i = 0, j = 0; i < 30; i += 10, j += 4) {
			scene.geometries.add(new Polygon(new Point3D(-16 + j, -200 + i, -4), new Point3D(-16 + j, -200 + i, -40), // עמודי
																														// דלת
																														// שמאל
					new Point3D(-12 + j, -200 + i, -40), new Point3D(-12 + j, -200 + i, -4))
							.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
							.setEmmission(new Color(245, 222, 179)));

			scene.geometries.add(new Polygon(new Point3D(-12 + j, -200 + i, -4), new Point3D(-12 + j, -200 + i, -40), // עמודי
					// דלת
					// שמאל
					new Point3D(-12 + j, -200 + i + 10, -40), new Point3D(-12 + j, -200 + i + 10, -4))
							.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
							.setEmmission(new Color(245, 222, 179)));

		}

		for (int i = 0, j = 0; i < 30; i += 10, j += 4) {
			scene.geometries.add(new Polygon(new Point3D(16 - j, -200 + i, -4), new Point3D(16 - j, -200 + i, -40), // עמודי
																													// דלת
																													// ימין
					new Point3D(12 - j, -200 + i, -40), new Point3D(12 - j, -200 + i, -4))
							.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
							.setEmmission(new Color(245, 222, 179)));

			scene.geometries.add(new Polygon(new Point3D(12 - j, -200 + i, -4), new Point3D(12 - j, -200 + i, -40), // עמודי
					// דלת
					// ימין
					new Point3D(12 - j, -200 + i + 10, -40), new Point3D(12 - j, -200 + i + 10, -4))
							.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
							.setEmmission(new Color(245, 222, 179)));

		}

		for (int i = 0, j = 0; i < 30; i += 10, j += 4) {
			scene.geometries
					.add(new Polygon(new Point3D(-16 + j, -200 + i, -4 - j), new Point3D(16 - j, -200 + i, -4 - j), // עמודי
							// דלת
							// למעלה
							new Point3D(16 - j, -200 + i, -8 - j), new Point3D(-16 + j, -200 + i, -8 - j))
									.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
									.setEmmission(new Color(245, 222, 179)));

			scene.geometries
					.add(new Polygon(new Point3D(-16 + j, -200 + i, -8 - j), new Point3D(16 - j, -200 + i, -8 - j), // עמודי
					// דלת
// למעלה
							new Point3D(16 - j, -200 + i + 10, -8 - j), new Point3D(-16 + j, -200 + i + 10, -8 - j))
									.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
									.setEmmission(new Color(245, 222, 179)));

		}

		scene.geometries.add(new Polygon(new Point3D(-4, -170, -40), new Point3D(4, -170, -40), // דלת

				new Point3D(4, -170, -14), new Point3D(-4, -170, -14))
						.setMaterial(new Material().setKD(0.8).setKS(0.2).setnShininess(100))
						.setEmmission(new Color(160, 82, 45)));

		for (int i = -48; i < 48; i += 6) {
			scene.geometries.add(new Triangle(new Point3D(i, -200, 20), new Point3D(i + 3, -200, 24), // גג משולשים

					new Point3D(i + 6, -200, 20)).setMaterial(new Material().setKD(0.3).setKS(0.7).setnShininess(200))
							.setEmmission(new Color(218, 165, 32)));
		}

		Color[] arr = { new Color(0, 0, 0), new Color(230, 230, 250) };// רצפה
		int index = 0;
		for (int i = -208; i < 402; i += 32) {
			for (int j = -900; j < 300; j += 32) {
				scene.geometries.add(new Polygon(new Point3D(i, j, -40), new Point3D(i + 32, j, -40),

						new Point3D(i + 32, j + 32, -40), new Point3D(i, j + 32, -40))
								.setMaterial(new Material().setKD(0.2).setKS(0.8).setnShininess(200).setkR(0.2))
								.setEmmission(arr[(index)]));
				index = (index + 1) % 2;

			}
			index = (index + 1) % 2;
		}

		scene.geometries.add(new Polygon(new Point3D(-16, -150, -39.8), new Point3D(16, -150, -39.8), // שטיח אדום

				new Point3D(16, -900, -39.8), new Point3D(-16, -900, -39.8))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(139, 0, 0)));

		scene.geometries.add(new Polygon(new Point3D(-48, -200, 20), new Point3D(-48, -200, -40), // אולם שמאל

				new Point3D(-48, -168, -40), new Point3D(-48, -168, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(255, 228, 196)));

		scene.geometries.add(new Polygon(new Point3D(-48, -168, 20), new Point3D(-48, -168, -40), // אולם כניסה שמאל
																									// פנימה

				new Point3D(-32, -168, -40), new Point3D(-32, -168, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(255, 228, 196)));

		scene.geometries.add(new Polygon(new Point3D(48, -200, 20), new Point3D(48, -200, -40), // אולם ימין

				new Point3D(48, -168, -40), new Point3D(48, -168, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(255, 228, 196)));

		scene.geometries.add(new Polygon(new Point3D(48, -168, 20), new Point3D(48, -168, -40), // אולם ימין כניסה פנימה

				new Point3D(32, -168, -40), new Point3D(32, -168, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(255, 228, 196)));

		scene.geometries.add(new Polygon(new Point3D(-32, -168, 20), new Point3D(-32, -168, -40), // היכל קיר שמאלי

				new Point3D(-32, -72, -40), new Point3D(-32, -72, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(255, 228, 196)));

		scene.geometries.add(new Polygon(new Point3D(32, -168, 20), new Point3D(32, -168, -40), // היכל קיר ימני

				new Point3D(32, -72, -40), new Point3D(32, -72, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(255, 228, 196)));

		scene.geometries.add(new Polygon(new Point3D(32, -72, 20), new Point3D(32, -72, -40), // היכל קיר אחורי

				new Point3D(-32, -72, -40), new Point3D(-32, -72, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(255, 228, 196)));

		scene.geometries.add(new Sphere(new Point3D(0, -120, 20), 16).setEmmission(new Color(192, 192, 192))// כיפה
				.setMaterial(new Material()));//

		scene.geometries.add(new Polygon(new Point3D(48, -200, 20), new Point3D(48, -168, 20), // אולם תקרה

				new Point3D(-48, -168, 20), new Point3D(-48, -200, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(222, 184, 135)));

		scene.geometries.add(new Polygon(new Point3D(32, -72, 20), new Point3D(32, -168, 20), // היכל תקרה

				new Point3D(-32, -168, 20), new Point3D(-32, -72, 20))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
						.setEmmission(new Color(222, 184, 135)));

		for (int j = -18; j <= 16; j += 34) {

			for (int i = -380; i < -200; i += 30) {
				scene.geometries.add(new Polygon(new Point3D(j, i, -40), new Point3D(j, i, -10), // עמוד קדימה

						new Point3D(j + 2, i, -10), new Point3D(j + 2, i, -40))
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
								.setEmmission(new Color(160, 82, 45)),

						new Polygon(new Point3D(j, i + 2, -40), new Point3D(j, i + 2, -10), // אחורה עמוד

								new Point3D(j + 2, i + 2, -10), new Point3D(j + 2, i + 2, -40))
										.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
										.setEmmission(new Color(160, 82, 45)),

						new Polygon(new Point3D(j, i + 2, -40), new Point3D(j, i + 2, -10), // שמאל עמוד

								new Point3D(j, i, -10), new Point3D(j, i, -40))
										.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
										.setEmmission(new Color(160, 82, 45)),

						new Polygon(new Point3D(j + 2, i + 2, -40), new Point3D(j + 2, i + 2, -10), // ימין עמוד

								new Point3D(j + 2, i, -10), new Point3D(j + 2, i, -40))
										.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(100))
										.setEmmission(new Color(160, 82, 45)),

						new Sphere(new Point3D(j + 1, i + 1, -10), 3).setEmmission(new Color(java.awt.Color.WHITE))// מנורה
																													// על
																													// העמודים

				);
			}
		}

		scene.geometries.add(new Sphere(new Point3D(0, -400, 15), 5).setEmmission(new Color(java.awt.Color.WHITE))// מנורה
																													// תקועה
				.setMaterial(new Material().setkT(0.8)));//

		scene.lights.add(new PointLight(new Color(java.awt.Color.WHITE), new Point3D(0, -400, 15)).setKl(0.0001)
				.setKq(0.000099));

		// scene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW), new
		// Point3D(80,80,80)).setKl(0.0001).setKq(0.000099));
		/*
		 * scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(-30,
		 * -210, 10), new Vector(-2, 1, -1)) .setKl(0.0000001).setKq(0.0000000001));
		 * scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(30,
		 * -210, 10), new Vector(2, 1, -1)) .setKl(0.0000001).setKq(0.0000000001));
		 */

		boolean toOrder = true;

		Render render = new Render() //
				.setImageWriter(new ImageWriter(scene.name + "1", 800, 800)) //
				.setCam(camera1) //
				.setRayTracer(new RayTracerBasic(scene).accelerate(toOrder))//
				.setMultithreading(3) //
				.setDebugPrint();
		render.renderImage();
		render.writeToImage();
	}

}
