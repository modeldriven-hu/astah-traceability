package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Map;

public interface GraphConnectionBuilder {

    void build(AstahNode node, Map<String, AstahConnection> visitedConnections, AstahTheme theme);

}
