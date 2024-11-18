package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.AstahNamedElement;
import hu.modeldriven.astah.core.DiagramRelationshipImpl;
import hu.modeldriven.astah.core.IDiagramRelationship;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DiagramNodeBuilder extends AbstractNodeBuilder<IDiagramRelationship> {

    @Override
    public List<IDiagramRelationship> getOutgoingRelationships(INamedElement element) {
        return Collections.emptyList();
    }

    @Override
    public List<IDiagramRelationship> getIncomingRelationships(INamedElement element) {

        var diagrams = new AstahNamedElement(element).getDiagrams();

        return diagrams
                .stream()
                .map(diagram -> new DiagramRelationshipImpl(diagram, element))
                .collect(Collectors.toList());
    }

    @Override
    public INamedElement getOutgoingSupplier(IDiagramRelationship relationship) {
        return null;
    }

    @Override
    public INamedElement getIncomingClient(IDiagramRelationship relationship) {
        return relationship.getClient();
    }
}
