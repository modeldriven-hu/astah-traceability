package hu.modeldriven.astah.traceability.ui;

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
        System.setProperty("org.graphstream.ui", "swing");
    }

    public void show(){
        Graph graph = new SingleGraph("Tutorial", false, true);
        graph.setAttribute("ui.stylesheet", "url('file:///home/zsolt/node.css')");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        graph.addNode("A").setAttribute("ui.label", "Engine");
        graph.addNode("B").setAttribute("ui.label", "Motor");
        graph.addEdge("E1", "A", "B").setAttribute("ui.label", "Composition");
        graph.addNode("C").setAttribute("ui.label", "Class Diagram");
        graph.addEdge("E2", "A", "C").setAttribute("ui.label", "diagramming");

        SwingViewer viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ViewPanel viewPanel = (ViewPanel)viewer.addDefaultView(false);
        viewPanel.enableMouseOptions();
        panel.add(viewPanel, BorderLayout.CENTER);

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
