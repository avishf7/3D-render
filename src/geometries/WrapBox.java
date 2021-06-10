/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class WrapBox is a class for representing a wrap box for objects in the
 * Three-dimensional space For finding intersections
 * 
 * @author Shai&Avishay
 * 
 */
class WrapBox {

	/**
	 * CTOR
	 * @param minX The minimum x value of the box
	 * @param minY The minimum y value of the box
	 * @param minZ The minimum z value of the box
	 * @param maxX The maximum x value of the box
	 * @param maxY The maximum y value of the box
	 * @param maxZ The maximum z value of the box
	 */
	public WrapBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		double[] tempMins = { minX, minY, minZ };
		double[] tempMaxes = { maxX, maxY, maxZ };
		mins = tempMins;
		maxes = tempMaxes;
	}

	/**
	 * An array that stores 
	 * the minimum coordinate values of the box
	 */
	double[] mins;
	/**
	 * An array that stores 
	 * the maximum coordinate values of the box
	 */
	double[] maxes;

	/**
	 * The function checks if there is an intersection 
	 * between the received ray and the box
	 * @param ray A Ray in three-dimensional space
	 * @return True if there is an intersection, otherwise false
	 */
	protected boolean isIntersect(Ray ray) {
		
		double[] headsCords =ray.getHeadCoordinates();
		double[] dirPCords = ray.getDirCoordinates();
		
		for (int i = 0; i < 3; i++) {
			if (dirPCords[i] == 0)
				if (mins[i] > headsCords[i] || maxes[i] < headsCords[i])
					return false;
		}
		double tStart = Double.NEGATIVE_INFINITY, tEnd = Double.POSITIVE_INFINITY;

		for (int i = 0; i < 3; i++) {
			if (dirPCords[i] != 0) {
				double t1 = (mins[i] - headsCords[i]) / dirPCords[i];
				double t2 = (maxes[i] - headsCords[i]) / dirPCords[i];
				if (t1 > t2) {
					double temp = t1;
					t1 = t2;
					t2 = temp;
				}
				if (t1 > tStart)
					tStart = t1;
				if (t2 < tEnd)
					tEnd = t2;
			}
		}

		if (tStart > tEnd)
			return false;
		if (tEnd < 0)
			return false;

		return true;
	}
}