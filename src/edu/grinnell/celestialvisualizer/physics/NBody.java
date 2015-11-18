package edu.grinnell.celestialvisualizer.physics;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.grinnell.celestialvisualizer.NBodyExamples;
import edu.grinnell.celestialvisualizer.quadtree.QuadTree;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Vector2d;

/**
 * NBody represents a particular N-body simulation.  It keeps tracks of the
 * bodies in the simulation and provides methods for updating those
 * bodies as the simulation progresses.
 */
public class NBody {
	private List<Body> bodies;

	/** Constructs a new NBody simulation with the provided bodies. */
	public NBody(List<Body> bodies) {
		this.bodies = bodies;
	}

	/** Constructs a new NBody simulation with no bodies.  This constructor
	 *  is a convenience to be used in conjunction with the add(b) method. */
	public NBody() { this(new LinkedList<>()); }

	/**
	 * Adds the given body to the simulation.
	 * @param b the body to add.
	 * @return this NBody simulation to be used in further calls to add.
	 */
	public NBody add(Body b) {
		bodies.add(b);
		return this;
	}

	/** @return the bodies this simulation managements */
	public List<Body> getBodies() { return bodies; }


	/**
	 * Calculates the accelerations for each body by all the bodies in the
	 * simulation.  The returned list should be the same size as the number
	 * of bodies where the ith element in the list contains the calculated
	 * acceleration for the ith body in the simulation.
	 * @param elapsedTime the time step of the simulation
	 * @return the list of accelerations
	 */
	public List<Vector2d> calculateAccelerations(double elapsedTime) {
		Stream<Body> stream1 = bodies.parallelStream();
		return stream1.map(s -> s.calculateAcceleration(bodies))
				.collect(Collectors.toList());
	}

	/**
	 * Updates this simulation, updating each of the bodies in the process.
	 * @param elapsedTime the time step of the simulation.
	 */
	public void update(double elapsedTime) {
		for (int i = 0; i < this.bodies.size(); i++) {
			this.bodies.get(i).update(elapsedTime, this.bodies.get(i).calculateAcceleration(getBodies()));
		}
	}

	/**
	 * Calculates the accelerations according to the given quad tree.
	 * @param qtree the quad tree used to calculate the accelerations
	 * @param bb the bounding box around the entire model
	 * @param elapsedTime the time step of the simulation
	 * @return a list of the calculated accelerations
	 */
	public List<Vector2d> calculateAccelerationsByQuadTree(QuadTree qtree, BoundingBox bb, double elapsedTime) {
		List<Vector2d> accelList = new LinkedList<Vector2d>();
		for (int i = 0; i < this.bodies.size(); i++) {
			accelList.add(qtree.calculateAcceleration(this.bodies.get(i).getPosition(), bb, 1000000.0));
		}
		return accelList;
	}

	/**
	 * Updates this simulation using a quad tree, updating each of the bodies
	 * in the process.
	 * @param elapsedTime the time step of the simulation.
	 */
	public void updateWithQuadTree(double elapsedTime) {
		QuadTree qtree = new QuadTree();
		for (Body planet : bodies) {
			qtree.insert(planet.getMass(), planet.getPosition(), NBodyExamples.WORLD_BOX);
		}
		
		List<Vector2d> accelList = calculateAccelerationsByQuadTree(qtree, NBodyExamples.WORLD_BOX, elapsedTime);
		for (Body planet : bodies) {
			planet.update(elapsedTime, accelList.remove(0));
		}
	}
}
