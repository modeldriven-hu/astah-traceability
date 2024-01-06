package hu.modeldriven.astah.traceability.model;

import java.util.List;

public interface Node {

    String name();

    List<Connection> connections();

    NodeRenderer renderer();

}
