package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.util.Map;

public interface ConnectionBuilder {

    void build(AstahNode node, Map<String, AstahConnection> visitedConnections);

}
