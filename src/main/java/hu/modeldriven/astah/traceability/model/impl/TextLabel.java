package hu.modeldriven.astah.traceability.model.impl;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextLabel {

    private final String label;

    public TextLabel(String label){
        this.label = label;
    }

    public Rectangle2D size(){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        // Get FontMetrics for the specified font
        FontMetrics fontMetrics = graphics.getFontMetrics();

        // Calculate the width and height of the text
        int textWidth = fontMetrics.stringWidth(label);
        int textHeight = fontMetrics.getHeight();

        return new Rectangle2D.Double(0,0,textWidth, textHeight);
    }

}
