/**
 * 
 */
package elements;

import java.util.List;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public interface TargetArea {
	/**
	 *The function returns a beam of rays from the target area to the point
	 * 
	 * @return list of light beams coming from the target area to the point
	 */
	List<Vector> getLs(Point3D p);
}
