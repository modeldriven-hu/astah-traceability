package hu.modeldriven.astah.traceability.layout.impl.render;

import hu.modeldriven.astah.traceability.layout.ConnectionRenderer;
import hu.modeldriven.astah.traceability.layout.Path;
import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;

public class AstahConnectionRenderer implements ConnectionRenderer {

    private final static int ICON_LABEL_PADDING = 5;

    private final static int ICON_COORDINATE_FIX = 1;

    private final AstahConnection connection;
    private final String name;

    private final AstahTheme theme;

    private final Image image;

    public AstahConnectionRenderer(AstahConnection connection, AstahTheme theme) {
        this.connection = connection;
        this.theme = theme;
        this.name = theme.getConnectionName(connection);
        this.image = theme.getLabelIcon(connection);
    }

    @Override
    public void render(Graphics2D g, Path path) {

        Color edgeColor;
        Color labelColor;

        if (connection.isSelected()) {
            edgeColor = theme.getSelectedConnectionEdgeColor();
            labelColor = theme.getSelectedConnectionLabelColor();
        } else {
            edgeColor = theme.getConnectionEdgeColor();
            labelColor = theme.getConnectionLabelColor();
        }

        g.setColor(edgeColor);
        drawPolyLine(g, path.coordinates());

        Arrow arrow = new Arrow(lastTwoPoints(path.coordinates()));
        arrow.draw(g);

        Point2D labelPosition = path.labelPosition();

        int posX = (int) labelPosition.getX();
        int posY = (int) labelPosition.getY();

        // Because labels are not drawn by swing to the top left coordinate, this
        // has to be fixed with ascent calculation
        FontMetrics metric = g.getFontMetrics(g.getFont());
        g.drawImage(image, posX, posY - metric.getAscent() - ICON_COORDINATE_FIX, null);

        g.setColor(labelColor);
        g.drawString(name, posX + image.getWidth(null) + ICON_LABEL_PADDING, posY);
    }

    private List<Point2D> lastTwoPoints(List<Point2D> points) {

        if (points.size() < 2) {
            throw new IllegalArgumentException("Point list shall have at least two points");
        }

        return points.subList(points.size() - 2, points.size());
    }

    private void drawPolyLine(Graphics2D g2d, List<Point2D> points) {
        if (points.size() < 2) {
            return; // Need at least two points to draw a polyline
        }

        int[] xPoints = new int[points.size()];
        int[] yPoints = new int[points.size()];

        // Convert Point2D coordinates to int arrays
        for (int i = 0; i < points.size(); i++) {
            xPoints[i] = (int) points.get(i).getX();
            yPoints[i] = (int) points.get(i).getY();
        }

        // Draw the polyline
        g2d.drawPolyline(xPoints, yPoints, points.size());
    }

    @Override
    public Dimension labelSize() {

        Dimension textSize = new TextLabel(name).size();

        double width = textSize.getWidth() + image.getWidth(null) + ICON_LABEL_PADDING;
        double height = textSize.getHeight();

        Dimension result = new Dimension();
        result.setSize(width, height);

        return result;
    }
}
