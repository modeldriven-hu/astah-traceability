package hu.modeldriven.astah.traceability.model;

import java.awt.*;
import java.util.Map;

public interface DiagramRenderer {

    void render(Graphics2D g,
                Map<ElementId, Rectangle> nodePositions,
                Map<ElementId, Path> connectionPositions);

}
