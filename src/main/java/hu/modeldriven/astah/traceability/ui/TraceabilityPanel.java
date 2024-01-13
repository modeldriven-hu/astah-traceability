package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.core.AstahRepresentation;
import hu.modeldriven.astah.traceability.layout.TraceabilityModel;
import hu.modeldriven.astah.traceability.ui.event.*;
import hu.modeldriven.astah.traceability.ui.usecase.*;
import hu.modeldriven.core.eventbus.EventBus;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class TraceabilityPanel extends AbstractTraceabilityPanel implements TraceabilityDiagramActionListener{

    private final Component parentComponent;
    private final transient EventBus eventBus;
    private final TraceabilityDiagramPanel diagramPanel;

    private final transient AstahRepresentation astah;

    public TraceabilityPanel(Component parentComponent, EventBus eventBus, AstahRepresentation astah) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
        this.astah = astah;
        this.diagramPanel = new TraceabilityDiagramPanel(this);
        initUIComponents();
        initUseCases();
    }

    private void initUIComponents() {
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        scrollPanel.setViewportView(diagramPanel);
        selectButton.addActionListener(this::onSelectButtonPressed);
        refreshButton.addActionListener(this::onRefreshButtonPressed);
        configureButton.addActionListener(this::onConfigureButtonPressed);
    }


    private void onSelectButtonPressed(ActionEvent actionEvent) {
        eventBus.publish(new ModelElementSelectionRequestedEvent());
    }

    private void onRefreshButtonPressed(ActionEvent actionEvent) {
        eventBus.publish(new DiagramRefreshRequestedEvent());
    }

    private void onConfigureButtonPressed(ActionEvent actionEvent) {
        eventBus.publish(new DisplayConfigurationDialogRequestedEvent());
    }

    private void initUseCases() {
        eventBus.subscribe(new DisplayElementSelectorUseCase(parentComponent, eventBus, astah));
        eventBus.subscribe(new DisplayElementNameUseCase(modelElementTextField));
        eventBus.subscribe(new UpdateDiagramUseCase(diagramPanel));
        eventBus.subscribe(new DisplayExceptionUseCase());
        eventBus.subscribe(new ShowElementInStructureTreeUseCase(eventBus, astah));
    }

    // Only for testing
    public void setModel(TraceabilityModel model) {
        this.diagramPanel.setModel(model);
    }

    @Override
    public void onElementSelectInTreeRequested(String id) {
        eventBus.publish(new ShowInStructureTreeRequestedEvent(id));
    }
}
