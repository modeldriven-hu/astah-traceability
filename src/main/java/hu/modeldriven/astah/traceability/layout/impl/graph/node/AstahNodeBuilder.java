package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstahNodeBuilder {

    private final List<NodeBuilder> builders;
    private final AstahTheme theme;

    public AstahNodeBuilder(AstahTheme theme) {
        this.theme = theme;
        builders = Arrays.asList(
                new DependencyNodeBuilder(),
                new UsageNodeBuilder());
    }

    public Map<String, AstahNode> build(INamedElement initialElement) {
        Map<String, AstahNode> result = new HashMap<>();
        buildNodesRecursively(initialElement, result);
        return result;
    }

    void buildNodesRecursively(INamedElement currentElement, Map<String, AstahNode> store) {
        AstahNode node = new AstahNode(currentElement, this.theme);
        store.put(currentElement.getId(), node);

        for (NodeBuilder builder : this.builders) {
            builder.build(currentElement, store, this::buildNodesRecursively);
        }
    }

}
