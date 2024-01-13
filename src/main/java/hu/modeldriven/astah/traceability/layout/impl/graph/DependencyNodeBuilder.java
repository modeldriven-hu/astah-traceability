package hu.modeldriven.astah.traceability.layout.impl.graph;

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
    protected INamedElement getSupplierForNode(IDependency relationship) {
        return relationship.getSupplier();
    }

    @Override
    protected INamedElement getClientForNode(IDependency relationship) {
        return relationship.getClient();
    }
}
