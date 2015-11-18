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
		return Physics.calculateAccelerationOn(p, mass, position);
	}

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
		} else if (p.equals(position)){
			return new LeafNode (this.mass + mass, p);
		} else {
			Centroid b1 = new Centroid(this.mass, this.position);
			Centroid b2 = new Centroid(mass, p);
			Centroid b3 = b1.add(b2);
			CentroidNode cNode = new CentroidNode(b3, new EmptyNode(), new EmptyNode(), new EmptyNode(), new EmptyNode());
			cNode.insert(mass, p, bb);
			cNode.insert(this.mass, position, bb);
			return cNode;
		}
	}
}
