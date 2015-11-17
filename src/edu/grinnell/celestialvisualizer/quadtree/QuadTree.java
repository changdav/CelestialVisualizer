package edu.grinnell.celestialvisualizer.quadtree;
import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class QuadTree {
	private Node root;

	public QuadTree() {}

	public static QuadTree makeTreeWithLeaf() {
		QuadTree ret = new QuadTree();
		ret.root = new LeafNode(1.0, new Point (2.0, 3.0));
		return ret;
	}

	public static QuadTree makeTreeWithEmpty() {
		QuadTree ret = new QuadTree();
		ret.root = new EmptyNode();
		return ret;
	}

	public Node getRoot() { 
		return root;
	}

	public boolean lookup(Point pos, BoundingBox bb) {
		return root.lookup(pos, bb);
	}

	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double eps) {
		return root.calculateAcceleration(p, bb, eps);
	}

	public void insert(double mass, Point pos, BoundingBox bb) {
		root = root.insert(mass, pos, bb);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof QuadTree) {
			return true;
		} else {
			return false;
		}
	}

	public static QuadTree q0() {
		QuadTree ret = new QuadTree();
		ret.root = new EmptyNode();
		return ret;
	}

	public static QuadTree q1() {
		QuadTree ret = new QuadTree();
		ret.root = new LeafNode(1.0, new Point(1.5, 2.5));
		return ret;
	}

	public static QuadTree q2() {
		QuadTree ret = new QuadTree();
		Centroid cent1 = new Centroid(1.0, new Point(1.5, 2.5));
		Centroid cent2 = new Centroid(1.0, new Point(2.1, 2.1));
		ret.root = new CentroidNode(cent1.add(cent2), 
				new EmptyNode(), 
				new EmptyNode(), 
				new LeafNode(1.0, new Point(1.5, 2.5)), 
				new LeafNode(1.0, new Point(2.1, 2.1)));
		return ret;
	}

	public static QuadTree q3() {
		QuadTree ret = new QuadTree();
		Centroid cent1 = new Centroid(1.0, new Point(1.5, 2.5));
		Centroid cent2 = new Centroid(1.0, new Point(2.1, 2.1));
		Centroid cent3 = new Centroid(2.0, new Point(1.0, 1.0));
		ret.root = new CentroidNode(cent1.add(cent2).add(cent3), 
				new LeafNode(2.0, new Point(1.0, 1.0)), 
				new EmptyNode(), 
				new LeafNode(1.0, new Point(1.5, 2.5)), 
				new LeafNode(1.0, new Point(2.1, 2.1)));
		return ret;
	}

	public static final QuadTree q4() {
		QuadTree ret = new QuadTree();
		Centroid cent1 = new Centroid(1.0, new Point(1.5, 2.5));
		Centroid cent2 = new Centroid(1.0, new Point(2.1, 2.1));
		Centroid cent3 = new Centroid(2.0, new Point(1.0, 1.0));
		Centroid cent4 = new Centroid(1.0, new Point(2.6, 2.8));
		Centroid c1 = cent2.add(cent4);
		Centroid c2 = c1.add(cent3).add(cent1);
		ret.root = new CentroidNode(c2,
				new LeafNode(2.0, new Point(1.0, 1.0)),      // level 1—upper left
				new EmptyNode(),                             // level 1—upper right
				new LeafNode(1.0, new Point(1.5, 2.5)),      // level 1—lower left
				new CentroidNode(c1,                         // level 1—lower right
						new CentroidNode(c1,                       //   level 2—upper left
								new LeafNode(1.0, new Point(2.1, 2.1)),  //     level 3—upper left
								new EmptyNode(),                         //     level 3—upper right
								new EmptyNode(),                         //     level 3—lower left
								new LeafNode(1.0, new Point(2.6, 2.8))   //     level 3—lower right
								),
						new EmptyNode(),                           //   level 2—upper right
						new EmptyNode(),                           //   level 2—lower left
						new EmptyNode()                            //   level 2—lower right
						)
				);
		return ret;
	}
}
