/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author User
 *
 */
abstract class Light {
	/**
	 * @param intensity
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	private Color intensity;

	/**
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
