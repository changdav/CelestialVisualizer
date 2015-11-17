package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.util.Quadrant;
import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class CentroidNode implements Node{
	private Centroid cent;
	private Node upperLeft;
	private Node upperRight;
	private Node lowerLeft;
	private Node lowerRight;

	public CentroidNode (Centroid cent, Node upperLeft, Node upperRight, Node lowerLeft, Node lowerRight){
		this.cent = cent;
		this.upperLeft = upperLeft;
		this.upperRight = upperRight;
		this.lowerLeft = lowerLeft;
		this.lowerRight = lowerRight;
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
		double distance = p.distance(this.cent.getPosition()).magnitude();
		if (this.lookup(p, bb) || distance < thresh) {
			return calculateAcceleration(p, bb.getQuadrant(Quadrant.UPPER_LEFT), thresh)
					.add(calculateAcceleration(p, bb.getQuadrant(Quadrant.UPPER_RIGHT), thresh))
							.add(calculateAcceleration(p, bb.getQuadrant(Quadrant.LOWER_LEFT), thresh))
									.add(calculateAcceleration(p, bb.getQuadrant(Quadrant.LOWER_RIGHT), thresh));
		} else {
			return Physics.calculateAccelerationOn(p, cent.getMass(), cent.getPosition());
		}
	}
	@Override
	public boolean equals(Object other) {
		if (other instanceof CentroidNode) {
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
