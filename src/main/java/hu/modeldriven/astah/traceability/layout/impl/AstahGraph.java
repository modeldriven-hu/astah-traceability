package hu.modeldriven.astah.traceability.layout.impl;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Graph;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AstahGraph implements Graph {

    private final Map<String, AstahNode> nodes;

    private final Map<String, AstahConnection> connections;

    public AstahGraph(INamedElement initialNode, AstahTheme theme) {
        this.nodes = buildNodes(initialNode, theme);
        this.connections = buildConnections(nodes, theme);
    }

    private Map<String, AstahNode> buildNodes(INamedElement currentNamedElement, AstahTheme theme) {
        Map<String, AstahNode> result = new HashMap<>();
        buildNodesRecursively(currentNamedElement, result, theme);
        return result;
    }

    private void buildNodesRecursively(INamedElement namedElement, Map<String, AstahNode> nodes, AstahTheme theme) {

        AstahNode node = new AstahNode(namedElement, theme);
        nodes.put(node.id().value(), node);

        for (IDependency dependency : namedElement.getClientDependencies()) {

            INamedElement element = dependency.getSupplier();

            if (!nodes.containsKey(element.getId())) {
                buildNodesRecursively(element, nodes, theme);
            }
        }

        for (IDependency dependency : namedElement.getSupplierDependencies()) {

            INamedElement element = dependency.getClient();

            if (!nodes.containsKey(element.getId())) {
                buildNodesRecursively(element, nodes, theme);
            }
        }
    }

    private Map<String, AstahConnection> buildConnections(Map<String, AstahNode> nodes, AstahTheme theme) {

        Map<String, AstahConnection> result = new HashMap<>();

        for (AstahNode node : nodes.values()){

            INamedElement namedElement = node.namedElement();

            for (IDependency dependency : namedElement.getClientDependencies()) {
                if (!result.containsKey(dependency.getId())) {
                    Node clientNode = nodes.get(dependency.getClient().getId());
                    Node supplierNode = nodes.get(dependency.getSupplier().getId());
                    AstahConnection connection = new AstahConnection(dependency, clientNode, supplierNode, theme);
                    result.put(dependency.getId(), connection);
                }
            }

            for (IDependency dependency : namedElement.getSupplierDependencies()) {
                if (!result.containsKey(dependency.getId())) {
                    Node clientNode = nodes.get(dependency.getClient().getId());
                    Node supplierNode = nodes.get(dependency.getSupplier().getId());
                    AstahConnection connection = new AstahConnection(dependency, clientNode, supplierNode, theme);
                    result.put(dependency.getId(), connection);
                }
            }

        }

        return result;
    }

    @Override
    public Node initalNode() {
        return null;
    }

    @Override
    public Collection<? extends Node> nodes() {
        return nodes.values();
    }

    @Override
    public Collection<? extends Connection> connections() {
        return connections.values();
    }
}
