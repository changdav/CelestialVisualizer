package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;
/**
 * The leaf node consists of a single body (represented by a mass and position).
 */
public class LeafNode implements Node{
	private double mass;
	private Point position;

	public LeafNode (double mass, Point position){
		this.mass = mass;
		this.position = position;
	}

	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return bb.contains(position) && position.equals(pos);
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
		if (p.equals(position)){
			return new LeafNode (this.mass + mass, p);
		} else {
			Centroid b1 = new Centroid(0, new Point(0,0));
			CentroidNode cNode = new CentroidNode(b1, new EmptyNode(), new EmptyNode(), new EmptyNode(), new EmptyNode());
			cNode.insert(mass, p, bb);
			cNode.insert(this.mass, this.position, bb);
			return cNode;
		}
	}
}
