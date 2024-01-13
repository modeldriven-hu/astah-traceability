package hu.modeldriven.astah.traceability.layout.impl.relationships;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IRealization;
import com.change_vision.jude.api.inf.model.IUsage;

import java.util.Arrays;
import java.util.List;

public class AstahElementRealizations extends AbstractAstahElementRelationships<IRealization> {


    @Override
    protected List<IRealization> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientRealizations());
    }

    @Override
    protected INamedElement getOutgoingElement(IRealization relation) {
        return relation.getSupplier();
    }
}
