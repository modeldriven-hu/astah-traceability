package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.model.Layout;
import hu.modeldriven.astah.traceability.model.TraceabilityModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TraceabilityDiagramPanel extends JPanel {

    private TraceabilityModel model;
    private Layout layout;

    public TraceabilityDiagramPanel(TraceabilityModel model) {
        this();
        setModel(model);
    }

    public TraceabilityDiagramPanel() {
        super();
        setBackground(Color.WHITE);
    }

    public void setModel(TraceabilityModel model) {
        this.model = model;
        this.layout = model.layout();
        this.setPreferredSize(this.layout.size());
        this.invalidate();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (this.model != null) {
            Graphics2D g2 = (Graphics2D) g;
            model.renderer().render(g2, this.layout);
        }
    }
}
