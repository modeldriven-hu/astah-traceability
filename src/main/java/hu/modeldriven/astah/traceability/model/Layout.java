package hu.modeldriven.astah.traceability.model;

import java.awt.Rectangle;

public interface Layout {

    Rectangle location(Node node);

    Path location(Connection connection);

//    Map<ElementId, Rectangle> nodePositions,
//    Map<ElementId, Path> connectionPositions
}
