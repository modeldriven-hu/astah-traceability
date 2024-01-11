package hu.modeldriven.astah.traceability.layout.impl;

import hu.modeldriven.astah.traceability.layout.ElementId;

import java.util.Objects;

public class DefaultElementId implements ElementId {

    private final String id;

    public DefaultElementId(String id) {
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
        DefaultElementId that = (DefaultElementId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
