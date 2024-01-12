package hu.modeldriven.astah.traceability.layout;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

public interface Layout {

    Rectangle2D location(Node node);

    Path location(Connection connection);

    Dimension size();

    Node findNodeByLocation(Point2D point);

    Connection findConnectionByLocation(Point2D point);

    void select(Selectable selectable);

    void deselectAll();

}
