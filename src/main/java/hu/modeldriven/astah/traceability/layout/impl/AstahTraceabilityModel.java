package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.*;
import hu.modeldriven.astah.traceability.layout.impl.layout.CachedLayoutAlgorithm;
import hu.modeldriven.astah.traceability.layout.impl.layout.ElkLayoutAlgorithm;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;
import hu.modeldriven.astah.traceability.layout.impl.render.DefaultDiagramRenderer;

import java.util.LinkedHashSet;

public class AstahTraceabilityModel implements TraceabilityModel {

    private final AstahNode rootNode;

    private final LayoutAlgorithm layoutAlgorithm;

    private final Graph graph;

    public AstahTraceabilityModel(INamedElement element) {
        AstahTheme theme = new AstahTheme();
        this.rootNode = new AstahNode(element, theme);
        this.graph = new AstahGraph(element, theme);
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
