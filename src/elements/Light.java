package elements;

import primitives.Color;

/**
 * Class Light is a class that represents a light that affects on scene
 * 
 * @author Shai&Avishay
 *
 */
abstract class Light {
	/**
	 * CTOR
	 * @param intensity
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * The color of the light
	 */
	private Color intensity;

	/**
	 * Getter
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
