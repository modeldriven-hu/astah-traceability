package hu.modeldriven.astah.traceability.layout.impl.render;

import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.ConnectionRenderer;
import hu.modeldriven.astah.traceability.layout.Path;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.List;

public class DefaultConnectionRenderer implements ConnectionRenderer {

    private final Connection connection;
    private final String name;

    public DefaultConnectionRenderer(Connection connection) {
        this.connection = connection;
        this.name = connection.name();
    }

    @Override
    public void render(Graphics2D g, Path path) {

        g.setColor(Color.BLACK);
        drawPolyLine(g, path.coordinates());

        List<Point2D> lastTwoPoints = lastTwoPoints(path.coordinates());
        Arrow arrow = new Arrow(lastTwoPoints.get(0), lastTwoPoints.get(1));
        arrow.draw(g);

        Point2D labelPosition = path.labelPosition();
        g.drawString(name, (float) labelPosition.getX(), (float) labelPosition.getY());
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
        return new TextLabel(name).size();
    }
}
