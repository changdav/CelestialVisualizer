package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class EmptyNode implements Node{
	
	
	//  Constructor
	//	public EmptyNode {
	//		
	//	}
	
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return false;
	}

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
