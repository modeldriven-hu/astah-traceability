package hu.modeldriven.astah.traceability.ui.usecase;

import hu.modeldriven.astah.traceability.layout.impl.DefaultTraceabilityModel;
import hu.modeldriven.astah.traceability.ui.TraceabilityDiagramPanel;
import hu.modeldriven.astah.traceability.ui.event.ModelElementSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Collections;
import java.util.List;

public class UpdateDiagramUseCase implements EventHandler<ModelElementSelectedEvent> {

    private final TraceabilityDiagramPanel diagramPanel;

    public UpdateDiagramUseCase(TraceabilityDiagramPanel diagramPanel){
        this.diagramPanel = diagramPanel;
    }

    @Override
    public void handleEvent(ModelElementSelectedEvent event) {
        this.diagramPanel.setModel(new DefaultTraceabilityModel(event.element()));
        this.diagramPanel.repaint();
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ModelElementSelectedEvent.class);
    }
}
