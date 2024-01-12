package hu.modeldriven.astah.traceability.layout;

public interface Connection extends Selectable {

    ElementId id();

    Node source();

    Node target();

    String name();

    ConnectionRenderer renderer();

}
