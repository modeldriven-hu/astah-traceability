package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.model.NodeRenderer;
import hu.modeldriven.astah.traceability.model.TraceabilityModel;

import javax.swing.JPanel;
import java.awt.*;

public class TraceabilityPanel extends JPanel {

    private final TraceabilityModel model;

    public TraceabilityPanel(TraceabilityModel model){
        super();
        this.model = model;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;

        NodeRenderer renderer = model.rootNode().renderer();
        renderer.render(g2, new Rectangle(100,100, 300,300));
    }
}
