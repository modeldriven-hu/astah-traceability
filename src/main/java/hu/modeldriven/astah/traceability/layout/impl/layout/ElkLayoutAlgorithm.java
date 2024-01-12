package hu.modeldriven.astah.traceability.layout.impl.layout;

import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Layout;
import hu.modeldriven.astah.traceability.layout.LayoutAlgorithm;
import hu.modeldriven.astah.traceability.layout.Node;
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider;
import org.eclipse.elk.core.RecursiveGraphLayoutEngine;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

public class ElkLayoutAlgorithm implements LayoutAlgorithm {

    @Override
    public Layout layout(Node rootNode) {
        ElkNode graph = createElkGraph(rootNode);
        ElkNode newNode = layout(graph);
        return new ElkLayout(newNode, rootNode);
    }

    private ElkNode layout(ElkNode graph) {

//        LayoutAlgorithmData data = LayoutMetaDataService
//                .getInstance()
//                .getAlgorithmDataBySuffixOrDefault(
//                        "org.eclipse.elk.alk.layered",
//                        "org.eclipse.elk.layered");
//
//        rootElkNode.setProperty(CoreOptions.RESOLVED_ALGORITHM, data);
//        rootElkNode.setProperty(CoreOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
//        rootElkNode.setProperty(CoreOptions.DIRECTION, Direction.RIGHT);
//        rootElkNode.setProperty(CoreOptions.SPACING_EDGE_NODE, 50.0);
//        rootElkNode.setProperty(LayeredOptions.HIERARCHY_HANDLING, HierarchyHandling.SEPARATE_CHILDREN);

        graph.setProperty(CoreOptions.SEPARATE_CONNECTED_COMPONENTS, true);
        graph.setProperty(CoreOptions.SPACING_COMPONENT_COMPONENT, 50.0);
        graph.setProperty(CoreOptions.SPACING_NODE_NODE, 50.0);
        graph.setProperty(LayeredMetaDataProvider.SPACING_NODE_NODE_BETWEEN_LAYERS, 50.0);
        graph.setProperty(CoreOptions.SPACING_EDGE_EDGE, 50.0);
        graph.setProperty(CoreOptions.SPACING_EDGE_NODE, 50.0);
        graph.setProperty(CoreOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
        graph.setProperty(CoreOptions.DIRECTION, Direction.DOWN);

        try {
            BasicProgressMonitor monitor = new BasicProgressMonitor();
            new RecursiveGraphLayoutEngine().layout(graph, monitor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }

    private ElkNode createElkGraph(Node rootNode) {
        ElkNode graph = ElkGraphUtil.createGraph();

        traverseTree(rootNode, graph, new HashSet<>());

        return graph;
    }

    private ElkNode traverseTree(Node modelNode, ElkNode graph, Set<ElkNode> elkNodes) {

        ElkNode currentElkNode = elkNodes.stream()
                .filter(elkNode -> modelNode.id().value().equals(elkNode.getIdentifier()))
                .findFirst()
                .orElse(null);

        if (currentElkNode == null) {
            currentElkNode = createElkNode(modelNode, graph);
            elkNodes.add(currentElkNode);
        }

        for (Connection connection : modelNode.connections()) {
            ElkNode targetElkNode = traverseTree(connection.target(), graph, elkNodes);
            createEdge(connection, currentElkNode, targetElkNode);
        }

        return currentElkNode;
    }

    private ElkNode createElkNode(Node modelNode, ElkNode parent) {

        ElkNode node = ElkGraphUtil.createNode(parent);
        node.setIdentifier(modelNode.id().value());
        Dimension preferredBounds = modelNode.renderer().size();

        node.setWidth(preferredBounds.getWidth());
        node.setHeight(preferredBounds.getHeight());

        return node;
    }

    private void createEdge(Connection connection, ElkNode sourceNode, ElkNode targetNode) {
        ElkEdge edge = ElkGraphUtil.createSimpleEdge(sourceNode, targetNode);
        edge.setIdentifier(connection.id().value());
        ElkLabel label = ElkGraphUtil.createLabel(connection.name(), edge);
        Dimension labelBounds = connection.renderer().labelSize();
        label.setDimensions(labelBounds.getWidth(), labelBounds.getHeight());
    }

}
