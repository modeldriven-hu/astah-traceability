package hu.modeldriven.astah.traceability.layout.impl.render;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Picture {

    private final Icon icon;

    public Picture(Icon icon) {
        this.icon = icon;
    }

    public Image asImage() {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();

            BufferedImage image = gc.createCompatibleImage(icon.getIconWidth(), icon.getIconHeight());

            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            return image;
        }
    }
}
