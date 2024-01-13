package hu.modeldriven.astah.traceability.layout.impl.dependencies;

import com.change_vision.jude.api.inf.model.IDependency;
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

    public List<Connection> getRelationships(INamedElement element, Node sourceNode, Set<Node> repository, AstahTheme theme) {
        List<Connection> results = new ArrayList<>();

        for (T relation : getElementRelations(element)) {

            INamedElement targetElement = getTargetElement(relation);

            Node targetNode = repository.stream()
                    .filter(node -> node.id().value().equals(targetElement.getId()))
                    .findFirst()
                    .orElseGet(() -> new AstahNode(targetElement, repository, theme));

            results.add(new AstahConnection(relation, sourceNode, targetNode, theme));
        }

        return results;
    }

    protected abstract List<T> getElementRelations(INamedElement element);

    protected abstract INamedElement getTargetElement(T relation);

}
