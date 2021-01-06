package org.acme.graph.routing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.acme.graph.model.Edge;

public class PathNode {
    private double cost;
    private Edge reachingEdge;
    private Boolean visited;


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @JsonIgnore
    public Edge getReachingEdge() {
        return reachingEdge;
    }

    public void setReachingEdge(Edge reachingEdge) {
        this.reachingEdge = reachingEdge;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }
}
