package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.ElementId;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.NodeRenderer;
import hu.modeldriven.astah.traceability.layout.impl.core.TextElementId;
import hu.modeldriven.astah.traceability.layout.impl.render.DefaultNodeRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AstahNode implements Node {

    private final INamedElement element;
    private final Set<Node> repository;

    private final List<Connection> connections;

    private boolean selected;

    public AstahNode(INamedElement element, Set<Node> repository) {
        this.element = element;
        this.repository = repository;
        this.repository.add(this);
        this.connections = buildConnections();
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

    private List<Connection> buildConnections() {

        List<Connection> connections = new ArrayList<>();

        for (IDependency dependency : element.getClientDependencies()) {

            INamedElement targetElement = dependency.getSupplier();

            Node targetNode = null;

            for (Node node : repository) {
                if (node.id().value().equals(targetElement.getId())) {
                    targetNode = node;
                    break;
                }
            }

            if (targetNode == null) {
                targetNode = new AstahNode(targetElement, repository);
            }

            connections.add(new AstahConnection(dependency, this, targetNode));
        }

        return connections;
    }

    @Override
    public NodeRenderer renderer() {
        return new DefaultNodeRenderer(this);
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
