package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.core.Astah;
import hu.modeldriven.astah.traceability.layout.TraceabilityModel;
import hu.modeldriven.astah.traceability.ui.event.DiagramRefreshRequestedEvent;
import hu.modeldriven.astah.traceability.ui.event.DisplayConfigurationDialogRequestedEvent;
import hu.modeldriven.astah.traceability.ui.event.ModelElementSelectionRequestedEvent;
import hu.modeldriven.astah.traceability.ui.usecase.DisplayElementSelectorUseCase;
import hu.modeldriven.astah.traceability.ui.usecase.DisplayElementNameUseCase;
import hu.modeldriven.astah.traceability.ui.usecase.UpdateDiagramUseCase;
import hu.modeldriven.core.eventbus.EventBus;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class TraceabilityPanel extends AbstractTraceabilityPanel {

    private final Component parentComponent;
    private final EventBus eventBus;
    private final TraceabilityDiagramPanel diagramPanel;

    private final Astah astah;

    public TraceabilityPanel(Component parentComponent, EventBus eventBus, Astah astah) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
        this.astah = astah;
        this.diagramPanel = new TraceabilityDiagramPanel();
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
    }

    // Only for testing
    public void setModel(TraceabilityModel model) {
        this.diagramPanel.setModel(model);
    }

}
