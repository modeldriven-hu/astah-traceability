package hu.modeldriven.astah.traceability.model.impl;

import hu.modeldriven.astah.traceability.model.ConnectionRenderer;
import hu.modeldriven.astah.traceability.model.Path;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class DefaultConnectionRenderer implements ConnectionRenderer {

    private final String name;

    public DefaultConnectionRenderer(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics2D g, Path path) {
        g.setColor(Color.BLUE);
        drawPolyLine(g, path.coordinates());

        if (path.labelPosition() != null){
            Point2D labelPosition = path.labelPosition();
            g.drawString(name, (float) labelPosition.getX(), (float) labelPosition.getY());
        } else {
            Rectangle2D bounds = path.bounds();
            g.drawString(name, (float) (bounds.getX() + bounds.getWidth() / 2), (float) (bounds.getY() + bounds.getHeight() / 2));
        }
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
    public Rectangle2D labelPreferredBounds() {
        return new TextLabel(name).size();
    }
}
