package hu.modeldriven.astah.traceability.layout.impl.graph.connection;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.AstahNamedElement;
import hu.modeldriven.astah.core.DiagramRelationshipImpl;
import hu.modeldriven.astah.core.IDiagramRelationship;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;
import hu.modeldriven.astah.traceability.layout.impl.render.AstahTheme;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiagramConnectionBuilder extends AbstractConnectionBuilder<IDiagramRelationship> {

    public DiagramConnectionBuilder(Map<String, AstahNode> nodes, AstahTheme theme) {
        super(nodes, theme);
    }

    @Override
    public List<IDiagramRelationship> getIncomingRelationships(INamedElement element) {
        return new AstahNamedElement(element).getDiagrams()
                .stream()
                .map(diagram -> new DiagramRelationshipImpl(diagram, element))
                .collect(Collectors.toList());
    }

    @Override
    protected List<IDiagramRelationship> getOutgoingRelationships(INamedElement element) {
        return Collections.emptyList();
    }

    @Override
    protected INamedElement getIncomingClient(IDiagramRelationship relation) {
        return relation.getClient();
    }

    @Override
    protected INamedElement getIncomingSupplier(IDiagramRelationship relation) {
        return relation.getSupplier();
    }

    @Override
    protected INamedElement getOutgoingClient(IDiagramRelationship relation) {
        // Not relevant
        return null;
    }

    @Override
    protected INamedElement getOutgoingSupplier(IDiagramRelationship relation) {
        // Not relevant
        return null;
    }
}
