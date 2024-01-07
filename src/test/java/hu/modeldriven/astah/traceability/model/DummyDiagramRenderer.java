package hu.modeldriven.astah.traceability.model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

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

        Rectangle2D nodePosition = layout.location(currentNode);
        if (nodePosition != null) {
            Graphics2D newGraphics = (Graphics2D) g.create();
            currentNode.renderer().render(newGraphics, nodePosition);
            newGraphics.dispose();

        } else {
            System.err.println("No coordinate for node " + currentNode.id());
        }

        for (Connection connection : currentNode.connections()) {

            Path connectionPosition = layout.location(connection);
            if (connectionPosition != null) {
                Graphics2D newGraphics = (Graphics2D) g.create();
                connection.renderer().render(newGraphics, connectionPosition);
                newGraphics.dispose();
            } else {
                System.err.println("No coordinate for connection " + connection.id());
            }

            renderRecursively(connection.target(), g, layout);
        }

    }


}
