package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class EmptyNode implements Node{

	public EmptyNode() {}

	//done
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return false;
	}

	//done
	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		return Vector2d.zero;
	}
	
	//done
	@Override
	public boolean equals(Object other) {
		if (other instanceof EmptyNode) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		// TODO Auto-generated method stub
		return null;
	}

}
