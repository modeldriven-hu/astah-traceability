package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.model.DummyTraceabilityModel;
import hu.modeldriven.astah.traceability.model.TraceabilityModel;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TraceabilityScreen {

    public TraceabilityScreen(){
    }

    public void show(){
        TraceabilityModel model = new DummyTraceabilityModel();
        TraceabilityPanel panel = new TraceabilityPanel(model);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TraceabilityScreen screen = new TraceabilityScreen();
        screen.show();
    }

}
