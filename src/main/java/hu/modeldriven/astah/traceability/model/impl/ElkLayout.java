package hu.modeldriven.astah.traceability.model.impl;

import hu.modeldriven.astah.traceability.model.Connection;
import hu.modeldriven.astah.traceability.model.Layout;
import hu.modeldriven.astah.traceability.model.Node;
import hu.modeldriven.astah.traceability.model.Path;
import org.eclipse.elk.graph.*;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElkLayout implements Layout {

    private final Map<String, Rectangle2D> nodes;
    private final Map<String, Path> paths;

    public ElkLayout(ElkNode rootNode) {
        nodes = new HashMap<>();
        paths = new HashMap<>();
        calculateFromRootNode(rootNode);
    }

    private void calculateFromRootNode(ElkNode rootNode) {
        for (ElkNode child : rootNode.getChildren()) {
            calculateNodesAndPaths(child);
        }
    }

    private void calculateNodesAndPaths(ElkNode node) {

        if (nodes.containsKey(node.getIdentifier())) {
            return;
        }

        Rectangle2D rectangle = new Rectangle2D.Double(
                node.getX(), node.getY(),
                node.getWidth(), node.getHeight());

        nodes.put(node.getIdentifier(), rectangle);

        for (ElkEdge edge : node.getOutgoingEdges()) {
            paths.put(edge.getIdentifier(), calculatePath(edge));
            calculateNodesAndPaths((ElkNode) edge.getTargets().get(0));
        }
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

        Point2D labelPosition = null;

        if (edge.getLabels().size() > 0) {
            ElkLabel elkLabel = edge.getLabels().get(0);
            labelPosition = new Point2D.Double(elkLabel.getX(), elkLabel.getY());
        }

        return new DefaultPath(points, labelPosition);
    }

    @Override
    public Rectangle2D location(Node node) {
        return nodes.get(node.id().value());
    }

    @Override
    public Path location(Connection connection) {
        return paths.get(connection.id().value());
    }
}
