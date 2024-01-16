package hu.modeldriven.astah.core;

import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.INamedElement;

/**
 * Represents a connection between a model element and a diagram
 */
public interface IDiagramRelationship extends INamedElement {
    INamedElement getSupplier();

    IDiagram getClient();
}
