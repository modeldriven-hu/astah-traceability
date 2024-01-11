package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.Astah;

public class TestAstah implements Astah {

    @Override
    public IPackage rootPackage(){

        TestPackage rootPackage = new TestPackage("Root");
        rootPackage.addElement(new TestNamedElement("Element1"));
        rootPackage.addElement(new TestNamedElement("Element2"));

        TestPackage childPackage = new TestPackage("Child");
        rootPackage.addElement(childPackage);

        childPackage.addElement(new TestNamedElement("Element3"));
        childPackage.addElement(new TestNamedElement("Element4"));

        TestPackage subChildPackage = new TestPackage("SubChild");
        childPackage.addElement(subChildPackage);

        subChildPackage.addElement(new TestNamedElement("Element5"));

        return rootPackage;
    }

}
