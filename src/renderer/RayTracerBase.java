/**
 * 
 */
package renderer;

import primitives.*;
import scene.Scene;

/**
 * RayTracerBase is an abstract class representing a type of ray scanner
 * 
 * @author Shai&Avishay
 *
 */
public abstract class RayTracerBase {
	/**
	 * The photographed scene
	 */
	protected Scene scene;
	

	/**
	 * RayTracerBase constructor receiving {@link scene}.
	 * 
	 * @param scene the photographed scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	

	/**
	 * 
	 */
	public RayTracerBase accelerate() {
			scene.geometries.buildBox();
		return this;
	}



	/**
	 * The function checks what color the ray coming out towards the scene meets
	 * @param ray
	 * @return Color The color of the points that the ray meets 
	 */
	public abstract Color traceRay(Ray ray);

}
