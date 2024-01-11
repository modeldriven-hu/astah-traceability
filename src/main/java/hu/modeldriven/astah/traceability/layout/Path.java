package hu.modeldriven.astah.traceability.layout;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public interface Path {

    List<Point2D> coordinates();

    Rectangle2D bounds();

    Point2D labelPosition();

}
