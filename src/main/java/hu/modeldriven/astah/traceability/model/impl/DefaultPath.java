package hu.modeldriven.astah.traceability.model.impl;

import hu.modeldriven.astah.traceability.model.Path;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class DefaultPath implements Path {

    private final List<Point2D> coordinates;
    private final Rectangle2D bounds;

    private final Point2D labelPosition;

    public DefaultPath(List<Point2D> coordinates, Point2D labelPosition) {
        this.coordinates = coordinates;
        this.labelPosition = labelPosition;
        this.bounds = calculateBounds(this.coordinates);
    }

    @Override
    public List<Point2D> coordinates() {
        return coordinates;
    }

    @Override
    public Rectangle2D bounds() {
        return bounds;
    }

    @Override
    public Point2D labelPosition(){
        return labelPosition;
    }

    private static Rectangle2D calculateBounds(List<Point2D> points) {

        if (points == null || points.isEmpty()) {
            return new Rectangle(0, 0, 0, 0);
        }

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        // Iterate through the points to find min and max coordinates
        for (Point2D point : points) {
            minX = Math.min(minX, point.getX());
            minY = Math.min(minY, point.getY());
            maxX = Math.max(maxX, point.getX());
            maxY = Math.max(maxY, point.getY());
        }

        // Create a Rectangle2D representing the bounds
        return new Rectangle2D.Double(minX, minY, (maxX - minX), (maxY - minY));
    }
}