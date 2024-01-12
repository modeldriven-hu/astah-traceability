package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.layout.Connection;
import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.TraceabilityModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class TraceabilityDiagramPanel extends JPanel {

    private TraceabilityModel model;

    public TraceabilityDiagramPanel() {
        super();
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                onMouseReleased(e);
            }
        });
    }

    public void onMouseReleased(MouseEvent e) {
        if (this.model != null && e.getButton() == MouseEvent.BUTTON1) {

            Point2D point = new Point2D.Double(e.getX(), e.getY());

            Node node = this.model.layout().findNodeByLocation(point);
            if (node != null) {
                this.model.layout().select(node);
            }

            Connection connection = this.model.layout().findConnectionByLocation(point);
            if (connection != null) {
                this.model.layout().select(connection);
            }

            if (node == null && connection == null) {
                this.model.layout().deselectAll();
            }

            this.repaint();
        }
    }

    public void setModel(TraceabilityModel model) {
        this.model = model;
        this.setPreferredSize(this.model.layout().size());
        this.invalidate();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (this.model != null) {
            model.renderer().render((Graphics2D) g);
        }
    }
}
