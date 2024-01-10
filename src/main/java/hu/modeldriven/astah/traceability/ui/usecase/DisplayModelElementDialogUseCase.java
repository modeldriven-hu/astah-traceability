package hu.modeldriven.astah.traceability.ui.usecase;

import hu.modeldriven.astah.traceability.ui.event.ModelElementSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;

public class DisplayModelElementDialogUseCase implements EventHandler<ModelElementSelectionRequestedEvent> {

    private final Component parent;
    private final EventBus eventBus;

    public DisplayModelElementDialogUseCase(Component parent, EventBus eventBus) {
        this.parent = parent;
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(ModelElementSelectionRequestedEvent event) {
        
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ModelElementSelectionRequestedEvent.class);
    }
}
