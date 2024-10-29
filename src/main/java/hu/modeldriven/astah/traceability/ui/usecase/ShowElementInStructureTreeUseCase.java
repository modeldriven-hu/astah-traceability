package hu.modeldriven.astah.traceability.ui.usecase;

import hu.modeldriven.astah.core.AstahRepresentation;
import hu.modeldriven.astah.traceability.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.astah.traceability.ui.event.ShowInStructureTreeRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Collections;
import java.util.List;

public class ShowElementInStructureTreeUseCase implements EventHandler<ShowInStructureTreeRequestedEvent> {

    private final AstahRepresentation astah;
    private final EventBus eventBus;

    public ShowElementInStructureTreeUseCase(EventBus eventBus, AstahRepresentation astah) {
        this.eventBus = eventBus;
        this.astah = astah;
    }

    @Override
    public void handleEvent(ShowInStructureTreeRequestedEvent event) {
        try {
            astah.selectModelElement(event.id());
        } catch (Exception e) {
            eventBus.publish(new ExceptionOccurredEvent(e));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ShowInStructureTreeRequestedEvent.class);
    }
}
