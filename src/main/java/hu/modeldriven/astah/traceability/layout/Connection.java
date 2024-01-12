package hu.modeldriven.astah.traceability.layout;

public interface Connection extends Selectable, Identifiable {

    Node source();

    Node target();

    String name();

    ConnectionRenderer renderer();

}
