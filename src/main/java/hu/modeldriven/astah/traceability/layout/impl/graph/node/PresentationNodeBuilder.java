package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.IPresentation;

import java.util.Collections;
import java.util.List;

public class PresentationNodeBuilder extends AbstractNodeBuilder<IPresentation> {

    @Override
    public List<IPresentation> getOutgoingRelationships(INamedElement element) {
        return Collections.emptyList();
    }

    @Override
    public List<IPresentation> getIncomingRelationships(INamedElement element) {
        return null;
    }

    @Override
    public INamedElement getOutgoingSupplier(IPresentation relationship) {
        return null;
    }

    @Override
    public INamedElement getIncomingClient(IPresentation relationship) {
        return null;
    }
}
