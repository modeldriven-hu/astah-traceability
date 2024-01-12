package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.*;

import java.util.LinkedHashSet;

public class DefaultTraceabilityModel implements TraceabilityModel {

    private final Node rootNode;

    private final LayoutAlgorithm layoutAlgorithm;

    public DefaultTraceabilityModel(INamedElement element) {
        this.rootNode = new AstahNode(element, new LinkedHashSet<>());
        this.layoutAlgorithm = new CachedLayoutAlgorithm(new ElkLayoutAlgorithm());
    }

    @Override
    public Layout layout() {
        return layoutAlgorithm.layout(rootNode);
    }

    @Override
    public DiagramRenderer renderer() {
        return new DefaultDiagramRenderer(rootNode, layout());
    }

}
