package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IRealization;

import java.util.Arrays;
import java.util.List;

public class RealizationNodeBuilder extends AbstractNodeBuilder<IRealization> {
    @Override
    public List<IRealization> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientRealizations());
    }

    @Override
    public List<IRealization> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierRealizations());
    }

    @Override
    public INamedElement getOutgoingSupplier(IRealization relationship) {
        return relationship.getSupplier();
    }

    @Override
    public INamedElement getIncomingClient(IRealization relationship) {
        return relationship.getClient();
    }
}
