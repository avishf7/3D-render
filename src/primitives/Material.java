
package primitives;

/**
 * Class Material is class for hold the shininess factors of a geometry shape
 * 
 * 
 * @author Shai&Avishay
 *
 */
public class Material {
	/**
	 * the Diffusive coefficient
	 */
	public double kD = 0;
	/**
	 * the spectacular effect coefficient
	 */
	public double kS = 0;
	/**
	 * the Spectacular exponent
	 */
	public int nShininess = 0;

	/**
	 * transparency coefficient
	 */
	public double kT = 0;

	/**
	 * reflection coefficient
	 */
	public double kR = 0;

	/**
	 * 
	 * @param kD the kD to set
	 */
	public Material setKD(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * @param kT the kT to set
	 */
	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * @param kR the kR to set
	 */
	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setKS(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

}
