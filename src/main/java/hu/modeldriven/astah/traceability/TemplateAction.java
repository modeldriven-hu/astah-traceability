package hu.modeldriven.astah.traceability;


import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;
import hu.modeldriven.astah.core.AstahRepresentation;
import hu.modeldriven.astah.traceability.layout.impl.core.DefaultAstahRepresentation;
import hu.modeldriven.astah.traceability.ui.TraceabilityPanel;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TemplateAction implements IPluginActionDelegate {

    public Object run(IWindow window) throws UnExpectedException {
        JFrame frame = new JFrame();

        EventBus eventBus = new EventBus();
        AstahRepresentation astahRepresentation = new DefaultAstahRepresentation();
        TraceabilityPanel panel = new TraceabilityPanel(frame, eventBus, astahRepresentation);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        return null;
    }


}
