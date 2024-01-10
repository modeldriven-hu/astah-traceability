package hu.modeldriven.astah.traceability.model.impl;

import hu.modeldriven.astah.traceability.model.Connection;
import hu.modeldriven.astah.traceability.model.Layout;
import hu.modeldriven.astah.traceability.model.Node;
import hu.modeldriven.astah.traceability.model.Path;
import org.eclipse.elk.graph.*;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElkLayout implements Layout {

    private final Map<String, Rectangle2D> nodes;
    private final Map<String, Path> paths;

    private final Rectangle2D bounds = new Rectangle2D.Double();

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

        bounds.add(rectangle);

        for (ElkEdge edge : node.getOutgoingEdges()) {

            Path path = calculatePath(edge);
            paths.put(edge.getIdentifier(), path);

            bounds.add(path.bounds());

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

    @Override
    public Rectangle2D location(Node node) {
        return nodes.get(node.id().value());
    }

    @Override
    public Path location(Connection connection) {
        return paths.get(connection.id().value());
    }

    @Override
    public Dimension size() {
        return new Dimension((int) bounds.getWidth(), (int) bounds.getHeight());
    }
}
