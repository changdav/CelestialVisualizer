package edu.grinnell.celestialvisualizer.quadtree;

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
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.lookup");
	}

	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double eps) {
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.calculateAcceleration");
	}

	public void insert(double mass, Point pos, BoundingBox bb) {
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.insert");
	}

	@Override
	public boolean equals(Object other) {
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.equals");
	}

	public static QuadTree q0() {
		QuadTree ret = new QuadTree();
		ret.root = new EmptyNode();
		return ret;
	}

	public static QuadTree q1() {
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.q1");
	}

	public static QuadTree q2() {
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.q2");
	}

	public static QuadTree q3() {
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.q3");
	}

	public static final QuadTree q4() {
		throw new edu.grinnell.celestialvisualizer.UnimplementedException("QuadTree.q4");
	}
}
