package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IDiagram;
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
                new UsageNodeBuilder(),
                new DiagramNodeBuilder());
    }

    public Map<String, AstahNode> build(INamedElement initialElement) {
        Map<String, AstahNode> result = new HashMap<>();
        buildNodesRecursively(initialElement, result);
        return result;
    }

    void buildNodesRecursively(INamedElement currentElement, Map<String, AstahNode> store) {
        AstahNode node = new AstahNode(currentElement, this.theme);
        store.put(currentElement.getId(), node);

        if (skipBuild(currentElement)) {
            return;
        }

        for (NodeBuilder builder : this.builders) {
            builder.build(currentElement, store, this::buildNodesRecursively);
        }
    }

    // FIXME maybe the builder shall tell that it is a final one, so after the creation we shall stop
    boolean skipBuild(INamedElement currentElement){
        return currentElement instanceof IDiagram;
    }

}
