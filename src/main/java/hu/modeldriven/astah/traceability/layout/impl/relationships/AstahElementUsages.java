package hu.modeldriven.astah.traceability.layout.impl.relationships;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUsage;

import java.util.Arrays;
import java.util.List;

public class AstahElementUsages extends AbstractAstahElementRelationships<IUsage> {

    @Override
    protected List<IUsage> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierUsages());
    }

    @Override
    protected List<IUsage> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientUsages());
    }

    @Override
    protected INamedElement getIncomingElement(IUsage relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getOutgoingElement(IUsage relation) {
        return relation.getSupplier();
    }
}
