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
		
		
		double[] headsCords =ray.getHeadCoordinates();//ray's head's coordinates
		double[] dirPCords = ray.getDirCoordinates();//ray's direction vector's head's coordinates
		
		//The loop checks for each dimension whether the ray is parallel to the box,
		//and if so, whether within the box range.
		for (int i = 0; i < 3; i++) {
			if (dirPCords[i] == 0)
				if (mins[i] > headsCords[i] || maxes[i] < headsCords[i])
					return false;
		}
		
		//The scalars that represent the doubling range that will bring the fund to the intersection points
		double tStart = Double.NEGATIVE_INFINITY, tEnd = Double.POSITIVE_INFINITY;

		//The loop finds out of all the dimensions 
		//maximum initial scalar and the minimum final scalar
		for (int i = 0; i < 3; i++) {
			if (dirPCords[i] != 0) {
				double t1 = (mins[i] - headsCords[i]) / dirPCords[i];//The beginning of the range
				double t2 = (maxes[i] - headsCords[i]) / dirPCords[i];//End of range
				
				//In case the ray starts after the box
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

		//If the beginning of the range is beyond the end of the range there is no common intersection range 
		if (tStart > tEnd)
			return false;
		//the ray's direction is reversed from the box
		if (tEnd < 0)
			return false;

		return true;
	}
}