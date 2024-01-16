package hu.modeldriven.astah.traceability.layout.impl.render;

import com.change_vision.jude.api.inf.model.*;
import hu.modeldriven.astah.core.IDiagramRelationship;
import hu.modeldriven.astah.core.ImageLoader;
import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.util.Arrays;

public class AstahTheme {

    private final Image generalizationImage;

    public AstahTheme() {
        this.generalizationImage = ImageLoader.loadImage("/icons/generalization.png");
    }

    public Image getNodeIcon(AstahNode node) {
        return new Picture(UIManager.getIcon("FileView.fileIcon")).asImage();
    }

    public Image getLabelIcon(AstahConnection connection) {

        if (connection.namedElement() instanceof IGeneralization) {
            return generalizationImage;
        }

        return new Picture(UIManager.getIcon("FileView.fileIcon")).asImage();
    }

    public String getNodeName(AstahNode node) {
        return node.name();
    }

    public String getConnectionName(AstahConnection connection) {
        INamedElement namedElement = connection.namedElement();

        if (namedElement instanceof IDependency) {

            IDependency dependency = (IDependency) namedElement;

            if (hasStereotype(dependency, "Allocate")) {
                return "Allocate";
            }

            if (hasStereotype(dependency, "DeriveRqt")) {
                return "Derived requirement";
            }

            if (hasStereotype(dependency, "Copy")) {
                return "Copy";
            }

            if (hasStereotype(dependency, "Satisfy")) {
                return "Satisfy";
            }

            if (hasStereotype(dependency, "Verify")) {
                return "Verify";
            }

            if (hasStereotype(dependency, "Refine")) {
                return "Refine";
            }

            if (hasStereotype(dependency, "Trace")) {
                return "Trace";
            }

            return "Dependency";
        }

        if (namedElement instanceof IAssociation) {
            return "Association";
        }

        if (namedElement instanceof IGeneralization) {
            return "Generalization";
        }

        if (namedElement instanceof IAssociationClass) {
            return "AssociationClass";
        }

        if (namedElement instanceof IRealization) {
            return "Realization";
        }

        if (namedElement instanceof IUsage) {
            return "Usage";
        }

        if (namedElement instanceof IDiagramRelationship) {
            return "Diagram";
        }

        return namedElement.getClass().toGenericString();
    }

    private boolean hasStereotype(INamedElement namedElement, String stereotype) {
        return Arrays.asList(namedElement.getStereotypes()).contains(stereotype);
    }


    public Color getSelectedNodeBackgroundColor(AstahNode node) {
        return Color.YELLOW;
    }

    public Color getNodeBackgroundColor(AstahNode node) {
        return new Color(77, 170, 109, 100);
    }

    public Color getSelectedNodeLabelColor(AstahNode node) {
        return Color.BLACK;
    }

    public Color getNodeLabelColor(AstahNode node) {
        return Color.BLACK;
    }

    public Color getConnectionEdgeColor() {
        return Color.BLACK;
    }

    public Color getSelectedConnectionEdgeColor() {
        return Color.BLUE;
    }

    public Color getConnectionLabelColor() {
        return Color.BLACK;
    }

    public Color getSelectedConnectionLabelColor() {
        return Color.RED;
    }

    public Color getSelectedNodeBorderColor(AstahNode node) {
        return Color.BLUE;
    }

    public Color getNodeBorderColor(AstahNode node) {
        return Color.BLACK;
    }


}
