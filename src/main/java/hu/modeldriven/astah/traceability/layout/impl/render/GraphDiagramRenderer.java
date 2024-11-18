package hu.modeldriven.astah.traceability.layout.impl.render;

import hu.modeldriven.astah.traceability.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class GraphDiagramRenderer implements DiagramRenderer {

    private static final Logger logger = LoggerFactory.getLogger(GraphDiagramRenderer.class);

    private final Graph graph;
    private final Layout layout;

    public GraphDiagramRenderer(Graph graph, Layout layout) {
        this.graph = graph;
        this.layout = layout;
    }

    @Override
    public void render(Graphics2D g) {

        for (Node node : graph.nodes()) {
            Rectangle2D nodePosition = layout.location(node);

            if (nodePosition != null) {
                Graphics2D newGraphics = cloneGraphics(g);
                node.renderer().render(newGraphics, nodePosition);
                newGraphics.dispose();
            } else {
                logger.info("No coordinate for node {}", node.id().value());
            }
        }

        for (Connection connection : graph.connections()) {
            Path connectionPosition = layout.location(connection);

            if (connectionPosition != null) {
                Graphics2D newGraphics = cloneGraphics(g);
                connection.renderer().render(newGraphics, connectionPosition);
                newGraphics.dispose();
            } else {
                logger.info("No coordinate for connection {}", connection.id().value());
            }
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
