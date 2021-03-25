package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * Class Triangle is the class representing a triangle in the Three-dimensional
 * space.
 * 
 * @author Shai&Avishay
 */
public class Triangle extends Polygon {
	/**
	 * Triangle constructor receiving the The vertices of the triangle.
	 * @param p1 The first vertex of the triangle
	 * @param p2 The second vertex of the triangle
	 * @param p3 The third vertex of the triangle
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);//call to the polygon cto'r
	}

	
	

}
