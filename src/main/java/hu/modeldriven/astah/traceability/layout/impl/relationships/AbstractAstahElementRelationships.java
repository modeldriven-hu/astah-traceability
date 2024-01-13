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

//public abstract class AbstractAstahElementRelationships<T extends INamedElement> {

//    public List<Connection> buildRelationships(INamedElement element, Node currentNode, Set<Node> repository, AstahTheme theme) {
//        List<Connection> results = new ArrayList<>();
//
//        for (T relation : getOutgoingRelationships(element)) {
//            INamedElement outgoingElement = getOutgoingElement(relation);
//            results.add(new AstahConnection(relation,
//                    currentNode,
//                    findOrCreateNode(outgoingElement, repository, theme),
//                    theme));
//        }
//
//        for (T relation : getIncomingRelationships(element)) {
//            INamedElement incomingElement = getIncomingElement(relation);
//            results.add(new AstahConnection(relation,
//                    findOrCreateNode(incomingElement, repository, theme),
//                    currentNode,
//                    theme));
//        }
//
//        return results;
//    }
//
//    private static Node findOrCreateNode(INamedElement element, Set<Node> repository, AstahTheme theme ) {
//        return repository.stream()
//                .filter(node -> node.id().value().equals(element.getId()))
//                .findFirst()
//                .orElseGet(() -> new AstahNode(element, repository, theme));
//    }
//
//
//    protected abstract List<T> getIncomingRelationships(INamedElement element);
//
//    protected abstract List<T> getOutgoingRelationships(INamedElement element);
//
//    protected abstract INamedElement getIncomingElement(T relation);
//
//    protected abstract INamedElement getOutgoingElement(T relation);

//}
