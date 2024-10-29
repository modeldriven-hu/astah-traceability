package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AttributeNodeBuilder extends AbstractNodeBuilder<IAttribute> {

    @Override
    protected List<IAttribute> getOutgoingRelationships(INamedElement element) {
        if (element instanceof IClass) {
            IClass clazz = (IClass) element;
            return Arrays.stream(clazz.getAttributes()).toList();
        }

        return Collections.emptyList();
    }

    @Override
    protected List<IAttribute> getIncomingRelationships(INamedElement element) {
        if (element instanceof IClass) {
            IClass clazz = (IClass) element;
            return Arrays.stream(clazz.getAttributes()).toList();
        }

        return Collections.emptyList();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IAttribute relationship) {
        return relationship.getType();
    }

    @Override
    protected INamedElement getIncomingClient(IAttribute relationship) {
        return relationship.getType();
    }
}
