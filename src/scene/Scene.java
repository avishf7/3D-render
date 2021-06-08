/**
 * 
 */
package scene;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

/**
 * The class scene is a representative class for a scene that includes shapes,
 * colors, and lighting
 * 
 * @author Shai&Avishay
 *
 */
public class Scene {

	
	/**
	 * CTOR
	 * 
	 * @param name The name of the scene
	 */
	public Scene(String name) {
		this.name = name;
		geometries = new Geometries();
	}
	
	/**
	 * CTOR
	 * 
	 * @param name The name of the scene
	 *  @param isToOrder
	 */
	public Scene(String name,boolean isToOrder) {
		this.name = name;
		geometries = new Geometries().setToOrder(isToOrder);
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param background the background to set
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param ambientLight the ambientLight to set
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * @param lights the lights to set
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}

	/**
	 * Builder pattern Setter
	 * 
	 * @param geometries the geometries to set
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}

	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0);
	public Geometries geometries;
	public List<LightSource> lights = new LinkedList<LightSource>();


}
