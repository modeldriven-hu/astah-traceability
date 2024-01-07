package hu.modeldriven.astah.traceability.model.impl;

import hu.modeldriven.astah.traceability.model.Connection;
import hu.modeldriven.astah.traceability.model.Layout;
import hu.modeldriven.astah.traceability.model.LayoutAlgorithm;
import hu.modeldriven.astah.traceability.model.Node;
import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.elk.alg.layered.options.LayeredOptions;

import java.util.HashSet;
import java.util.Set;

public class ElkLayoutAlgorithm implements LayoutAlgorithm {
    @Override
    public Layout layout(Node rootNode) {

        ElkNode graph = createElkGraph(rootNode);
        ElkNode layoutedNode = layout(graph);


        return null;
    }

    private ElkNode layout(ElkNode rootElkNode){
        LayoutAlgorithmData data = LayoutMetaDataService
                .getInstance()
                .getAlgorithmDataBySuffixOrDefault(
                        "org.eclipse.elk.layered",
                        "org.eclipse.elk.layered");

        rootElkNode.setProperty(CoreOptions.RESOLVED_ALGORITHM, data);
        rootElkNode.setProperty(CoreOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
        rootElkNode.setProperty(CoreOptions.DIRECTION, Direction.RIGHT);
        rootElkNode.setProperty(CoreOptions.SPACING_EDGE_NODE, 50.0);
        rootElkNode.setProperty(LayeredOptions.HIERARCHY_HANDLING, HierarchyHandling.SEPARATE_CHILDREN);

        LayeredLayoutProvider layeredLayoutProvider = new LayeredLayoutProvider();
        layeredLayoutProvider.initialize(null);

        try{
            BasicProgressMonitor monitor = new BasicProgressMonitor();
            layeredLayoutProvider.layout(rootElkNode, monitor);
        } finally {
            layeredLayoutProvider.dispose();
        }

        return rootElkNode;
    }

    private ElkNode createElkGraph(Node rootNode) {
        ElkNode graph = ElkGraphUtil.createGraph();

        traverseTree(rootNode, graph, new HashSet<>());

        return graph;
    }

    private ElkNode traverseTree(Node node, ElkNode parentElkNode, Set<ElkNode> elkNodes) {

        ElkNode currentElkNode = elkNodes.stream()
                .filter(elkNode -> node.id().value().equals(elkNode.getIdentifier()))
                .findFirst()
                .orElse(null);

        if (currentElkNode == null) {
            currentElkNode = createElkNode(node, parentElkNode);
            elkNodes.add(currentElkNode);
        }

        for (Connection connection : node.connections()) {
            ElkNode targetElkNode = traverseTree(connection.target(), currentElkNode, elkNodes);
            createEdge(connection, currentElkNode, targetElkNode);
        }

        return currentElkNode;
    }

    private ElkNode createElkNode(Node modelNode, ElkNode parent) {

        ElkNode node = ElkGraphUtil.createNode(parent);
        node.setIdentifier(modelNode.id().value());

        node.setWidth(100);
        node.setHeight(50);

        return node;
    }

    private void createEdge(Connection connection, ElkNode sourceNode, ElkNode targetNode) {
        ElkEdge edge = ElkGraphUtil.createSimpleEdge(sourceNode, targetNode);
        edge.setIdentifier(connection.id().value());

        ElkGraphUtil.createLabel(connection.name(), edge);
    }

}
