package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IGeneralization;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GeneralizationConnectionBuilder extends AbstractConnectionBuilder<IGeneralization> {

    public GeneralizationConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        super(nodes, theme);
    }

    @Override
    protected List<IGeneralization> getIncomingRelationships(INamedElement element) {
        if (element instanceof IClass){
            return Arrays.asList(((IClass)element).getSpecializations());
        }

        return Collections.emptyList();
    }

    @Override
    protected List<IGeneralization> getOutgoingRelationships(INamedElement element) {
        if (element instanceof IClass){
            return Arrays.asList(((IClass)element).getGeneralizations());
        }

        return Collections.emptyList();
    }

    @Override
    protected INamedElement getIncomingClient(IGeneralization relation) {
        return relation.getSubType();
    }

    @Override
    protected INamedElement getIncomingSupplier(IGeneralization relation) {
        return relation.getSuperType();
    }

    @Override
    protected INamedElement getOutgoingClient(IGeneralization relation) {
        return relation.getSubType();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IGeneralization relation) {
        return relation.getSuperType();
    }
}
