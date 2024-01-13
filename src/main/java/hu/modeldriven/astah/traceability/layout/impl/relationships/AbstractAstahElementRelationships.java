package hu.modeldriven.astah.traceability.layout.impl.relationships;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractAstahElementRelationships<T extends INamedElement> {

    public List<Connection> getRelationships(INamedElement element, Node currentNode, Set<Node> repository, AstahTheme theme) {
        List<Connection> results = new ArrayList<>();

        for (T relation : getOutgoingRelationships(element)) {

            INamedElement outgoingElement = getOutgoingElement(relation);

            Node outgoingNode = repository.stream()
                    .filter(node -> node.id().value().equals(outgoingElement.getId()))
                    .findFirst()
                    .orElseGet(() -> new AstahNode(outgoingElement, repository, theme));

            results.add(new AstahConnection(relation, currentNode, outgoingNode, theme));
        }

        return results;
    }

    protected abstract List<T> getOutgoingRelationships(INamedElement element);

    protected abstract INamedElement getOutgoingElement(T relation);

}
