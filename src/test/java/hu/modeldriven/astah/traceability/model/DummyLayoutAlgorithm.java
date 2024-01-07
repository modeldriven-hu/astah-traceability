package hu.modeldriven.astah.traceability.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DummyLayoutAlgorithm implements LayoutAlgorithm {

    @Override
    public Layout layout(Node rootNode) {

        FlattenedGraph flattenedGraph = flattenGraph(rootNode);
        Coordinates coordinates = calculateCoordinates(flattenedGraph);

        return new DummyLayout(coordinates.nodePositions, coordinates.connectionPositions);
    }

    private FlattenedGraph flattenGraph(Node rootNode) {
        FlattenedGraph graph = new FlattenedGraph();
        flattenGraph(rootNode, graph);
        return graph;
    }

    private void flattenGraph(Node currentNode, FlattenedGraph content) {
        if (!content.nodes.contains(currentNode)) {
            content.nodes.add(currentNode);

            for (Connection connection : currentNode.connections()) {
                content.connections.add(connection);
                flattenGraph(connection.target(), content);
            }
        }
    }

    private Coordinates calculateCoordinates(FlattenedGraph flattenedGraph) {
        Coordinates coordinates = new Coordinates();

        int width = 100;
        int height = 50;

        int x = 0;
        int y = 0;

        for (Node node : flattenedGraph.nodes) {
            coordinates.nodePositions.put(node.id(), new Rectangle(x, y, width, height));
            x += width + 50;
            y += height + 50;
        }

        for (Connection connection : flattenedGraph.connections) {
            Rectangle sourceRect = coordinates.nodePositions.get(connection.source().id());
            Rectangle targetRect = coordinates.nodePositions.get(connection.target().id());

            Point sourcePoint = new Point(sourceRect.x + sourceRect.width, sourceRect.y + sourceRect.height);
            Point targetPoint = new Point(targetRect.x, targetRect.y);

            Path path = new DummyPath(sourcePoint, targetPoint);
            coordinates.connectionPositions.put(connection.id(), path);
        }

        return coordinates;
    }

    class Coordinates {
        Map<ElementId, Rectangle> nodePositions = new HashMap<>();
        Map<ElementId, Path> connectionPositions = new HashMap<>();
    }

    class FlattenedGraph {
        Set<Node> nodes = new LinkedHashSet<>();
        Set<Connection> connections = new LinkedHashSet<>();
    }

}
