package hu.modeldriven.astah.traceability.layout.impl.layout;

import hu.modeldriven.astah.traceability.layout.*;
import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

public class ElkLayoutAlgorithm implements LayoutAlgorithm {

    private final Logger logger = LoggerFactory.getLogger(ElkLayoutAlgorithm.class);

    @Override
    public Layout layout(Graph graph) {
        ElkNode elkGraph = createElkGraph(graph);
        ElkNode newNode = layout(elkGraph);
        return new ElkLayout(newNode, graph);
    }

    private ElkNode layout(ElkNode graph) {

        logger.info("Running layout algorithm for {}", graph);

        // https://git.eclipse.org/c/sirius/org.eclipse.sirius.git/commit/?id=f7513dac9aa9dacd68354f4cd9bd834ae07b5756

        LayoutMetaDataService service = LayoutMetaDataService.getInstance();
        service.registerLayoutMetaDataProviders(new LayeredMetaDataProvider());

        for (LayoutAlgorithmData data : service.getAlgorithmData()) {
            logger.info("Bundle name: {}", data.getBundleName());
            logger.info("Id: {}", data.getId());
            logger.info("Name: {}", data.getName());
        }

        LayoutAlgorithmData data = service
                .getAlgorithmDataBySuffixOrDefault(
                        "org.eclipse.elk.layered",
                        "org.eclipse.elk.layered");

        logger.info("Layout algorithm data: {}", data);

        graph.setProperty(CoreOptions.RESOLVED_ALGORITHM, data);
        graph.setProperty(CoreOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
        graph.setProperty(CoreOptions.DIRECTION, Direction.DOWN);
        graph.setProperty(CoreOptions.SPACING_EDGE_EDGE, 50.0);
        graph.setProperty(CoreOptions.SPACING_NODE_NODE, 50.0);
        graph.setProperty(CoreOptions.SPACING_EDGE_NODE, 50.0);

        LayeredLayoutProvider provider = new LayeredLayoutProvider();

        try {
            BasicProgressMonitor monitor = new BasicProgressMonitor().withLogging(true).withLogPersistence(true).withExecutionTimeMeasurement(false);
            provider.initialize(null);
            provider.layout(graph, monitor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.dispose();
        }

        return graph;
    }

    private ElkNode createElkGraph(Graph graph) {
        ElkNode elkGraph = ElkGraphUtil.createGraph();
        buildElkGraph(graph, elkGraph);
        return elkGraph;
    }

    private void buildElkGraph(Graph graph, ElkNode elkGraph) {

        logger.info("Building elk graph");
        logger.info("Graph.nodes.size = {}", graph.nodes().size());
        logger.info("Graph.connection.size = {}", graph.connections().size());

        Map<Node, ElkNode> elkNodes = new HashMap<>();

        for (Node node : graph.nodes()) {
            logger.info("Building node: {}", node);
            elkNodes.put(node, createElkNode(node, elkGraph));
        }

        for (Connection connection : graph.connections()) {
            logger.info("Building connection: {}", connection);
            ElkNode sourceNode = elkNodes.get(connection.source());
            ElkNode targetNode = elkNodes.get(connection.target());
            createEdge(connection, sourceNode, targetNode);
        }

    }

    private ElkNode createElkNode(Node modelNode, ElkNode parent) {

        ElkNode node = ElkGraphUtil.createNode(parent);
        node.setIdentifier(modelNode.id().value());
        Dimension preferredBounds = modelNode.renderer().size();

        node.setWidth(preferredBounds.getWidth());
        node.setHeight(preferredBounds.getHeight());

        logger.info("Creating ELK node with {} {}", preferredBounds, modelNode);

        return node;
    }

    private void createEdge(Connection connection, ElkNode sourceNode, ElkNode targetNode) {
        ElkEdge edge = ElkGraphUtil.createSimpleEdge(sourceNode, targetNode);
        edge.setIdentifier(connection.id().value());

        ElkLabel label = ElkGraphUtil.createLabel(connection.labelName(), edge);
        Dimension labelBounds = connection.renderer().labelSize();

        label.setDimensions(labelBounds.getWidth(), labelBounds.getHeight());

        logger.info("Creating ELK edge with label {} for {} between elk nodes: {} and {}",
                labelBounds, connection, sourceNode.getIdentifier(), targetNode.getIdentifier());
    }

}
