package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IAssociation;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.AstahClass;

import java.util.Collections;
import java.util.List;

public class AssociationNodeBuilder extends AbstractNodeBuilder<IAssociation> {

    @Override
    protected List<IAssociation> getOutgoingRelationships(INamedElement element) {
        if (element instanceof IClass) {
            IClass clazz = (IClass) element;
            return new AstahClass(clazz).associations();
        }

        return Collections.emptyList();
    }

    @Override
    protected List<IAssociation> getIncomingRelationships(INamedElement element) {

        if (element instanceof IClass) {
            IClass clazz = (IClass) element;
            return new AstahClass(clazz).associations();
        }

        return Collections.emptyList();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IAssociation relationship) {
        // FIXME todo
        return null;
    }

    @Override
    protected INamedElement getIncomingClient(IAssociation relationship) {
        // FIXME todo
        return null;
    }


}
