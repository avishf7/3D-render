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
public class AmbientLight {

	/**
	 * The color of the ambient lighting
	 */
	Color intensity;

	/**
	 * AmbientLight constructor receiving {@link Color} and {@link double}.
	 * @param IA The color of the ambient lighting
	 * @param KA Coefficient of intensity of lighting
	 */
	public AmbientLight(Color IA,double KA){
		intensity=IA.scale(KA);
	 }
	
	/**
	 * Getter 
	 * @return intensity field
	 */
	public Color getIntensity() {
		return intensity;
	}
}
