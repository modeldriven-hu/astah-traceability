package hu.modeldriven.astah.component.modelselector;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;

public class ModelElementTreeNodeRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        Component component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        ModelElementTreeNode node = (ModelElementTreeNode) value;

        if (component instanceof JLabel) {
            ((JLabel) component).setText(node.name());

            if (node.isPackage()) {
                ((JLabel) component).setIcon(this.getDefaultOpenIcon());
            }
        }

        return component;
    }
}
