package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IGeneralization;
import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneralizationNodeBuilder extends AbstractNodeBuilder<IGeneralization> {

    @Override
    public List<IGeneralization> getOutgoingRelationships(INamedElement element) {
        return Collections.emptyList();
    }

    @Override
    public List<IGeneralization> getIncomingRelationships(INamedElement element) {

        if (element instanceof IClass){
            return Arrays.asList(((IClass)element).getGeneralizations());
        }

        return Collections.emptyList();
    }

    @Override
    public INamedElement getOutgoingSupplier(IGeneralization relationship) {
        return null;
    }

    @Override
    public INamedElement getIncomingClient(IGeneralization relationship) {
        return relationship.getSubType();
    }
}
