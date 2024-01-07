package hu.modeldriven.astah.traceability.model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public interface Layout {

    Rectangle2D location(Node node);

    Path location(Connection connection);

}
