package hu.modeldriven.astah.traceability.layout.impl.graph;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.List;
import java.util.Map;

public abstract class AbstractGraphConnectionBuilder<T extends INamedElement> implements GraphConnectionBuilder {

    protected final Map<String, AstahNode> nodes;

    public AbstractGraphConnectionBuilder(Map<String, AstahNode> nodes){
        this.nodes = nodes;
    }


    @Override
    public void build(AstahNode node, Map<String, AstahConnection> visitedConnections, AstahTheme theme){

        INamedElement namedElement = node.namedElement();

        for (T relationship : getOutgoingRelationships(namedElement)) {
            if (!visitedConnections.containsKey(relationship.getId())) {
                Node clientNode = getOutgoingClientForConnection(relationship);
                Node supplierNode = getOutgoingSupplierForConnection(relationship);
                AstahConnection connection = new AstahConnection(relationship, clientNode, supplierNode, theme);
                visitedConnections.put(relationship.getId(), connection);
            }
        }

        for (T relationship : getIncomingRelationships(namedElement)) {
            if (!visitedConnections.containsKey(relationship.getId())) {
                Node clientNode = getIncomingClientForConnection(relationship);
                Node supplierNode = getIncomingSupplierForConnection(relationship);
                AstahConnection connection = new AstahConnection(relationship, clientNode, supplierNode, theme);
                visitedConnections.put(relationship.getId(), connection);
            }
        }

    }

    protected abstract List<T> getIncomingRelationships(INamedElement element);

    protected abstract List<T> getOutgoingRelationships(INamedElement element);

    protected abstract Node getIncomingClientForConnection(T relation);

    protected abstract Node getIncomingSupplierForConnection(T relation);

    protected abstract Node getOutgoingClientForConnection(T relation);

    protected abstract Node getOutgoingSupplierForConnection(T relation);

}
