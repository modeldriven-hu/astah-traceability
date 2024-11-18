package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstahConnectionBuilder {

    private final Map<String, AstahNode> nodes;

    private final List<ConnectionBuilder> builders;

    public AstahConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        this.nodes = nodes;
        builders = Arrays.asList(
                new AttributeConnectionBuilder(nodes, theme),
                new DependencyConnectionBuilder(nodes, theme),
                new UsageConnectionBuilder(nodes, theme),
                new DiagramConnectionBuilder(nodes, theme),
                new GeneralizationConnectionBuilder(nodes, theme),
                new RealizationConnectionBuilder(nodes, theme),
                new IncludeConnectionBuilder(nodes, theme)
                );
    }

    public Map<String, AstahConnection> build() {
        Map<String, AstahConnection> result = new HashMap<>();

        for (AstahNode node : nodes.values()) {
            for (ConnectionBuilder builder : builders) {
                builder.build(node, result);
            }
        }

        return result;
    }

}
