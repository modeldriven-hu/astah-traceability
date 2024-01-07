package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.model.DummyTraceabilityModel;
import hu.modeldriven.astah.traceability.model.TraceabilityModel;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TraceabilityScreen {

    public TraceabilityScreen() {
    }

    public void show() {
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
