/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author Shai&Avishay
 *
 */
public class AmbientLight {

	Color intensity;

	public AmbientLight(Color IA,double KA){
		intensity=IA.scale(KA);
	 }
	
	public Color getIntensity() {
		return intensity;
	}
}
