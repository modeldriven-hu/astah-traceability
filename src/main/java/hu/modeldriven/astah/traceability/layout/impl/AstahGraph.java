package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Graph;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.graph.connection.AstahConnectionBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.node.AstahNodeBuilder;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Collection;
import java.util.Map;

public class AstahGraph implements Graph {

    private final Map<String, AstahNode> nodes;

    private final Map<String, AstahConnection> connections;

    public AstahGraph(INamedElement initialElement, AstahTheme theme) {
        this.nodes = buildNodes(initialElement, theme);
        this.connections = buildConnections(this.nodes, theme);
    }

    private Map<String, AstahNode> buildNodes(INamedElement initialElement, AstahTheme theme) {
        AstahNodeBuilder nodeBuilder = new AstahNodeBuilder(theme);
        return nodeBuilder.build(initialElement);
    }

    private Map<String, AstahConnection> buildConnections(Map<String, AstahNode> nodes, AstahTheme theme) {
        AstahConnectionBuilder connectionBuilder = new AstahConnectionBuilder(nodes, theme);
        return connectionBuilder.build();
    }

    @Override
    public Collection<? extends Node> nodes() {
        return nodes.values();
    }

    @Override
    public Collection<? extends Connection> connections() {
        return connections.values();
    }

    @Override
    public Node findNodeById(String id) {
        return nodes.get(id);
    }

    @Override
    public Connection findConnectionById(String id) {
        return connections.get(id);
    }
}
