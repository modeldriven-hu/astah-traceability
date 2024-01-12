package hu.modeldriven.astah.traceability.layout.impl;

import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Node;

import java.util.LinkedHashSet;
import java.util.Set;

public class TreeTraverseAlgorithm {

    public void traverse(Node rootNode, TreeVisitor callback) {
        Set<Node> visitedNodes = new LinkedHashSet<>();
        traverseRecursive(rootNode, callback, visitedNodes);
    }

    private void traverseRecursive(Node currentNode, TreeVisitor callback, Set<Node> visitedNodes) {
        if (visitedNodes.contains(currentNode)) {
            return;
        }

        visitedNodes.add(currentNode);
        callback.visit(currentNode);

        for (Connection connection : currentNode.connections()) {
            callback.visit(connection);
            traverseRecursive(connection.target(), callback, visitedNodes);
        }
    }

    public interface TreeVisitor {

        void visit(Node node);

        void visit(Connection connection);

    }

}
