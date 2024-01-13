package hu.modeldriven.astah.traceability.layout.impl.graph;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.List;
import java.util.Map;

public abstract class AbstractGraphNodeBuilder<T extends INamedElement> implements GraphNodeBuilder{

    @Override
    public void build(INamedElement initialElement, Map<String, AstahNode> repository, AstahTheme theme) {

        AstahNode node = new AstahNode(initialElement, theme);
        repository.put(node.id().value(), node);

        for (T relationship : getOutgoingRelationships(initialElement)) {

            INamedElement element = getSupplierForNode(relationship);

            if (!repository.containsKey(element.getId())) {
                build(element, repository, theme);
            }
        }

        for (T relationship : getIncomingRelationships(initialElement)) {

            INamedElement element = getClientForNode(relationship);

            if (!repository.containsKey(element.getId())) {
                build(element, repository, theme);
            }
        }
    }

    protected abstract List<T> getIncomingRelationships(INamedElement element);

    protected abstract List<T> getOutgoingRelationships(INamedElement element);

    protected abstract INamedElement getSupplierForNode(T relationship);

    protected abstract INamedElement getClientForNode(T relationship);

}
