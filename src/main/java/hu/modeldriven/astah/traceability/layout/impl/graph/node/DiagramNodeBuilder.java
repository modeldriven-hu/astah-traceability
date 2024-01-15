package hu.modeldriven.astah.traceability.layout.impl.graph.node;

import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.DiagramRelationshipImpl;
import hu.modeldriven.astah.core.IDiagramRelationship;

import java.util.ArrayList;
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
        return Arrays.asList(element.getDiagrams())
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
