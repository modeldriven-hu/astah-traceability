package hu.modeldriven.astah.traceability.layout.impl;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Arrow {

    private final Point2D fromPt;
    private final Point2D toPt;

    public Arrow(Point2D fromPt, Point2D toPt) {
        this.fromPt = fromPt;
        this.toPt = toPt;
    }

    public void draw(Graphics2D g) {
        drawArrow(g, fromPt, toPt, 11);
    }

    private void drawArrow(Graphics2D g2d, Point2D fromPt, Point2D toPt, int size) {
        // Calculate the angle of the arrow
        double angle = Math.atan2(toPt.getY() - fromPt.getY(), toPt.getX() - fromPt.getX());

        // Calculate the arrowhead points
        double x1 = toPt.getX() - size * Math.cos(angle - Math.PI / 6);
        double y1 = toPt.getY() - size * Math.sin(angle - Math.PI / 6);
        double x2 = toPt.getX() - size * Math.cos(angle + Math.PI / 6);
        double y2 = toPt.getY() - size * Math.sin(angle + Math.PI / 6);

        // Draw the arrowhead
        g2d.fillPolygon(new int[]{(int) toPt.getX(), (int) x1, (int) x2},
                new int[]{(int) toPt.getY(), (int) y1, (int) y2}, 3);
    }

}
