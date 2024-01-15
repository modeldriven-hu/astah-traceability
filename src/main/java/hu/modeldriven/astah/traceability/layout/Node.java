package hu.modeldriven.astah.traceability.layout;

public interface Node extends Selectable, Identifiable {

    String name();

    NodeRenderer renderer();

}
