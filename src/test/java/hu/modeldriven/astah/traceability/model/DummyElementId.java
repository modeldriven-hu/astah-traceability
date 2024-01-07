package hu.modeldriven.astah.traceability.model;

import java.util.Objects;

public class DummyElementId implements ElementId {

    private final String id;

    public DummyElementId(String id) {
        this.id = id;
    }

    @Override
    public String value() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DummyElementId that = (DummyElementId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
