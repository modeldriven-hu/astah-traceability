package hu.modeldriven.astah.traceability.layout;

public interface LayoutAlgorithm {

    Layout layout(Node rootNode);

    Layout layout(Graph graph);

}
