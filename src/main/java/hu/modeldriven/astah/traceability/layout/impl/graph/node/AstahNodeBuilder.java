package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.AstahNamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstahNodeBuilder {

    private final Logger logger = LoggerFactory.getLogger(AstahNodeBuilder.class);

    private final List<NodeBuilder> builders;
    private final AstahTheme theme;

    public AstahNodeBuilder(AstahTheme theme) {
        this.theme = theme;
        builders = Arrays.asList(
                new AttributeNodeBuilder(),
                new DependencyNodeBuilder(),
                new UsageNodeBuilder(),
                new DiagramNodeBuilder(),
                new GeneralizationNodeBuilder(),
                new RealizationNodeBuilder(),
                new IncludeNodeBuilder()
        );
    }

    public Map<String, AstahNode> build(INamedElement initialElement) {
        var result = new HashMap<String, AstahNode>();
        buildNodesRecursively(initialElement, result);
        return result;
    }

    void buildNodesRecursively(INamedElement currentElement, Map<String, AstahNode> store) {

        logger.info("Building node: {}", new AstahNamedElement(currentElement).asLog());

        AstahNode node = new AstahNode(currentElement, this.theme);
        store.put(currentElement.getId(), node);

        for (NodeBuilder builder : this.builders) {
            builder.build(currentElement, store, this::buildNodesRecursively);
        }
    }

}
