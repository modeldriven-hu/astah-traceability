package hu.modeldriven.astah.traceability.layout;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

public interface Layout {

    Rectangle2D location(Node node);

    Path location(Connection connection);

    Dimension size();

}
