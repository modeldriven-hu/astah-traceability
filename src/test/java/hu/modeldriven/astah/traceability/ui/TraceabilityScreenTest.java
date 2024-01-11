package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.api.TestAstah;
import hu.modeldriven.astah.core.Astah;
import hu.modeldriven.astah.traceability.layout.DummyTraceabilityModel;
import hu.modeldriven.astah.traceability.layout.TraceabilityModel;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TraceabilityScreenTest {

    public TraceabilityScreenTest() {
    }

    public void show() {
        JFrame frame = new JFrame();

        EventBus eventBus = new EventBus();
        Astah astah = new TestAstah();
        TraceabilityPanel panel = new TraceabilityPanel(frame, eventBus, astah);

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
