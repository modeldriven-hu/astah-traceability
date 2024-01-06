package hu.modeldriven.astah.traceability.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

public class DummyDiagramRenderer implements DiagramRenderer {

    private final Node rootNode;

    public DummyDiagramRenderer(Node rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void render(Graphics2D g,
                       Map<ElementId, Rectangle> nodePositions,
                       Map<ElementId, Path> connectionPositions) {
        renderRecursively(rootNode, g, nodePositions, connectionPositions);
    }

    private void renderRecursively(Node currentNode,
                                   Graphics2D g,
                                   Map<ElementId, Rectangle> nodePositions,
                                   Map<ElementId, Path> connectionPositions) {

        Rectangle nodePosition = nodePositions.get(currentNode.id());
        if (nodePosition != null) {
            currentNode.renderer().render(g, nodePosition);
        }

        for (Connection connection : currentNode.connections()) {

            Path connectionPosition = connectionPositions.get(connection.id());
            if (connectionPosition != null) {
                connection.renderer().render(g, connectionPosition);
            }

            renderRecursively(connection.target(), g, nodePositions, connectionPositions);
        }

    }


}
