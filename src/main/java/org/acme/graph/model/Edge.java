package org.acme.graph.model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

/**
 * Un arc matérialisé par un sommet source et un sommet cible
 *
 * @author MBorne
 */
public class Edge {
	/**
	 * Identifiant de l'arc
	 */
	private String id;

	/**
	 * Sommet initial
	 */
	private Vertex source;

	/**
	 * Sommet final
	 */
	private Vertex target;

	public Edge(Vertex source, Vertex target) {
		this.source = source;
		this.source.getOutEdges().add(this);

		this.target = target;
		this.target.getInEdges().add(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIdentityInfo(
			generator = ObjectIdGenerators.PropertyGenerator.class,
			property = "id"
	)
	@JsonIdentityReference(alwaysAsId = true)
	public Vertex getSource() {
		return source;
	}

	@JsonIdentityInfo(
			generator = ObjectIdGenerators.PropertyGenerator.class,
			property = "id"
	)
	@JsonIdentityReference(alwaysAsId = true)
	public Vertex getTarget() {
		return target;
	}

	@JsonSerialize(using = GeometrySerializer.class)
	public LineString getGeometry() {
		Coordinate[] coordinates = {this.source.getCoordinate(), this.target.getCoordinate()};
		CoordinateSequence cs = new CoordinateArraySequence(coordinates);
		return new LineString(cs, new GeometryFactory());
	}

	/**
	 * dijkstra - coût de parcours de l'arc (distance géométrique)
	 *
	 * @return
	 */
	public double getCost() {
		return source.getCoordinate().distance(target.getCoordinate());
	}

	@Override
	public String toString() {
		return id + " (" + source + "->" + target + ")";
	}

}
