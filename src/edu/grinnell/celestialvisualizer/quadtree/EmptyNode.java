package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class EmptyNode implements Node{
	
	public EmptyNode() {}
	
	/**
	 * This method returns false as an EmptyNode does not contain anything.
	 */
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return false;
	}

	/**
	 * This method returns a Vector2d of (0,0) as an EmptyNode exerts no force.
	 */
	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		return Vector2d.zero;
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		// TODO Auto-generated method stub
		return null;
	}

}
