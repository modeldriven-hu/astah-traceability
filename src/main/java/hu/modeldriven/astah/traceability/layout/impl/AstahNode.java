package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUsage;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.ElementId;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.NodeRenderer;
import hu.modeldriven.astah.traceability.layout.impl.core.TextElementId;
import hu.modeldriven.astah.traceability.layout.impl.dependencies.AstahElementDependencies;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahNodeRenderer;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AstahNode implements Node {

    private final INamedElement element;
    private final Set<Node> repository;

    private final AstahTheme theme;

    private final List<Connection> connections;

    private boolean selected;

    public AstahNode(INamedElement element, Set<Node> repository, AstahTheme theme) {
        this.element = element;
        this.repository = repository;
        this.theme = theme;
        this.repository.add(this);
        this.connections = buildConnections(element, repository, theme);
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
    public List<Connection> connections() {
        return connections;
    }

    private List<Connection> buildConnections(INamedElement element, Set<Node> repository, AstahTheme theme) {

        List<Connection> results = new ArrayList<>();

        results.addAll(new AstahElementDependencies().getRelationships(element, this, repository, theme));

        return results;
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
