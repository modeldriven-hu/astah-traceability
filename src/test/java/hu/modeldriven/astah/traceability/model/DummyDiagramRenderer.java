package hu.modeldriven.astah.traceability.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DummyDiagramRenderer implements DiagramRenderer {

    private final Node rootNode;

    public DummyDiagramRenderer(Node rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void render(Graphics2D g, Layout layout) {
        renderRecursively(rootNode, g, layout);
    }

    private void renderRecursively(Node currentNode,
                                   Graphics2D g,
                                   Layout layout) {

        Rectangle nodePosition = layout.location(currentNode);
        if (nodePosition != null) {
            currentNode.renderer().render(g, nodePosition);
        } else {
            System.err.println("No coordinate for node " + currentNode.id());
        }

        for (Connection connection : currentNode.connections()) {

            Path connectionPosition = layout.location(connection);
            if (connectionPosition != null) {
                connection.renderer().render(g, connectionPosition);
            } else {
                System.err.println("No coordinate for connection " + connection.id());
            }

            renderRecursively(connection.target(), g, layout);
        }

    }


}
