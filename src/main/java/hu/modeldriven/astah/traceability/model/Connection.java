package hu.modeldriven.astah.traceability.model;

public interface Connection {

    Node source();
    Node target();

    String name();

    Type type();

}
