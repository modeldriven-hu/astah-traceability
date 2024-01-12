package hu.modeldriven.astah.traceability.layout.impl.layout;

import hu.modeldriven.astah.traceability.layout.*;
import hu.modeldriven.astah.traceability.layout.impl.core.DefaultPath;
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

    private final Map<Node, Rectangle2D> nodeRectangles = new HashMap<>();
    private final Map<Connection, Path> connectionPaths = new HashMap<>();

    private final Rectangle2D bounds = new Rectangle2D.Double();

    public ElkLayout(ElkNode rootElkNode, Node rootNode) {
        TreeCache treeCache = new TreeCache(rootNode);
        calculateFromRootNode(rootElkNode, treeCache);
    }


    private void calculateFromRootNode(ElkNode rootNode, TreeCache treeCache) {
        for (ElkNode child : rootNode.getChildren()) {
            calculateNodesAndPaths(child, treeCache);
        }
    }

    private void calculateNodesAndPaths(ElkNode node, TreeCache treeCache) {

        // Skip if the node's rectangle is already calculated
        if (nodeRectangles.containsKey(node.getIdentifier())) {
            return;
        }

        // Calculate the rectangle for the current node
        Rectangle2D rectangle = calculateRectangle(node);

        // Find the corresponding canvas node
        Node canvasNode = treeCache.findNodeById(node.getIdentifier());

        // Store the rectangle in the map and add to bounds
        nodeRectangles.put(canvasNode, rectangle);
        bounds.add(rectangle);

        // Process outgoing edges
        for (ElkEdge edge : node.getOutgoingEdges()) {

            // Calculate the path for the current edge
            Path path = calculatePath(edge);

            // Find the connection associated with the edge
            Connection connection = treeCache.findConnectionById(edge.getIdentifier());

            // Store the path in the map and add to bounds
            connectionPaths.put(connection, path);
            bounds.add(path.bounds());

            // Recursively calculate nodes and paths for the target node
            calculateNodesAndPaths((ElkNode) edge.getTargets().get(0), treeCache);
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

            points.add(new Point2D.Double(section.getStartX(), section.getStartY()));

            for (ElkBendPoint bendPoint : section.getBendPoints()) {
                points.add(new Point2D.Double(bendPoint.getX(), bendPoint.getY()));
            }

            points.add(new Point2D.Double(section.getEndX(), section.getEndY()));
        }

        Rectangle2D labelBounds = edge.getLabels()
                .stream()
                .findFirst()
                .map(elkLabel -> new Rectangle2D.Double(
                        elkLabel.getX(),
                        elkLabel.getY(),
                        elkLabel.getWidth(),
                        elkLabel.getHeight()))
                .orElse(null);

        return new DefaultPath(points, labelBounds);
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


    @Override
    public void select(Selectable selectable) {
        deselectAll();
        selectable.select();
    }

    @Override
    public void deselectAll() {
        nodeRectangles.keySet().stream().forEach(Selectable::deselect);
        connectionPaths.keySet().stream().forEach(Selectable::deselect);
    }

}
