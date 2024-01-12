package hu.modeldriven.astah.traceability.layout;

public interface LayoutSelection {

    void select(Selectable selectable);

    void deselectAll();

    Identifiable selectedElement();

    boolean hasSelection();
}
