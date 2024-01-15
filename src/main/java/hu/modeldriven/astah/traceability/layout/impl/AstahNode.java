package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.ElementId;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.NodeRenderer;
import hu.modeldriven.astah.traceability.layout.impl.core.TextElementId;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahNodeRenderer;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Collections;
import java.util.List;

public class AstahNode implements Node {

    private final INamedElement element;
    private final AstahTheme theme;

    private boolean selected;

    public AstahNode(INamedElement element, AstahTheme theme) {
        this.element = element;
        this.theme = theme;
    }

    public INamedElement namedElement() {
        return this.element;
    }

    @Override
    public ElementId id() {
        String id = element.getId();
        return new TextElementId(id);
    }

    @Override
    public String name() {
        return element.getName();
    }

    @Override
    public NodeRenderer renderer() {
        return new AstahNodeRenderer(this, theme);
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
