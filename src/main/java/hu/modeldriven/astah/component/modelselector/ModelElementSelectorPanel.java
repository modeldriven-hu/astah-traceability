package hu.modeldriven.astah.component.modelselector;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.util.function.Consumer;

public class ModelElementSelectorPanel extends AbstractModelElementSelectorPanel {

    private final transient JDialog parentDialog;
    private final transient IPackage rootPackage;

    private final transient Consumer<INamedElement> callback;

    public ModelElementSelectorPanel(JDialog parentDialog, IPackage rootPackage, Consumer<INamedElement> callback) {
        super();
        this.parentDialog = parentDialog;
        this.rootPackage = rootPackage;
        this.callback = callback;
        initUIComponents();
    }

    private void initUIComponents() {

        DefaultTreeModel treeModel = new DefaultTreeModel(
                new ModelElementTreeNode(null, rootPackage));

        packageTree.setModel(treeModel);
        packageTree.setCellRenderer(new ModelElementTreeNodeRenderer());
        packageTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        this.okButton.addActionListener(actionEvent -> {

            ModelElementTreeNode node = (ModelElementTreeNode)
                    packageTree.getLastSelectedPathComponent();

            if (node != null) {
                this.callback.accept(node.model());
            }

            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

        this.cancelButton.addActionListener(actionEvent -> {
            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });
    }

}
