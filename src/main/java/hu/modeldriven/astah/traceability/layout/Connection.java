package hu.modeldriven.astah.traceability.layout;

public interface Connection {

    ElementId id();

    Node source();

    Node target();

    String name();

    ConnectionRenderer renderer();

}
