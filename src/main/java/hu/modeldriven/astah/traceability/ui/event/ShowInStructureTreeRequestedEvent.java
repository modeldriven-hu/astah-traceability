package hu.modeldriven.astah.traceability.ui.event;

import hu.modeldriven.core.eventbus.Event;

public class ShowInStructureTreeRequestedEvent implements Event {

    private final String id;

    public ShowInStructureTreeRequestedEvent(String id){
        this.id = id;
    }

    public String id() {
        return id;
    }
}
