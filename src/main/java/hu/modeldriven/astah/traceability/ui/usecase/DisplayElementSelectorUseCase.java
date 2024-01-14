package hu.modeldriven.astah.traceability.ui.usecase;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.component.modelselector.ModelElementSelectorDialog;
import hu.modeldriven.astah.core.AstahException;
import hu.modeldriven.astah.core.AstahRepresentation;
import hu.modeldriven.astah.traceability.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.astah.traceability.ui.event.ModelElementSelectedEvent;
import hu.modeldriven.astah.traceability.ui.event.ModelElementSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayElementSelectorUseCase implements EventHandler<ModelElementSelectionRequestedEvent>, Consumer<INamedElement> {

    private final Component parent;
    private final EventBus eventBus;

    private final AstahRepresentation astah;

    public DisplayElementSelectorUseCase(Component parent, EventBus eventBus, AstahRepresentation astah) {
        this.parent = parent;
        this.eventBus = eventBus;
        this.astah = astah;
    }

    @Override
    public void handleEvent(ModelElementSelectionRequestedEvent event) {
        try {
            ModelElementSelectorDialog dialog = new ModelElementSelectorDialog(parent, astah.rootPackage(), this);
            dialog.show();
        } catch (AstahException e) {
            eventBus.publish(new ExceptionOccurredEvent(e));
        }
    }

    @Override
    public void accept(INamedElement namedElement) {
        eventBus.publish(new ModelElementSelectedEvent(namedElement));
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ModelElementSelectionRequestedEvent.class);
    }

}
