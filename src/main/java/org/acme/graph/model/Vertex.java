package org.acme.graph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Un sommet dans un graphe
 *
 * @author MBorne
 */
public class Vertex {

	/**
	 * Identifiant du sommet
	 */
	private String id;

	/**
	 * Position du sommet
	 */
	private Coordinate coordinate;

	@JsonIgnore
	private List<Edge> inEdges;

	@JsonIgnore
	private List<Edge> outEdges;

	public Vertex() {
		this.inEdges = new ArrayList<>();
		this.outEdges = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return id;
	}


	public Collection<Edge> getInEdges() {
		return inEdges;
	}

	public Collection<Edge> getOutEdges() {
		return outEdges;
	}
}
