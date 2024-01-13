package hu.modeldriven.astah.traceability.layout.impl.render;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.traceability.layout.impl.AstahConnection;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AstahTheme {

    public Image getNodeIcon(AstahNode node) {
        return convertIconToImage(UIManager.getIcon("FileView.fileIcon"));
    }

    public Image getLabelIcon(AstahConnection connection) {
        return convertIconToImage(UIManager.getIcon("FileView.fileIcon"));
    }

    public String getConnectionName(AstahConnection connection) {
        INamedElement namedElement = connection.namedElement();

        if (namedElement instanceof IDependency){
            return "Dependency";
        }

        return namedElement.getClass().toGenericString();
    }

    private Image convertIconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
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
