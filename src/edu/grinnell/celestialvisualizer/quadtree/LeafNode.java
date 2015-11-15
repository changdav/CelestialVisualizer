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

	/**
	 * This method returns true when the given position is within bb and matches pos.
	 */
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		if (bb.contains(position) && position.equals(pos)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		Vector2d posDif = p.distance(position);
		if (posDif.magnitude() < thresh || bb.contains(p)) {
			return Physics.calculateAccelerationOn(p, mass, position);
		} else {
			return Vector2d.zero;
		}
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		// TODO Auto-generated method stub
		return null;
	}

}
