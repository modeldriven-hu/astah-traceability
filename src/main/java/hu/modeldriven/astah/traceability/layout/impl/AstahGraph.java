package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Graph;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.graph.DependencyConnectionBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.DependencyNodeBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.GraphConnectionBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.GraphNodeBuilder;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.*;

public class AstahGraph implements Graph {

    private final Map<String, AstahNode> nodes;

    private final Map<String, AstahConnection> connections;

    public AstahGraph(INamedElement initialElement, AstahTheme theme) {
        this.nodes = buildNodes(initialElement, theme);
        this.connections = buildConnections(this.nodes, theme);
    }

    private Map<String, AstahNode> buildNodes(INamedElement initialElement, AstahTheme theme) {

        List<GraphNodeBuilder> builders = Arrays.asList(new DependencyNodeBuilder());

        Map<String, AstahNode> result = new HashMap<>();

        for (GraphNodeBuilder builder : builders) {
            builder.build(initialElement, result, theme);
        }

        return result;
    }


    private Map<String, AstahConnection> buildConnections(Map<String, AstahNode> nodes, AstahTheme theme) {

        List<GraphConnectionBuilder> builders = Arrays.asList(new DependencyConnectionBuilder(nodes));

        Map<String, AstahConnection> result = new HashMap<>();

        for (AstahNode node : nodes.values()) {
            for (GraphConnectionBuilder builder : builders) {
                builder.build(node, result, theme);
            }
        }

        return result;
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
