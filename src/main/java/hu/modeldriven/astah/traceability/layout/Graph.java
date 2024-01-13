package hu.modeldriven.astah.traceability.layout;

import java.util.Collection;
import java.util.Set;

public interface Graph {

    Node initalNode();

    Collection<? extends Node> nodes();

    Collection<? extends Connection> connections();

}
