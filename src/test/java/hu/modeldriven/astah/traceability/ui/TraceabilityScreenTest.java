package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.api.DummyAstahRepresentation;
import hu.modeldriven.astah.core.AstahRepresentation;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TraceabilityScreenTest {

    public TraceabilityScreenTest() {
    }

    public void show() {
        JFrame frame = new JFrame();

        EventBus eventBus = new EventBus();
        AstahRepresentation astahRepresentation = new DummyAstahRepresentation();
        TraceabilityPanel panel = new TraceabilityPanel(frame, eventBus, astahRepresentation);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TraceabilityScreenTest screen = new TraceabilityScreenTest();
        screen.show();
    }

}
