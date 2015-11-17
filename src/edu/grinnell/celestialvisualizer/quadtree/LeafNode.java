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

	//	public Node determineQuad(double mass, Point p, BoundingBox bb) {
	//		if (bb.quadrantOf(position) == Quadrant.UPPER_LEFT && bb.quadrantOf(p) == Quadrant.UPPER_RIGHT) {
	//			Centroid b1 = new Centroid(this.mass, this.position);
	//			Centroid b2 = new Centroid(mass, p);
	//			return new CentroidNode(b1.add(b2),
	//					new LeafNode(this.mass, this.position),
	//					new )
	//		}
	//		
	//		return new 
	//	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		if (!bb.contains(p)){
			throw new UnsupportedOperationException();
		} else if (p.getX() == this.position.getX() && p.getY() == this.position.getY()){
			return new LeafNode (this.mass + mass, p);
		} else {
			return new CentroidNode()
		}




		if (bb.quadrantOf(p).equals(bb.quadrantOf(position))){
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

			return new CentroidNode(cent2.add(cent1), upperLeft, upperRight, lowerLeft, lowerRight);
		}
	}

}
