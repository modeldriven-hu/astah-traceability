package hu.modeldriven.astah.traceability.model;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

public class DummyLayoutAlgorithm implements LayoutAlgorithm {

    @Override
    public Layout layout(Node rootNode) {

        Map<ElementId, Rectangle> nodePositions = new HashMap<>();
        Map<ElementId, Path> connectionPositions = new HashMap<>();

        // TODO finish layout code

        return new DummyLayout(nodePositions, connectionPositions);
    }
}
