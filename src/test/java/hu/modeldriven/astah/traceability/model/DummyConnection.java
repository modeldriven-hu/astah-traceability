package hu.modeldriven.astah.traceability.model;

public class DummyConnection implements Connection {

    private static int INSTANCE_ID = 0;

    private final String id;

    private final String name;
    private final Node source;

    private final Node target;

    public DummyConnection(String name, Node source, Node target) {
        this.name = name;
        this.source = source;
        this.target = target;
        this.id = String.valueOf(INSTANCE_ID++);
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public Node source() {
        return source;
    }

    @Override
    public Node target() {
        return target;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ConnectionRenderer renderer() {
        return new DummyConnectionRenderer();
    }
}
