package hu.modeldriven.astah.traceability.model;

import java.awt.Rectangle;
import java.util.Map;

public class DummyLayout implements Layout {

    private final Map<ElementId,Rectangle> nodePositions;
    private final Map<ElementId, Path> connectionPositions;

    public DummyLayout(Map<ElementId,Rectangle> nodePositions,
                       Map<ElementId, Path> connectionPositions){
        this.nodePositions = nodePositions;
        this.connectionPositions = connectionPositions;
    }

    @Override
    public Rectangle location(Node node) {
        return nodePositions.get(node.id());
    }

    @Override
    public Path location(Connection connection) {
        return connectionPositions.get(connection.id());
    }
}
