package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.List;
import java.util.Map;

public class DependencyConnectionBuilder extends AbstractConnectionBuilder<IDependency> {

    public DependencyConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        super(nodes, theme);
    }

    @Override
    protected List<IDependency> getIncomingRelationships(INamedElement element) {
        return List.of(element.getSupplierDependencies());
    }

    @Override
    protected List<IDependency> getOutgoingRelationships(INamedElement element) {
        return List.of(element.getClientDependencies());
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
