package hu.modeldriven.astah.traceability.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

public class DummyConnectionRenderer implements ConnectionRenderer {

    private final String name;

    public DummyConnectionRenderer(String name){
        this.name = name;
    }

    @Override
    public void render(Graphics2D g, Path path) {
        g.setColor(Color.BLUE);
        drawPolyLine(g, path.coordinates());
        Rectangle bounds = path.bounds();
        g.drawString(name, bounds.x + bounds.width / 2,bounds.y + bounds.height /2);
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

}
