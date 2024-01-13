package hu.modeldriven.astah.traceability.layout.impl.relationships;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;
import java.util.List;

public class AstahElementDependencies extends AbstractAstahElementRelationships<IDependency> {

    @Override
    public List<IDependency> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientDependencies());
    }

    @Override
    public INamedElement getOutgoingElement(IDependency relation) {
        return relation.getSupplier();
    }
}
