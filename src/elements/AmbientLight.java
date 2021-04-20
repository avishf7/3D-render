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

	AmbientLight(Color IA,double KA){
		intensity=IA.scale(KA);
	 }
	
	Color getIntensity() {
		return intensity;
	}
}
