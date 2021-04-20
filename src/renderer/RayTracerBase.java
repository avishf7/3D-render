/**
 * 
 */
package renderer;

import primitives.*;
import scene.Scene;

/**
 * @author Shai&Avishay
 *
 */
public abstract class RayTracerBase {
	protected Scene scene;

	/**
	 * @param scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	public abstract Color traceRay(Ray ray);
	

}
