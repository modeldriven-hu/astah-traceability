package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IRealization;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.List;
import java.util.Map;

public class RealizationConnectionBuilder extends AbstractConnectionBuilder<IRealization> {

    public RealizationConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        super(nodes, theme);
    }

    @Override
    protected List<IRealization> getIncomingRelationships(INamedElement element) {
        return List.of(element.getSupplierRealizations());
    }

    @Override
    protected List<IRealization> getOutgoingRelationships(INamedElement element) {
        return List.of(element.getClientRealizations());
    }

    @Override
    protected INamedElement getIncomingClient(IRealization relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getIncomingSupplier(IRealization relation) {
        return relation.getSupplier();
    }

    @Override
    protected INamedElement getOutgoingClient(IRealization relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IRealization relation) {
        return relation.getSupplier();
    }

}
