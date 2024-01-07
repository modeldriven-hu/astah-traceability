package hu.modeldriven.astah.traceability.model;

public interface Connection {

    ElementId id();

    Node source();

    Node target();

    String name();

    ConnectionRenderer renderer();

}
