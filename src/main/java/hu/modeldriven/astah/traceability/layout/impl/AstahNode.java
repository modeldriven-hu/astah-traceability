package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.ElementId;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.NodeRenderer;

import java.util.Collections;
import java.util.List;

public class AstahNode implements Node {

    private final INamedElement element;

    public AstahNode(INamedElement element) {
        this.element = element;
    }

    @Override
    public ElementId id() {
        return new TextElementId(element.getId());
    }

    @Override
    public String name() {
        return element.getName();
    }

    @Override
    public List<Connection> connections() {
        return Collections.emptyList();
    }

    @Override
    public NodeRenderer renderer() {
        return new DefaultNodeRenderer(name());
    }
}
