package hu.modeldriven.astah.core;

import com.change_vision.jude.api.inf.model.INamedElement;

/**
 * Represents a connection between a model element and a diagram
 *
 */
public interface IPresentation extends INamedElement {
    INamedElement getSupplier();

    INamedElement getClient();
}
