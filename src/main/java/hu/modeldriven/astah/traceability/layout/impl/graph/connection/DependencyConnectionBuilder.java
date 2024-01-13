package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DependencyConnectionBuilder extends AbstractGraphConnectionBuilder<IDependency> {

    public DependencyConnectionBuilder(Map<String, AstahNode> nodes) {
        super(nodes);
    }

    @Override
    protected List<IDependency> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierDependencies());
    }

    @Override
    protected List<IDependency> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientDependencies());
    }

    @Override
    protected INamedElement getIncomingClient(IDependency relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getIncomingSupplier(IDependency relation) {
        return relation.getSupplier();
    }

    @Override
    protected INamedElement getOutgoingClient(IDependency relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IDependency relation) {
        return relation.getSupplier();
    }

}
