package hu.modeldriven.astah.traceability.layout.impl;

import hu.modeldriven.astah.traceability.layout.ElementId;

import java.util.Objects;

public class TextElementId implements ElementId {

    private final String id;

    public TextElementId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id shall not be null");
        }
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
        TextElementId that = (TextElementId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
