package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.AstahException;
import hu.modeldriven.astah.core.AstahRepresentation;

public class DummyAstahRepresentation implements AstahRepresentation {

    @Override
    public IPackage rootPackage() {

        DummyPackage rootPackage = new DummyPackage("Root");

        DummyClass animal = new DummyClass("Animal");
        DummyClass dog = new DummyClass("Dog");

        rootPackage.addElement(animal);
        rootPackage.addElement(dog);

        animal.addClientDependency(new DummyDependency("dep", animal, dog));

        /*
        DummyClass element3 = new DummyClass("Element3");
        DummyClass element4 = new DummyClass("Element4");
        DummyClass element5 = new DummyClass("Element5");

        DummyDiagram diagram = new DummyDiagram("My Class Diagram");

        DummyGeneralization generalization  = new DummyGeneralization("Generalization", animal, dog);
        animal.addSpecialization(generalization);
        dog.addGeneralization(generalization);

        DummyRealization realization = new DummyRealization("element1->element2", dog, animal);
        dog.addClientRealization(realization);
        animal.addSupplierRealization(realization);

        dog.addDiagram(diagram);

        element3.addClientUsage(new DummyUsage("element3->element4", element3, element4));
        element4.addClientDependency(new DummyDependency("element4->element5", element4, element5));
        //element5.addClientDependency(new DummyDependency("element5->element3", element5, element3));

        rootPackage.addElement(animal);
        rootPackage.addElement(dog);

        DummyPackage childPackage = new DummyPackage("Child");
        rootPackage.addElement(childPackage);

        childPackage.addElement(element3);
        childPackage.addElement(element4);

        DummyPackage subChildPackage = new DummyPackage("SubChild");
        childPackage.addElement(subChildPackage);

        subChildPackage.addElement(element5);
        */

        return rootPackage;
    }

    @Override
    public void selectModelElement(String id) throws AstahException {
        System.out.println("Selecting model element id=" + id);
        throw new AstahException(new UnsupportedOperationException("Selecting model element"));
    }

}
