/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class AmbientLight is a class that represents the Ambient light of a scene
 * 
 * @author Shai&Avishay
 *
 */
public class AmbientLight extends Light {

	/**
	 * CTOR
	 */
	public AmbientLight() {
		super(Color.BLACK);
	}
	
	/**
	 * AmbientLight constructor receiving {@link Color} and {@link double}.
	 * @param IA The color of the ambient lighting
	 * @param KA Coefficient of intensity of lighting
	 */
	public AmbientLight(Color IA,double KA){
		super(IA.scale(KA));
	 }
}
