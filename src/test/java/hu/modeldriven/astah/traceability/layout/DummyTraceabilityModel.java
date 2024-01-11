package hu.modeldriven.astah.traceability.layout;

import hu.modeldriven.astah.traceability.layout.impl.DefaultDiagramRenderer;
import hu.modeldriven.astah.traceability.layout.impl.ElkLayoutAlgorithm;

public class DummyTraceabilityModel implements TraceabilityModel {

    private final Node rootNode;
    private final LayoutAlgorithm layoutAlgorithm;

    public DummyTraceabilityModel() {
        rootNode = createRootNode();
        this.layoutAlgorithm = new ElkLayoutAlgorithm();
    }

    private Node createRootNode() {

        DummyNode rootNode = new DummyNode("Root element");

        DummyNode child1 = new DummyNode("Child1");
        DummyNode child2 = new DummyNode("Child2");

        rootNode.addConnection(new DummyConnection("Root -> Child2", rootNode, child1));
        rootNode.addConnection(new DummyConnection("Root -> Child2", rootNode, child2));

        DummyNode subChild1 = new DummyNode("SubChild1");
        DummyNode subChild2 = new DummyNode("SubChild2");
        DummyNode subChild3 = new DummyNode("SubChild3");

        child1.addConnection(new DummyConnection("Child1 -> SubChild1", child1, subChild1));
        child1.addConnection(new DummyConnection("Child1 -> SubChild2", child1, subChild2));

        child2.addConnection(new DummyConnection("Child2 -> SubChild3", child2, subChild3));

        subChild1.addConnection(new DummyConnection("SubChild1 -> SubChild2", subChild1, subChild2));

        return rootNode;
    }

    @Override
    public Layout layout() {
        return layoutAlgorithm.layout(rootNode);
    }

    @Override
    public DiagramRenderer renderer() {
        return new DefaultDiagramRenderer(rootNode);
    }

}
