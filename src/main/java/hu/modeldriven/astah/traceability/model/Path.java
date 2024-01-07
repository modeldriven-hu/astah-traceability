package hu.modeldriven.astah.traceability.model;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

public interface Path {

    List<Point2D> coordinates();

    Rectangle bounds();

}
