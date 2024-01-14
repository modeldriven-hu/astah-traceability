package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;
import java.util.List;

class DependencyNodeBuilder extends AbstractNodeBuilder<IDependency> {

    @Override
    public List<IDependency> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientDependencies());
    }

    @Override
    public List<IDependency> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierDependencies());
    }

    @Override
    public INamedElement getOutgoingSupplier(IDependency relationship) {
        return relationship.getSupplier();
    }

    @Override
    public INamedElement getIncomingClient(IDependency relationship) {
        return relationship.getClient();
    }
}
