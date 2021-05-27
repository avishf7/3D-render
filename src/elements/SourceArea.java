/**
 * 
 */
package elements;

import java.util.List;

import primitives.Point3D;
import primitives.Vector;

/**
 * Interface SourceArea is an interface that represents a color source in a specific size and area in a scene
 * 
 * @author Shai&Avishay
 *
 */
public interface SourceArea {
	/**
	 *The function calculates the beams vectors 
	 *from points in the target area to the received point
	 * 
	 * @param p the point
	 * @return list of beams vectors coming from the target area to the received point
	 */
	List<Vector> getLs(Point3D p);
}
