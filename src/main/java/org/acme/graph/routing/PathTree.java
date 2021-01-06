package org.acme.graph.routing;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Path;
import org.acme.graph.model.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class PathTree {
    private static final Logger log = LogManager.getLogger(PathTree.class);

    private Map<Vertex, PathNode> nodes;

    public PathTree(Graph graph, Vertex source) {
        this.nodes = new HashMap<>();
        log.trace("PathTree({})", source);
        for (Vertex vertex : graph.getVertices()) {
            PathNode node = new PathNode();
            node.setCost(source == vertex ? 0.0 : Double.POSITIVE_INFINITY);
            node.setReachingEdge(null);
            node.setVisited(false);
            this.nodes.put(vertex, node);
        }
    }

    protected Path getPath(Vertex target) {
        Path result = new Path();

        Edge current = this.getNode(target).getReachingEdge();
        do {
            result.getEdgeList().add(current);
            current = this.getNode(current.getSource()).getReachingEdge();
        } while (current != null);

        result.reversePath();
        return result;
    }

    public PathNode getNode(Vertex vertex) {
        return this.nodes.get(vertex);
    }
}
