package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.model.Layout;
import hu.modeldriven.astah.traceability.model.TraceabilityModel;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TraceabilityPanel extends JPanel {

    private TraceabilityModel model;
    private Layout layout;

    public TraceabilityPanel(TraceabilityModel model) {
        super();
        this.model = model;
        calculateLayout();
    }

    private void calculateLayout() {
        this.layout = model.layout();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        model.renderer().render(g2, this.layout);
    }
}
