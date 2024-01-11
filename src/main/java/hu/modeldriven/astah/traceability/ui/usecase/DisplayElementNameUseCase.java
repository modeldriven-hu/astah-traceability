package hu.modeldriven.astah.traceability.ui.usecase;

import hu.modeldriven.astah.traceability.ui.event.ModelElementSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class DisplayElementNameUseCase implements EventHandler<ModelElementSelectedEvent> {

    private final JTextField nameField;

    public DisplayElementNameUseCase(JTextField nameField) {
        this.nameField = nameField;
    }

    @Override
    public void handleEvent(ModelElementSelectedEvent event) {
        this.nameField.setText(event.element().getName());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ModelElementSelectedEvent.class);
    }
}
