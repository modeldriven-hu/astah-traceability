package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.ConnectionRenderer;
import hu.modeldriven.astah.traceability.layout.ElementId;
import hu.modeldriven.astah.traceability.layout.Node;

public class AstahConnection implements Connection {

    private final INamedElement element;

    private final Node source;

    private final Node target;

    public AstahConnection(INamedElement element, Node source, Node target){
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

    private String type(){
        // FIXME calculate type
        return "FIXME";
    }

    @Override
    public ConnectionRenderer renderer() {
        return new DefaultConnectionRenderer(type());
    }
}
