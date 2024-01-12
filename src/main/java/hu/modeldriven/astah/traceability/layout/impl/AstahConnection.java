package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.ConnectionRenderer;
import hu.modeldriven.astah.traceability.layout.ElementId;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.core.TextElementId;
import hu.modeldriven.astah.traceability.layout.impl.render.DefaultConnectionRenderer;

public class AstahConnection implements Connection {

    private final INamedElement element;

    private final Node source;

    private final Node target;

    private boolean selected;

    public AstahConnection(INamedElement element, Node source, Node target) {
        this.element = element;
        this.source = source;
        this.target = target;
    }

    @Override
    public ElementId id() {
        return new TextElementId(element.getId());
    }

    @Override
    public Node source() {
        return source;
    }

    @Override
    public Node target() {
        return target;
    }

    @Override
    public String name() {
        return element.getName();
    }

    @Override
    public ConnectionRenderer renderer() {
        return new DefaultConnectionRenderer(this);
    }

    @Override
    public void select() {
        this.selected = true;
    }

    @Override
    public void deselect() {
        this.selected = false;
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }
}
