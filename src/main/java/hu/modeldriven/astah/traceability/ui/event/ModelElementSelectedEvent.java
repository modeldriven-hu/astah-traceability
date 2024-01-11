package hu.modeldriven.astah.traceability.ui.event;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.core.eventbus.Event;

public class ModelElementSelectedEvent implements Event {

    private final INamedElement element;

    public ModelElementSelectedEvent(INamedElement element){
        this.element = element;
    }

    public INamedElement element() {
        return element;
    }
}
