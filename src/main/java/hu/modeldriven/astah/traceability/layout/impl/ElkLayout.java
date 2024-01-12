package hu.modeldriven.astah.traceability.layout.impl;

import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Layout;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.Path;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElkLayout implements Layout {

    private final Map<Node, Rectangle2D> nodeRectangles;
    private final Map<Connection, Path> connectionPaths;

    private final Map<String, Node> nodes;
    private final Map<String, Connection> connections;

    private final Rectangle2D bounds = new Rectangle2D.Double();

    public ElkLayout(ElkNode rootElkNode, Node rootNode) {
        nodeRectangles = new HashMap<>();
        connectionPaths = new HashMap<>();
        nodes = new HashMap<>();
        connections = new HashMap<>();

        buildNodeAndConnectionCache(rootNode, nodes, connections);
        calculateFromRootNode(rootElkNode);
    }

    void buildNodeAndConnectionCache(Node rootNode, Map<String, Node> nodes, Map<String, Connection> connections) {
        TreeTraverseAlgorithm algorithm = new TreeTraverseAlgorithm();

        algorithm.traverse(rootNode, new TreeTraverseAlgorithm.TreeVisitor() {

            @Override
            public void visit(Node node) {
                nodes.put(node.id().value(), node);
            }

            @Override
            public void visit(Connection connection) {
                connections.put(connection.id().value(), connection);
            }
        });
    }

    private void calculateFromRootNode(ElkNode rootNode) {
        for (ElkNode child : rootNode.getChildren()) {
            calculateNodesAndPaths(child);
        }
    }

    private void calculateNodesAndPaths(ElkNode node) {

        if (nodeRectangles.containsKey(node.getIdentifier())) {
            return;
        }

        Rectangle2D rectangle = calculateRectangle(node);
        Node canvasNode = findNodeById(node.getIdentifier());

        nodeRectangles.put(canvasNode, rectangle);

        bounds.add(rectangle);

        for (ElkEdge edge : node.getOutgoingEdges()) {

            Path path = calculatePath(edge);
            Connection connection = findConnectionById(edge.getIdentifier());
            connectionPaths.put(connection, path);

            bounds.add(path.bounds());

            calculateNodesAndPaths((ElkNode) edge.getTargets().get(0));
        }
    }

    private Rectangle2D calculateRectangle(ElkNode node) {
        return new Rectangle2D.Double(
                node.getX(), node.getY(),
                node.getWidth(), node.getHeight());
    }

    private Path calculatePath(ElkEdge edge) {
        List<Point2D> points = new ArrayList<>();

        for (ElkEdgeSection section : edge.getSections()) {

            points.add(new Point2D.Double(
                    section.getStartX(),
                    section.getStartY()));

            for (ElkBendPoint bendPoint : section.getBendPoints()) {
                points.add(new Point2D.Double(
                        bendPoint.getX(),
                        bendPoint.getY()));
            }

            points.add(new Point2D.Double(
                    section.getEndX(),
                    section.getEndY()));
        }

        Rectangle2D labelBounds = edge.getLabels().stream()
                .findFirst()
                .map(elkLabel -> new Rectangle2D.Double(
                        elkLabel.getX(),
                        elkLabel.getY(),
                        elkLabel.getWidth(),
                        elkLabel.getHeight()))
                .orElse(null);

        return new DefaultPath(points, labelBounds);
    }

    private Node findNodeById(String id) {
        return nodes.get(id);
    }

    private Connection findConnectionById(String id) {
        return connections.get(id);
    }

    @Override
    public Rectangle2D location(Node node) {
        return nodeRectangles.get(node);
    }

    @Override
    public Path location(Connection connection) {
        return connectionPaths.get(connection);
    }

    @Override
    public Dimension size() {
        return new Dimension((int) bounds.getWidth(), (int) bounds.getHeight());
    }

    @Override
    public Node findNodeByLocation(Point2D point) {

        for (Map.Entry<Node, Rectangle2D> nodeEntry : nodeRectangles.entrySet()) {
            if (nodeEntry.getValue().contains(point)) {
                return nodeEntry.getKey();
            }
        }

        return null;
    }

    @Override
    public Connection findConnectionByLocation(Point2D point) {
        for (Map.Entry<Connection, Path> connectionEntry : connectionPaths.entrySet()) {
            if (connectionEntry.getValue().bounds().contains(point)) {
                return connectionEntry.getKey();
            }
        }

        return null;
    }

}
