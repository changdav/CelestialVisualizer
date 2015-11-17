package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.util.Quadrant;
import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class CentroidNode implements Node{
	private Centroid cent;
	private Node q0;
	private Node q1;
	private Node q2;
	private Node q3;

	public CentroidNode (Centroid cent, Node q0, Node q1, Node q2, Node q3){
		this.cent = cent;
		this.q0 = q0;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
	}

	//done
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		switch (bb.quadrantOf(pos)) {
		case UPPER_LEFT: return this.lookup(pos, bb.getQuadrant(Quadrant.UPPER_LEFT));
		case UPPER_RIGHT: return this.lookup(pos, bb.getQuadrant(Quadrant.UPPER_RIGHT));
		case LOWER_LEFT: return this.lookup(pos, bb.getQuadrant(Quadrant.LOWER_LEFT));
		case LOWER_RIGHT: return this.lookup(pos, bb.getQuadrant(Quadrant.LOWER_RIGHT));
		default: return false;
		}
	}

	//done
	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		if 
	}

    public boolean equals(Object other) {
    	
    }
	
	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		// TODO Auto-generated method stub
		return null;
	}

}
