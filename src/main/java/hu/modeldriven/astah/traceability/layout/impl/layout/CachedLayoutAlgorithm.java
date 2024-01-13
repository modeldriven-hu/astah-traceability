package hu.modeldriven.astah.traceability.layout.impl.layout;

import hu.modeldriven.astah.traceability.layout.Graph;
import hu.modeldriven.astah.traceability.layout.Layout;
import hu.modeldriven.astah.traceability.layout.LayoutAlgorithm;
import hu.modeldriven.astah.traceability.layout.Node;

public class CachedLayoutAlgorithm implements LayoutAlgorithm {

    private final LayoutAlgorithm algorithm;

    private Layout currentLayout;
    private Node currentNode;

    private Graph currentGraph;

    public CachedLayoutAlgorithm(LayoutAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Layout layout(Node rootNode) {

        if (currentNode != rootNode || currentLayout == null) {
            currentNode = rootNode;
            currentLayout = algorithm.layout(rootNode);
        }

        return currentLayout;
    }

    @Override
    public Layout layout(Graph graph){
        if (currentGraph != graph || currentLayout == null) {
            currentGraph = graph;
            currentLayout = algorithm.layout(graph);
        }

        return currentLayout;
    }
}
