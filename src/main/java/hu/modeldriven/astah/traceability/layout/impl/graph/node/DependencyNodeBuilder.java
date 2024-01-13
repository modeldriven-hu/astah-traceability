package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;
import java.util.List;

public class DependencyNodeBuilder extends AbstractGraphNodeBuilder<IDependency> {

    @Override
    protected List<IDependency> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierDependencies());
    }

    @Override
    protected List<IDependency> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientDependencies());
    }

    @Override
    protected INamedElement getSupplier(IDependency relationship) {
        return relationship.getSupplier();
    }

    @Override
    protected INamedElement getClient(IDependency relationship) {
        return relationship.getClient();
    }
}
