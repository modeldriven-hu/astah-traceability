package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUsage;

import java.util.Arrays;
import java.util.List;

public class UsageNodeBuilder extends AbstractGraphNodeBuilder<IUsage> {

    @Override
    protected List<IUsage> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierUsages());
    }

    @Override
    protected List<IUsage> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientUsages());
    }

    @Override
    protected INamedElement getSupplier(IUsage relationship) {
        return relationship.getSupplier();
    }

    @Override
    protected INamedElement getClient(IUsage relationship) {
        return relationship.getClient();
    }
}
