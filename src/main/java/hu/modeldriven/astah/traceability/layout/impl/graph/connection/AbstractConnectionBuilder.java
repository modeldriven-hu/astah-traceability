package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.List;
import java.util.Map;

public abstract class AbstractConnectionBuilder<T extends INamedElement> implements ConnectionBuilder {

    protected final Map<String, AstahNode> nodes;

    protected final AstahTheme theme;

    public AbstractConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        this.nodes = nodes;
        this.theme = theme;
    }

    @Override
    public void build(AstahNode node, Map<String, AstahConnection> visitedConnections) {

        INamedElement namedElement = node.namedElement();

        for (T relationship : getOutgoingRelationships(namedElement)) {
            if (!visitedConnections.containsKey(relationship.getId())) {
                Node clientNode = nodes.get(getOutgoingClient(relationship).getId());
                Node supplierNode = nodes.get(getOutgoingSupplier(relationship).getId());
                AstahConnection connection = new AstahConnection(relationship, clientNode, supplierNode, theme);
                visitedConnections.put(relationship.getId(), connection);
            }
        }

        for (T relationship : getIncomingRelationships(namedElement)) {
            if (!visitedConnections.containsKey(relationship.getId())) {
                Node clientNode = nodes.get(getIncomingClient(relationship).getId());
                Node supplierNode = nodes.get(getIncomingSupplier(relationship).getId());
                AstahConnection connection = new AstahConnection(relationship, clientNode, supplierNode, theme);
                visitedConnections.put(relationship.getId(), connection);
            }
        }

    }

    protected abstract List<T> getIncomingRelationships(INamedElement element);

    protected abstract List<T> getOutgoingRelationships(INamedElement element);

    protected abstract INamedElement getIncomingClient(T relation);

    protected abstract INamedElement getIncomingSupplier(T relation);

    protected abstract INamedElement getOutgoingClient(T relation);

    protected abstract INamedElement getOutgoingSupplier(T relation);

}
