package hu.modeldriven.astah.traceability.layout.impl.core;

import hu.modeldriven.astah.traceability.layout.Path;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class DefaultPath implements Path {

    private final List<Point2D> coordinates;
    private final Rectangle2D bounds;

    private final Rectangle2D labelBounds;

    public DefaultPath(List<Point2D> coordinates, Rectangle2D labelBounds) {
        this.coordinates = coordinates;
        this.labelBounds = labelBounds;
        this.bounds = calculateBounds(this.coordinates, labelBounds);
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
    public Point2D labelPosition() {
        return labelBounds.getBounds().getLocation();
    }

    private static Rectangle2D calculateBounds(List<Point2D> points, Rectangle2D labelBounds) {

        Rectangle2D initialBounds = new Rectangle2D.Double(points.get(0).getX(), points.get(0).getY(), 0,0);

        for (Point2D point : points) {
            initialBounds.add(point);
        }

        if (initialBounds.getWidth() > initialBounds.getHeight()){
            initialBounds.add(initialBounds.getX(), initialBounds.getY() - 10);
            initialBounds.add(initialBounds.getX(), initialBounds.getY() + 20);
        } else {
            initialBounds.add(initialBounds.getX() - 10, initialBounds.getY());
            initialBounds.add(initialBounds.getX() + 20, initialBounds.getY());
        }

        if (labelBounds != null) {
            //initialBounds.add(labelBounds);
        }

        return initialBounds;
    }
}
