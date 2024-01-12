package hu.modeldriven.astah.traceability.layout.impl.layout;

import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.tree.TreeTraverseAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class TreeCache {

    private final Map<String, Node> nodes = new HashMap<>();
    private final Map<String, Connection> connections = new HashMap<>();

    public TreeCache(Node rootNode){
        TreeTraverseAlgorithm algorithm = new TreeTraverseAlgorithm();

        algorithm.traverse(rootNode, new TreeTraverseAlgorithm.TreeVisitor() {

            @Override
            public void visit(Node node) {
                nodes.put(node.id().value(), node);
            }

            @Override
            public void visit(Connection connection) {
                connections.put(connection.id().value(), connection);
            }
        });
    }

    public Node findNodeById(String id){
        return nodes.get(id);
    }

    public Connection findConnectionById(String id){
        return connections.get(id);
    }

}
