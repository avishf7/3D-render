/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Ray;

/**
 * Class Geometries is class for works with several geometric shapes.
 * 
 * @author Shai&Avishay
 *
 */

public class Geometries extends Intersectable {

	List<Intersectable> shapes;

	/**
	 * 
	 */
	public Geometries() {
		shapes = new LinkedList<Intersectable>();
	}

	public Geometries(Intersectable... geometries) {
		shapes = List.of(geometries);
	}

	public void add(Intersectable... geometries) {
		shapes.addAll(List.of(geometries));
	}

	@Override
	public void buildBox() {
		for (Intersectable sh : shapes) {
			sh.buildBox();
		}

		buildTreeBox(0);
	}

	private void buildTreeBox(int axis) {
		if (shapes.size() <= 2)
			return;
		double[] boxMins = shapes.get(0).getBoxMins();
		double[] boxMaxes = shapes.get(0).getBoxMaxes();
		double minX = boxMins[0], minY = boxMins[1], minZ = boxMins[2], maxX = boxMaxes[0], maxY = boxMaxes[1],
				maxZ = boxMaxes[2];
		for (Intersectable shape : shapes) {
			boxMins = shape.getBoxMins();
			boxMaxes = shape.getBoxMaxes();
			double minShapeX = boxMins[0];
			double minShapeY = boxMins[1];
			double minShapeZ = boxMins[2];
			if (minX > minShapeX)
				minX = minShapeX;
			if (minY > minShapeY)
				minY = minShapeY;
			if (minZ > minShapeZ)
				minZ = minShapeZ;

			double maxShapeX = boxMaxes[0];
			double maxShapeY = boxMaxes[1];
			double maxShapeZ = boxMaxes[2];
			if (maxX < maxShapeX)
				maxX = maxShapeX;
			if (maxY < maxShapeY)
				maxY = maxShapeY;
			if (maxZ < maxShapeZ)
				maxZ = maxShapeZ;

		}
		this.box = new WrapBox(minX, minY, minZ, maxX, maxY, maxZ);

		Geometries left = new Geometries();
		Geometries right = new Geometries();

		for (Intersectable inter : shapes) {
			if (inter.getMid(axis) < getMid(axis))
				left.add(inter);
			else
				right.add(inter);
		}

		int nextAxis = (axis + 1) % 3;
		if (left.shapes.size() > 0)
			left.buildTreeBox(nextAxis);
		if (right.shapes.size() > 0)
			right.buildTreeBox(nextAxis);
		shapes = List.of(left, right);
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance, boolean isAccelerated) {
		if (shapes.size() != 0 && isAccelerated) {
			if (this.box == null)
				this.buildBox();
			if (!this.box.isIntersect(ray))
				return null;
		}

		List<GeoPoint> intsPoints = null, result;

		for (Intersectable sh : shapes) {
			result = sh.findGeoIntersections(ray, maxDistance, isAccelerated);
			if (result != null)
				if (intsPoints == null)
					intsPoints = result;
				else
					(intsPoints).addAll(result);
		}

		return intsPoints;
	}

}
