package hu.modeldriven.astah.traceability.model;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

public class DummyPath implements Path {

    private final List<Point2D> coordinates;
    private final Rectangle bounds;

    public DummyPath(Point2D... coordinates) {
        this.coordinates = Arrays.asList(coordinates);
        this.bounds = calculateBounds(this.coordinates);
    }

    @Override
    public List<Point2D> coordinates() {
        return coordinates;
    }

    @Override
    public Rectangle bounds() {
        return bounds;
    }

    private static Rectangle calculateBounds(List<Point2D> points) {

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
        return new Rectangle((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
    }
}
