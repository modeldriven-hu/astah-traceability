package hu.modeldriven.astah.traceability.model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class DummyNodeRenderer implements NodeRenderer {

    private final String name;

    public DummyNodeRenderer(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics2D g, Rectangle2D bounds) {
        g.setColor(Color.BLUE);
        g.fill(bounds);

        g.setColor(Color.DARK_GRAY);
        g.draw(bounds);

        g.setColor(Color.WHITE);
        g.drawString(name, (float) bounds.getX(), (float) (bounds.getY() + bounds.getHeight() / 2));
    }

    @Override
    public Rectangle2D preferredBounds(){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        // Get FontMetrics for the specified font
        FontMetrics fontMetrics = graphics.getFontMetrics();

        // Calculate the width and height of the text
        int textWidth = fontMetrics.stringWidth(name);
        int textHeight = fontMetrics.getHeight();

        int margin = 5;

        int width = margin + textWidth + margin;
        int height = margin + textHeight + margin;

        return new Rectangle2D.Double(0,0, width, height);
    }

}
