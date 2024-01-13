package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.AstahException;
import hu.modeldriven.astah.core.AstahRepresentation;

public class DummyAstahRepresentation implements AstahRepresentation {

    @Override
    public IPackage rootPackage(){

        DummyPackage rootPackage = new DummyPackage("Root");

        DummyNamedElement element1 = new DummyNamedElement("Element1");
        DummyNamedElement element2 = new DummyNamedElement("Element2");
        DummyNamedElement element3 = new DummyNamedElement("Element3");
        DummyNamedElement element4 = new DummyNamedElement("Element4");
        DummyNamedElement element5 = new DummyNamedElement("Element5");

        DummyDependency dependency = new DummyDependency("element1->element2", element1, element2);

        element1.addClientDependency(dependency);
        element2.addSupplierDependency(dependency);

        element3.addClientDependency(new DummyDependency("element3->element4", element3, element4));
        element4.addClientUsage(new DummyUsage("element4->element5", element4, element5));
        element5.addClientDependency(new DummyDependency("element5->element3", element5, element3));

        rootPackage.addElement(element1);
        rootPackage.addElement(element2);

        DummyPackage childPackage = new DummyPackage("Child");
        rootPackage.addElement(childPackage);

        childPackage.addElement(element3);
        childPackage.addElement(element4);

        DummyPackage subChildPackage = new DummyPackage("SubChild");
        childPackage.addElement(subChildPackage);

        subChildPackage.addElement(element5);

        return rootPackage;
    }

    @Override
    public void selectModelElement(String id) throws AstahException {
        System.out.println("Selecting model element id=" + id);
        throw new AstahException(new UnsupportedOperationException("Selecting model element"));
    }

}
