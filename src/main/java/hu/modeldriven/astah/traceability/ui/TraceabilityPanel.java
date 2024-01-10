package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.model.TraceabilityModel;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;
import java.awt.Dimension;

public class TraceabilityPanel extends AbstractTraceabilityPanel{

    private final EventBus eventBus;
    private final TraceabilityDiagramPanel diagramPanel;

    public TraceabilityPanel(){
        this.eventBus = new EventBus();
        this.diagramPanel = new TraceabilityDiagramPanel();
        initUIComponents();
    }

    private void initUIComponents() {
        scrollPanel.setPreferredSize(new Dimension(500,500));
        scrollPanel.setViewportView(diagramPanel);
    }

    public void setModel(TraceabilityModel model){
        this.diagramPanel.setModel(model);
    }

}
