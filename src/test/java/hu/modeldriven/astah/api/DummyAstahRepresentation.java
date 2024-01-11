package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.model.IPackage;
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

        element1.addClientDependency(new DummyDependency("element1->element2", element1, element2));
        element2.addClientDependency(new DummyDependency("element2->element3", element2, element3));
        element4.addClientDependency(new DummyDependency("element4->element2", element4, element2));

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

}