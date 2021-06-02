/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
	public void setBox() {
		for (Intersectable sh : shapes) {
			sh.setBox();
		}

		
		double minX = shapes.get(0).box.mins[0], minY = shapes.get(0).box.mins[1], minZ = shapes.get(0).box.mins[2],
				maxX = shapes.get(0).box.maxes[0], maxY = shapes.get(0).box.maxes[1], maxZ = shapes.get(0).box.maxes[2];
		for (Intersectable shape : shapes) {
			double minShapeX = shape.box.mins[0];
			double minShapeY = shape.box.mins[1];
			double minShapeZ = shape.box.mins[2];
			if (minX > minShapeX)
				minX = minShapeX;
			if (minY > minShapeY)
				minY = minShapeY;
			if (minZ > minShapeZ)
				minZ = minShapeZ;

			double maxShapeX = shape.box.maxes[0];
			double maxShapeY = shape.box.maxes[1];
			double maxShapeZ = shape.box.maxes[2];
			if (maxX < maxShapeX)
				maxX = maxShapeX;
			if (maxY < maxShapeY)
				maxY = maxShapeY;
			if (maxZ < maxShapeZ)
				maxZ = maxShapeZ;

		}
		this.box = new WrapBox(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance, boolean isAccelerated) {
		if (shapes.size() != 0 && isAccelerated) {
			if(this.box == null) 
				this.setBox();
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
