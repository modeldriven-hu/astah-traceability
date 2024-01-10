package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.model.Layout;
import hu.modeldriven.astah.traceability.model.TraceabilityModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TraceabilityDiagramPanel extends JPanel {

    private final TraceabilityModel model;
    private Layout layout;

    public TraceabilityDiagramPanel(TraceabilityModel model) {
        super();
        setBackground(Color.WHITE);
        this.model = model;
        this.layout = model.layout();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        model.renderer().render(g2, this.layout);
    }
}