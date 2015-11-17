package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
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
		// TODO Auto-generated method stub
		return null;
	}

}
