package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.layout.Layout;
import hu.modeldriven.astah.traceability.layout.TraceabilityModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TraceabilityDiagramPanel extends JPanel {

    private TraceabilityModel model;
    private Layout layout;

    public TraceabilityDiagramPanel() {
        super();
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClicked(e);
            }
        });
    }

    public void onMouseClicked(MouseEvent e) {
        if (this.model != null && e.getButton() == MouseEvent.BUTTON1){
            int x = e.getX();
            int y = e.getY();
        }
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

        if (this.layout != null) {
            Graphics2D g2 = (Graphics2D) g;
            model.renderer().render(g2, this.layout);
            // FIXME we might not need to provide layout externally
            // because renderer could do just that
        }
    }
}
