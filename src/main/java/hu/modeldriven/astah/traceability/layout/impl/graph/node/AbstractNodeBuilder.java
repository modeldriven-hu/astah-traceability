package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

abstract class AbstractNodeBuilder<T extends INamedElement> implements NodeBuilder {
    public void build(INamedElement currentElement, Map<String, AstahNode> store, BiConsumer<INamedElement, Map<String, AstahNode>> callback) {

        for (T relationship : getOutgoingRelationships(currentElement)) {

            INamedElement supplier = getOutgoingSupplier(relationship);

            if (!store.containsKey(supplier.getId())) {
                callback.accept(supplier, store);
            }
        }

        for (T relationship : getIncomingRelationships(currentElement)) {

            INamedElement client = getIncomingClient(relationship);

            if (!store.containsKey(client.getId())) {
                callback.accept(client, store);
            }
        }
    }

    protected abstract List<T> getOutgoingRelationships(INamedElement element);

    protected abstract List<T> getIncomingRelationships(INamedElement element);

    protected abstract INamedElement getOutgoingSupplier(T relationship);

    protected abstract INamedElement getIncomingClient(T relationship);
}

