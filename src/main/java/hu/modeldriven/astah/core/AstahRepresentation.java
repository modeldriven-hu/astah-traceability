package hu.modeldriven.astah.core;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

public interface AstahRepresentation {

    IPackage rootPackage() throws AstahException;

    void selectModelElement(String id) throws AstahException;

}
