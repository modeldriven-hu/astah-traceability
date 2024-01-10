package hu.modeldriven.astah.component.modelselector;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class ModelElementTreeNode implements TreeNode {

    private final TreeNode parent;
    private final List<TreeNode> children;

    private final INamedElement modelElement;

    public ModelElementTreeNode(TreeNode parent, INamedElement modelElement) {
        this.parent = parent;
        this.children = new ArrayList<>();
        this.modelElement = modelElement;
        createChildren(modelElement);
    }

    private void createChildren(INamedElement modelElement){
        if (modelElement instanceof IPackage) {
            for (INamedElement child : ((IPackage) modelElement).getOwnedElements()) {
                    children.add(new ModelElementTreeNode(this, child ));
            }
        }
    }

    @Override
    public TreeNode getChildAt(int i) {
        return children.get(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        return children.indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isPackage() {
        return this.modelElement instanceof IPackage;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children);
    }

    public String name() {
        return this.modelElement.getName();
    }

    public INamedElement model() {
        return this.modelElement;
    }
}
