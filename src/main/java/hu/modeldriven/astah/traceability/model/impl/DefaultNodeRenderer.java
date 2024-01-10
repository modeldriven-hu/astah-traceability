package hu.modeldriven.astah.traceability.model.impl;

import hu.modeldriven.astah.traceability.model.NodeRenderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DefaultNodeRenderer implements NodeRenderer {

    private final String name;

    public DefaultNodeRenderer(String name) {
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
    public Rectangle2D preferredBounds() {

        int margin = 5;

        Rectangle2D bounds = new TextLabel(name).size();

        int width = margin + (int) bounds.getWidth() + margin;
        int height = margin + (int) bounds.getHeight() + margin;

        return new Rectangle2D.Double(0, 0, width, height);
    }

}
