package hu.modeldriven.astah.traceability.layout.impl.dependencies;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;
import java.util.List;

public class AstahElementDependencies extends AbstractAstahElementRelationships<IDependency> {

    @Override
    public List<IDependency> getElementRelations(INamedElement element) {
        return Arrays.asList(element.getClientDependencies());
    }

    @Override
    public INamedElement getTargetElement(IDependency relation) {
        return relation.getSupplier();
    }
}
