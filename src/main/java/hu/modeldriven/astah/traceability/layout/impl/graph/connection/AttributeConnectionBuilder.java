package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AttributeConnectionBuilder extends AbstractConnectionBuilder<IAttribute> {

    protected AttributeConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        super(nodes, theme);
    }

    @Override
    protected List<IAttribute> getIncomingRelationships(INamedElement element) {
        return Collections.emptyList();
    }

    @Override
    protected List<IAttribute> getOutgoingRelationships(INamedElement element) {

        if (element instanceof IClass iClass) {

            if (iClass.getAttributes() == null || iClass.getAttributes().length == 0) {
                return Collections.emptyList();
            }

            return List.of(iClass.getAttributes());
        }

        return Collections.emptyList();
    }

    @Override
    protected INamedElement getIncomingClient(IAttribute relation) {
        return null;
    }

    @Override
    protected INamedElement getIncomingSupplier(IAttribute relation) {
        return null;
    }

    @Override
    protected INamedElement getOutgoingClient(IAttribute relation) {
        return (INamedElement) relation.getOwner();
    }

    @Override
    protected INamedElement getOutgoingSupplier(IAttribute relation) {
        return relation.getType();
    }
}
