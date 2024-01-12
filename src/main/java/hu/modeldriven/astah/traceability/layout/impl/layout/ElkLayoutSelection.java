package hu.modeldriven.astah.traceability.layout.impl.layout;

import hu.modeldriven.astah.traceability.layout.*;

import java.util.Set;
import java.util.stream.Stream;

public class ElkLayoutSelection implements LayoutSelection {

    private final Set<Node> nodes;
    private final Set<Connection> connections;

    public ElkLayoutSelection(Set<Node> nodes, Set<Connection> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    @Override
    public void select(Selectable selectable) {
        deselectAll();
        selectable.select();
    }

    @Override
    public void deselectAll() {
        nodes.stream().forEach(Selectable::deselect);
        connections.stream().forEach(Selectable::deselect);
    }

    @Override
    public Identifiable selectedElement() {
        return Stream.concat(
                        nodes.stream()
                                .filter(Node::isSelected)
                                .map(Identifiable.class::cast),
                        connections.stream()
                                .filter(Connection::isSelected)
                                .map(Identifiable.class::cast)
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean hasSelection() {
        return selectedElement() != null;
    }

}
