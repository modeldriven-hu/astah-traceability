package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Graph;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.graph.connection.DependencyConnectionBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.connection.GraphConnectionBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.connection.UsageConnectionBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.node.DependencyNodeBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.node.GraphNodeBuilder;
import hu.modeldriven.astah.traceability.layout.impl.graph.node.UsageNodeBuilder;
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

        // FIXME because it is not recursive, it fails to work properly
        /*

            for (NodeBuilder builder : builders){
                AstahNode node = new AstahNode(initialElement);

                List<INamedElement> nextLevelElements = relationshipResolver.nextLevelElements(node/initialElement);

                for (INamedElement nextLevelElement : nextLevelElements){
                    buildNodes(nextLevelElement, theme);
                }
            }

         */

        List<GraphNodeBuilder> builders = Arrays.asList(
                new DependencyNodeBuilder(),
                new UsageNodeBuilder());

        Map<String, AstahNode> result = new HashMap<>();

        for (GraphNodeBuilder builder : builders) {
            builder.build(initialElement, result, theme);
        }

        return result;
    }


    private Map<String, AstahConnection> buildConnections(Map<String, AstahNode> nodes, AstahTheme theme) {

        List<GraphConnectionBuilder> builders = Arrays.asList(
                new DependencyConnectionBuilder(nodes),
                new UsageConnectionBuilder(nodes));

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
