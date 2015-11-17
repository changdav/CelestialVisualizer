package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Quadrant;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class LeafNode implements Node{
	private double mass;
	private Point position;

	public LeafNode (double mass, Point position){
		this.mass = mass;
		this.position = position;
	}

	//done
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		if (bb.contains(position) && position.equals(pos)) {
			return true;
		} else {
			return false;
		}
	}

	//done
	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		return Physics.calculateAccelerationOn(p, mass, position);
	}

	//done
	@Override
	public boolean equals(Object other) {
		if (other instanceof LeafNode) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		if (!bb.contains(p)){
			throw new UnsupportedOperationException();
		} else if (p.getX() == this.position.getX() && p.getY() == this.position.getY()){
			return new LeafNode (this.mass + mass, p);
		} else if (bb.quadrantOf(p).equals(bb.quadrantOf(position))){
			return insert (mass, p, bb.getQuadrant(bb.quadrantOf(p)));
		} else {
			Centroid cent1 = new Centroid (this.mass, this.position);
			Centroid cent2 = new Centroid (mass, p);
			
			Node upperLeft = new EmptyNode();
			Node upperRight= new EmptyNode();
			Node lowerLeft = new EmptyNode();
			Node lowerRight= new EmptyNode();
			
			if (bb.getQuadrant(bb.quadrantOf(p)) == bb.getQuadrant(Quadrant.UPPER_LEFT)){
				LeafNode ret = new LeafNode (mass, p);
				upperLeft = ret;
			} else if (bb.getQuadrant(bb.quadrantOf(position)) == bb.getQuadrant(Quadrant.UPPER_LEFT)){
				LeafNode ret = new LeafNode (this.mass, position);
				upperLeft = ret;
			} else if (bb.getQuadrant(bb.quadrantOf(p)) == bb.getQuadrant(Quadrant.UPPER_RIGHT)){
				LeafNode ret = new LeafNode (mass, p);
				upperRight = ret;
			} else if (bb.getQuadrant(bb.quadrantOf(position)) == bb.getQuadrant(Quadrant.UPPER_RIGHT)){
				LeafNode ret = new LeafNode (this.mass, position);
				upperRight= ret;
			} else if (bb.getQuadrant(bb.quadrantOf(p)) == bb.getQuadrant(Quadrant.LOWER_LEFT)){
				LeafNode ret = new LeafNode (mass, p);
				lowerLeft = ret;
			} else if (bb.getQuadrant(bb.quadrantOf(position)) == bb.getQuadrant(Quadrant.LOWER_LEFT)){
				LeafNode ret = new LeafNode (this.mass, position);
				lowerLeft = ret;
			} else if (bb.getQuadrant(bb.quadrantOf(p)) == bb.getQuadrant(Quadrant.LOWER_RIGHT)){
				LeafNode ret = new LeafNode (mass, p);
				lowerRight = ret;
			} else if (bb.getQuadrant(bb.quadrantOf(position)) == bb.getQuadrant(Quadrant.LOWER_RIGHT)){
				LeafNode ret = new LeafNode (this.mass, position);
				lowerRight = ret;
			}
			
//			switch (bb.quadrantOf(p)) {
//			case UPPER_LEFT: ;
//			case UPPER_RIGHT: return this.lookup(p, bb.getQuadrant(Quadrant.UPPER_RIGHT));
//			case LOWER_LEFT: return this.lookup(p, bb.getQuadrant(Quadrant.LOWER_LEFT));
//			case LOWER_RIGHT: return this.lookup(p, bb.getQuadrant(Quadrant.LOWER_RIGHT));
//			default: return false;
//			}
			return new CentroidNode(cent2.add(cent1), upperLeft, upperRight, lowerLeft, lowerRight);
		}
	}

}
