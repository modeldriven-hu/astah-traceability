package hu.modeldriven.astah.traceability.model;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

public class DummyPath implements Path {

    private final List<Point2D> coordinates;

    public DummyPath(Point2D... coordinates) {
        this.coordinates = Arrays.asList(coordinates);
    }

    @Override
    public List<Point2D> coordinates() {
        return coordinates;
    }
}
