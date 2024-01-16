package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.IInclude;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IUseCase;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class IncludeConnectionBuilder extends AbstractConnectionBuilder<IInclude> {

    public IncludeConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        super(nodes, theme);
    }

    @Override
    protected List<IInclude> getIncomingRelationships(INamedElement element) {
        return Collections.emptyList();
    }

    @Override
    protected List<IInclude> getOutgoingRelationships(INamedElement element) {

        if (element instanceof IUseCase) {
            IUseCase useCase = (IUseCase) element;
            return Arrays.asList(useCase.getIncludes());
        }

        return Collections.emptyList();
    }

    @Override
    protected INamedElement getIncomingClient(IInclude relation) {
        // Nothing to do here
        return null;
    }

    @Override
    protected INamedElement getIncomingSupplier(IInclude relation) {
        // Nothing to do here
        return null;
    }

    @Override
    protected INamedElement getOutgoingClient(IInclude relation) {
        return relation.getIncludingCase();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IInclude relation) {
        return relation.getAddition();
    }
}
