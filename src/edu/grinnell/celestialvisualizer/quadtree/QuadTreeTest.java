package edu.grinnell.celestialvisualizer.quadtree;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class QuadTreeTest {

	@Test
	public void testQuadTreeLookup() {
		BoundingBox bb = new BoundingBox(0.0, 0.0, 4.0, 4.0);
		Point pos = new Point(2.0, 3.0);
		QuadTree ret = QuadTree.makeTreeWithLeaf();
		assertEquals(true, ret.getRoot().lookup(pos, bb));
	}

	@Test
	public void testQuadTreeAccel() {
		Point p = new Point(1.0, 1.0);
		BoundingBox bb = new BoundingBox(0.0, 0.0, 4.0, 4.0);
		QuadTree qtree = QuadTree.makeTreeWithLeaf();
		System.out.println(qtree.calculateAcceleration(p, bb, 0.0));
		System.out.println(Physics.calculateAccelerationOn(p, 1.0, new Point(2, 3)));
		assertEquals(Vector2d.zero, qtree.calculateAcceleration(p, bb, 0.0));

		qtree = QuadTree.makeTreeWithEmpty();
		qtree.insert(10000000.0, new Point(2.0, 3.0), bb);
		p = new Point (2.0, 2.0);
		assertEquals(new Vector2d(0.0, 0.000667428), qtree.calculateAcceleration(p, bb, 0.0));
		}

	@Test
	public void testQuadTreeInsert() {
		BoundingBox bb = new BoundingBox(0.0, 0.0, 4.0, 4.0);

		QuadTree q = QuadTree.makeTreeWithEmpty();
		assertEquals(q, QuadTree.q0());

		q.insert(1.0, new Point(1.5, 2.5), bb);
		assertEquals(q, QuadTree.q1());

		q.insert(1.0, new Point(2.1, 2.1), bb);
		assertEquals(q, QuadTree.q2());

		q.insert(2.0, new Point(1.0, 1.0), bb);
		assertEquals(q, QuadTree.q3());

		q.insert(1.0, new Point(2.6, 2.8), bb);
		assertEquals(q, QuadTree.q4());
	}

}
