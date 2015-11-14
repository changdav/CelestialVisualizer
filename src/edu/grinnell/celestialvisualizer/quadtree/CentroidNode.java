package edu.grinnell.celestialvisualizer.quadtree;

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

	/**
	 * This method returns false as a CentroidNode does not contain any points.
	 */
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return false;
	}

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		// TODO Auto-generated method stub
		return null;
	}

}
