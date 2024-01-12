package hu.modeldriven.astah.traceability.layout.impl;

import hu.modeldriven.astah.traceability.layout.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class DefaultDiagramRenderer implements DiagramRenderer {

    private final Node rootNode;
    private final Layout layout;

    public DefaultDiagramRenderer(Node rootNode, Layout layout) {
        this.rootNode = rootNode;
        this.layout = layout;
    }

    @Override
    public void render(Graphics2D g) {

        TreeTraverseAlgorithm algorithm = new TreeTraverseAlgorithm();

        algorithm.traverse(rootNode, new TreeTraverseAlgorithm.TreeVisitor() {
            @Override
            public void visit(Node node) {
                Rectangle2D nodePosition = layout.location(node);

                if (nodePosition != null) {
                    Graphics2D newGraphics = cloneGraphics(g);
                    node.renderer().render(newGraphics, nodePosition);
                    newGraphics.dispose();
                } else {
                    System.err.println("No coordinate for node " + node.id().value());
                }
            }

            @Override
            public void visit(Connection connection) {

                Path connectionPosition = layout.location(connection);

                if (connectionPosition != null) {
                    Graphics2D newGraphics = cloneGraphics(g);
                    connection.renderer().render(newGraphics, connectionPosition);
                    newGraphics.dispose();
                } else {
                    System.err.println("No coordinate for connection " + connection.id().value());
                }
            }
        });
    }

    private Graphics2D cloneGraphics(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();

        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        return graphics2D;
    }


}
