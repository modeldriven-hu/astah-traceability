package hu.modeldriven.astah.traceability.layout.impl.graph;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DependencyConnectionBuilder extends AbstractGraphConnectionBuilder<IDependency> {

    public DependencyConnectionBuilder(Map<String, AstahNode> nodes){
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
    protected Node getIncomingClientForConnection(IDependency relation) {
        return nodes.get(relation.getClient().getId());
    }

    @Override
    protected Node getIncomingSupplierForConnection(IDependency relation) {
        return nodes.get(relation.getSupplier().getId());
    }

    @Override
    protected Node getOutgoingClientForConnection(IDependency relation) {
        return nodes.get(relation.getClient().getId());
    }

    @Override
    protected Node getOutgoingSupplierForConnection(IDependency relation) {
        return nodes.get(relation.getSupplier().getId());
    }

}
