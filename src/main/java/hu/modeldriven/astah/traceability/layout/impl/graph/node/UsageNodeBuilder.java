package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUsage;

import java.util.Arrays;
import java.util.List;

public class UsageNodeBuilder extends AbstractNodeBuilder<IUsage> {

    @Override
    public List<IUsage> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientUsages());
    }

    @Override
    public List<IUsage> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierUsages());
    }

    @Override
    public INamedElement getOutgoingSupplier(IUsage relationship) {
        return relationship.getSupplier();
    }

    @Override
    public INamedElement getIncomingClient(IUsage relationship) {
        return relationship.getClient();
    }
}
