package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUsage;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.List;
import java.util.Map;

public class UsageConnectionBuilder extends AbstractConnectionBuilder<IUsage> {

    public UsageConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        super(nodes, theme);
    }

    @Override
    protected List<IUsage> getIncomingRelationships(INamedElement element) {
        return List.of(element.getSupplierUsages());
    }

    @Override
    protected List<IUsage> getOutgoingRelationships(INamedElement element) {
        return List.of(element.getClientUsages());
    }

    @Override
    protected INamedElement getIncomingClient(IUsage relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getIncomingSupplier(IUsage relation) {
        return relation.getSupplier();
    }

    @Override
    protected INamedElement getOutgoingClient(IUsage relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IUsage relation) {
        return relation.getSupplier();
    }
}
