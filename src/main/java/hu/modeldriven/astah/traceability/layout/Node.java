package hu.modeldriven.astah.traceability.layout;

import java.util.List;

public interface Node extends Selectable {

    ElementId id();

    String name();

    List<Connection> connections();

    NodeRenderer renderer();

}
