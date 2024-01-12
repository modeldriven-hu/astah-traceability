package hu.modeldriven.astah.traceability.layout.impl.render;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TextLabel {

    private final String label;

    public TextLabel(String label) {
        this.label = label;
    }

    public Dimension size() {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        // Get FontMetrics for the specified font
        FontMetrics fontMetrics = graphics.getFontMetrics();

        // Calculate the width and height of the text
        int textWidth = fontMetrics.stringWidth(label);
        int textHeight = fontMetrics.getHeight();

        return new Dimension(textWidth, textHeight);
    }

}
