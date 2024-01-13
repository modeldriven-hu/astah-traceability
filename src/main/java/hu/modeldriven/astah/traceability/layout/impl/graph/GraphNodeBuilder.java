package hu.modeldriven.astah.traceability.layout.impl.graph;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Map;

public interface GraphNodeBuilder {

    void build(INamedElement namedElement, Map<String, AstahNode> nodes, AstahTheme theme);

}
