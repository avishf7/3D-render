/**
 * 
 */
package renderer;

import static org.junit.Assert.assertThrows;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import scene.Scene;

/**
 * @author Shai&Avishay
 *
 */
public class Render {

	ImageWriter imageWriter;
	Scene scene;
	RayTracerBase rayTracer;
	Camera cam;

	/**
	 * @param cam the Camera to set
	 */
	public Render setCam(Camera cam) {
		this.cam = cam;
		return this;
	}

	/**
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param scene the scene to set
	 */
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}

	/**
	 * @param rayTracer the rayTracer to set
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	public void renderImage() {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter field is empty", "ImageWriter", "imageWriter");
		if (scene == null)
			throw new MissingResourceException("scene field is empty", "Scene", "scene");
		if (rayTracer == null)
			throw new MissingResourceException("rayTracer field is empty", "RayTracerBase", "rayTracer");
		if (cam == null)
			throw new MissingResourceException("cam field is empty", "Camera", "cam");
		for(int i=0;i<imageWriter.getNx();i++)
			for(int j=0;j<imageWriter.getNy();j++) {
				imageWriter.writePixel(j, i, rayTracer.traceRay(cam.constructRayThroughPixel(imageWriter.getNx(),imageWriter.getNy(),j,i)));
				
			}
	}

	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter field is empty", "ImageWriter", "imageWriter");
		for(int i=interval;i<imageWriter.getNx()-1;i+=interval)
			for(int j=0;j<imageWriter.getNy();j++) 
				imageWriter.writePixel(j, i,color);
		for(int j=interval;j<imageWriter.getNy()-1;j+=interval)
			for(int i=0;i<imageWriter.getNx();i++) 
				imageWriter.writePixel(j, i,color);
			
	}

	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter field is empty", "ImageWriter", "imageWriter");
		imageWriter.writeToImage();
	}

}


