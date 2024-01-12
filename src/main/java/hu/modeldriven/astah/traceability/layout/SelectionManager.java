package hu.modeldriven.astah.traceability.layout;

public interface SelectionManager {

    enum SelectionMethod { SingleSelection};

    void select(Node node, SelectionMethod method);

}
