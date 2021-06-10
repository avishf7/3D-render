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
	 * A Boolean variable determines whether to reorder the shapes 
	 * in a more efficient way for scanning the rays
	 */
	boolean toOrder;
	
	/**
	 * CTOR
	 */ 
	public Geometries() {
		shapes = new LinkedList<Intersectable>();
	}

	/**
	 * CTOR
	 * 
	 * @param geometries the shapes
	 */
	public Geometries(Intersectable... geometries) {
		shapes = List.of(geometries);
	}

	/**
	 * The function add list of shapes to "this" shapes 
	 * @param geometries the shapes to add
	 */
	public void add(Intersectable... geometries) {
		shapes.addAll(List.of(geometries));
	}

	
	
	/**
	 * Builder pattern Setter
	 * @param toOrder the toOrder to set
	 */
	public Geometries setToOrder(boolean toOrder) {
		this.toOrder = toOrder;
		return this;
	}

	@Override
	public void buildBox() {
		//Build boxes for all shapes in geometries
		for (Intersectable sh : shapes) {
			sh.buildBox();
		}

		if (shapes.size() != 0)
			if(this.toOrder)
				buildTreeBox(0);
			else 
				buildMyBox();
	}

	/**
	 * A recursive function for building a tree that will contains all the shapes 
	 * in a more efficient arrangement for scanning rays.
	 * 
	 * @param axis The axis that determines the division into two collections inside the box.
	 */
	private void buildTreeBox(int axis) {

		buildMyBox();

		// conditions if there are 2 shapes or less in the collection
		if (shapes.size() <= 2)
			return;

		//List of sons of the current node
		List<Intersectable> treeShapes = new LinkedList<Intersectable>();
		
		//Two nodes that branch off from the current node in the box tree
		Geometries left = new Geometries();
		Geometries right = new Geometries();

		//division of the shapes by location
		for (Intersectable inter : shapes) {
			if (inter.getMid(axis) < getMid(axis))
				left.add(inter);
			else if (inter.getMid(axis) > getMid(axis))
				right.add(inter);
			else
				treeShapes.add(inter);
		}

		//The next axis that determines the division into two collections inside the box
		int nextAxis = (axis + 1) % 3;
		
		if (!left.isEmpty()) {
			left.buildTreeBox(nextAxis);
			treeShapes.add(left);
		}
		if (!right.isEmpty()) {
			right.buildTreeBox(nextAxis);
			treeShapes.add(right);
		}

		shapes = treeShapes;

	}

	/**
	 * Auxiliary function for building a box 
	 * that will wrap all shapes in "geometries"
	 */
	private void buildMyBox() {
		
		// ----Finds the minimum and maximum coordinate values between all the boxes of shapes in geometries----
		
		
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
		
		//--------------------------------------------------------------------------------------------------------
		
		this.box = new WrapBox(minX, minY, minZ, maxX, maxY, maxZ);
	}

	/**
	 * The function checks if there are no shapes
	 * @return true if there are no shapes ,otherwise returns false.
	 */
	public boolean isEmpty (){
		return this.shapes.size() == 0;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		//Checks if there is a intersect with the wrapping box 
		//(if there is no box continues to the normal intersect test)
		if (this.box != null && !this.box.isIntersect(ray))
			return null;

		
		List<GeoPoint> intsPoints = null, result;

		for (Intersectable sh : shapes) {
			result = sh.findGeoIntersections(ray, maxDistance);
			if (result != null)
				if (intsPoints == null)
					intsPoints = result;
				else
					(intsPoints).addAll(result);
		}

		return intsPoints;
	}

}