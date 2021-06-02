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
	 * @param minX
	 * @param minY
	 * @param minZ
	 * @param maxX
	 * @param maxY
	 * @param maxZ
	 */
	public WrapBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		double[] tempMins = { minX, minY, minZ };
		double[] tempMaxes = { maxX, maxY, maxZ };
		mins = tempMins;
		maxes = tempMaxes;
	}

	double[] mins;
	double[] maxes;

	protected boolean isIntersect(Ray ray) {
		Point3D head = ray.getP0();
		Point3D vecP = ray.getDir().getHead();
		double[] headsCords = { head.getX(), head.getY(), head.getZ() };
		double[] dirPCords = { vecP.getX(), vecP.getY(), vecP.getZ() };
		
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
