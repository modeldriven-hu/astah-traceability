package hu.modeldriven.astah.component.modelselector;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.function.Consumer;

public class ModelElementSelectorDialog {

    private final Component parentComponent;

    private final IPackage rootPackage;

    private final Consumer<INamedElement> callback;

    public ModelElementSelectorDialog(Component parentComponent, IPackage rootPackage, Consumer<INamedElement> callback) {
        this.parentComponent = parentComponent;
        this.rootPackage = rootPackage;
        this.callback = callback;
    }

    public void show() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(new BorderLayout());

        ModelElementSelectorPanel panel = new ModelElementSelectorPanel(dialog, rootPackage, callback);
        dialog.getContentPane().add(panel);

        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
    }

}
