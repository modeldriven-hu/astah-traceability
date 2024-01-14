package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.util.Map;
import java.util.function.BiConsumer;

public interface NodeBuilder {

    void build(INamedElement currentElement, Map<String, AstahNode> store, BiConsumer<INamedElement, Map<String, AstahNode>> callback);

}
