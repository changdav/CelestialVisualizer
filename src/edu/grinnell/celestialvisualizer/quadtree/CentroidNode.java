package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.util.Quadrant;
import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

/**
 * A centroid node consists of a centroid and four sub-quad trees for the four possible sub-divisions of the space: 
 * upper left, upper right, lower left, and lower right.
 *
 */
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

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		double distance = p.distance(this.cent.getPosition()).magnitude();
		if (bb.contains(p) || distance < thresh) {
			Vector2d accel = Vector2d.zero;
			
			accel = accel.add(upperLeft.calculateAcceleration(p, bb.getQuadrant(Quadrant.UPPER_LEFT), thresh));
			accel = accel.add(upperRight.calculateAcceleration(p, bb.getQuadrant(Quadrant.UPPER_RIGHT), thresh));
			accel = accel.add(lowerLeft.calculateAcceleration(p, bb.getQuadrant(Quadrant.LOWER_LEFT), thresh));
			accel = accel.add(lowerRight.calculateAcceleration(p, bb.getQuadrant(Quadrant.LOWER_RIGHT), thresh));
			
			return accel;
			
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

		this.cent = cent.add(new Centroid(mass, p));
		
		if (bb.quadrantOf(p).equals(Quadrant.UPPER_LEFT)) {
			BoundingBox bb1 = bb.getQuadrant(Quadrant.UPPER_LEFT);
			upperLeft = upperLeft.insert(mass, p, bb1);
			return this;
		} else if (bb.quadrantOf(p).equals(Quadrant.UPPER_RIGHT)) {
			BoundingBox bb1 = bb.getQuadrant(Quadrant.UPPER_RIGHT);
			upperRight = upperRight.insert(mass, p, bb1);
			return this;
		} else if (bb.quadrantOf(p).equals(Quadrant.LOWER_LEFT)) {
			BoundingBox bb1 = bb.getQuadrant(Quadrant.LOWER_LEFT);
			lowerLeft = lowerLeft.insert(mass, p, bb1);
			return this;
		} else {
			BoundingBox bb1 = bb.getQuadrant(Quadrant.LOWER_RIGHT);
			lowerRight = lowerRight.insert(mass, p, bb1);
			return this;
		}
	}

}
