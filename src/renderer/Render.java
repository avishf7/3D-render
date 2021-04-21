/**
 * 
 */
package renderer;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import scene.Scene;

/**
 * Class render is a representative class for render a scene from the camera
 * lens to an image
 * 
 * @author Shai&Avishay
 *
 */
public class Render {

	ImageWriter imageWriter;
	Scene scene;
	RayTracerBase rayTracer;
	Camera cam;

	/**
	 * Builder pattern Setter
	 * 
	 * @param cam the Camera to set
	 */
	public Render setCam(Camera cam) {
		this.cam = cam;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param scene the scene to set
	 */
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param rayTracer the rayTracer to set
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	/**
	 * The function scans the scene and sends the scan data to the image Writer
	 */
	public void renderImage() {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter field is empty", "ImageWriter", "imageWriter");
		if (scene == null)
			throw new MissingResourceException("scene field is empty", "Scene", "scene");
		if (rayTracer == null)
			throw new MissingResourceException("rayTracer field is empty", "RayTracerBase", "rayTracer");
		if (cam == null)
			throw new MissingResourceException("cam field is empty", "Camera", "cam");

		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();

		for (int i = 0; i < nX; i++) {
			for (int j = 0; j < nY; j++) {
				imageWriter.writePixel(j, i, rayTracer.traceRay(cam.constructRayThroughPixel(nX, nY, j, i)));

			}
		}
	}

	/**
	 * The function sends the image writer lines (boundaries) 
	 * in color and spacing that receiveded
	 * lengthwise and widthwise of the image
	 * 
	 * @param interval The interval between the boundaries
	 * @param color    The color of the boundaries
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter field is empty", "ImageWriter", "imageWriter");

		for (int i = interval; i < imageWriter.getNx() - 1; i += interval)
			for (int j = 0; j < imageWriter.getNy(); j++)
				imageWriter.writePixel(j, i, color);
		for (int j = interval; j < imageWriter.getNy() - 1; j += interval)
			for (int i = 0; i < imageWriter.getNx(); i++)
				imageWriter.writePixel(j, i, color);

	}

	/**
	 * The function calls the image writer to produce the image that given to it
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter field is empty", "ImageWriter", "imageWriter");
		imageWriter.writeToImage();
	}

}
