package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IInclude;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUseCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IncludeNodeBuilder extends AbstractNodeBuilder<IInclude> {
    @Override
    protected List<IInclude> getOutgoingRelationships(INamedElement element) {

        if (element instanceof IUseCase) {
            IUseCase useCase = (IUseCase) element;
            return Arrays.asList(useCase.getIncludes());
        }

        return Collections.emptyList();
    }

    @Override
    protected List<IInclude> getIncomingRelationships(INamedElement element) {
        return Collections.emptyList();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IInclude relationship) {
        return relationship.getAddition();
    }

    @Override
    protected INamedElement getIncomingClient(IInclude relationship) {
        // Nothing to do here
        return null;
    }
}
