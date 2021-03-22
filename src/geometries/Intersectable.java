package geometries;
 import java.util.List;

import primitives.*;


public interface Intersectable {
	List <Point3D> findIntsersections(Ray ray);
}
