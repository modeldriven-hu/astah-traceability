package hu.modeldriven.astah.traceability.model.impl;

import hu.modeldriven.astah.traceability.model.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class DefaultDiagramRenderer implements DiagramRenderer {

    private final Node rootNode;

    public DefaultDiagramRenderer(Node rootNode) {
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
            Graphics2D newGraphics = cloneGraphics(g);
            currentNode.renderer().render(newGraphics, nodePosition);
            newGraphics.dispose();
        } else {
            System.err.println("No coordinate for node " + currentNode.id().value());
        }

        for (Connection connection : currentNode.connections()) {

            Path connectionPosition = layout.location(connection);
            if (connectionPosition != null) {
                Graphics2D newGraphics = cloneGraphics(g);
                connection.renderer().render(newGraphics, connectionPosition);
                newGraphics.dispose();
            } else {
                System.err.println("No coordinate for connection " + connection.id().value());
            }

            renderRecursively(connection.target(), g, layout);
        }

    }

    private Graphics2D cloneGraphics(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();

        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        return graphics2D;
    }


}
