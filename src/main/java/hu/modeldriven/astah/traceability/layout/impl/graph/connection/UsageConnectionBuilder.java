package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUsage;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsageConnectionBuilder extends AbstractGraphConnectionBuilder<IUsage> {

    public UsageConnectionBuilder(Map<String, AstahNode> nodes) {
        super(nodes);
    }

    @Override
    protected List<IUsage> getIncomingRelationships(INamedElement element) {
        return Arrays.asList(element.getSupplierUsages());
    }

    @Override
    protected List<IUsage> getOutgoingRelationships(INamedElement element) {
        return Arrays.asList(element.getClientUsages());
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
