package hu.modeldriven.astah.traceability.layout;

import java.util.Collection;

public interface Graph {

    Collection<? extends Node> nodes();

    Collection<? extends Connection> connections();

    Node findNodeById(String id);

    Connection findConnectionById(String id);

}
