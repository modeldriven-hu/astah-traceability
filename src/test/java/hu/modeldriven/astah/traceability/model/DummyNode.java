package hu.modeldriven.astah.traceability.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyNode implements Node {

    private final String name;
    private final List<Connection> connections;

    public DummyNode(String name){
        this.name = name;
        this.connections = new ArrayList<>();
    }

    public void addConnection(Connection connection){
        this.connections.add(connection);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Connection> connections() {
        return connections;
    }

    @Override
    public NodeRenderer renderer() {
        return new DummyNodeRenderer(name);
    }
}
