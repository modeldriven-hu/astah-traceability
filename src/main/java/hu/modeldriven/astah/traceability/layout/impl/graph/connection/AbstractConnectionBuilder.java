package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.AstahNamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public abstract class AbstractConnectionBuilder<T extends INamedElement> implements ConnectionBuilder {

    protected final Map<String, AstahNode> nodes;
    protected final AstahTheme theme;
    private final Logger logger = LoggerFactory.getLogger(AbstractConnectionBuilder.class);

    protected AbstractConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        this.nodes = nodes;
        this.theme = theme;
    }

    @Override
    public void build(AstahNode node, Map<String, AstahConnection> visitedConnections) {

        INamedElement namedElement = node.namedElement();

        for (T relationship : getOutgoingRelationships(namedElement)) {
            if (!visitedConnections.containsKey(relationship.getId())) {
                AstahNode clientNode = nodes.get(getOutgoingClient(relationship).getId());
                AstahNode supplierNode = nodes.get(getOutgoingSupplier(relationship).getId());
                AstahConnection connection = buildConnection(relationship, clientNode, supplierNode, theme);
                visitedConnections.put(relationship.getId(), connection);
            }
        }

        for (T relationship : getIncomingRelationships(namedElement)) {
            if (!visitedConnections.containsKey(relationship.getId())) {
                AstahNode clientNode = nodes.get(getIncomingClient(relationship).getId());
                AstahNode supplierNode = nodes.get(getIncomingSupplier(relationship).getId());
                AstahConnection connection = buildConnection(relationship, clientNode, supplierNode, theme);
                visitedConnections.put(relationship.getId(), connection);
            }
        }

    }

    private AstahConnection buildConnection(T relationship, AstahNode clientNode, AstahNode supplierNode, AstahTheme theme) {
        logger.info("Building connection {}  between {} and {}",
                new AstahNamedElement(relationship).asLog(),
                new AstahNamedElement(clientNode.namedElement()).asLog(),
                new AstahNamedElement(supplierNode.namedElement()).asLog());

        return new AstahConnection(relationship, clientNode, supplierNode, theme);
    }

    protected abstract List<T> getIncomingRelationships(INamedElement element);

    protected abstract List<T> getOutgoingRelationships(INamedElement element);

    protected abstract INamedElement getIncomingClient(T relation);

    protected abstract INamedElement getIncomingSupplier(T relation);

    protected abstract INamedElement getOutgoingClient(T relation);

    protected abstract INamedElement getOutgoingSupplier(T relation);

}
