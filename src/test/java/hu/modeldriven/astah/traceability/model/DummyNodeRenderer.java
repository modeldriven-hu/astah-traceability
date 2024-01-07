package hu.modeldriven.astah.traceability.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

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
        g.drawString(name, (float)bounds.getX(), (float)(bounds.getY() + bounds.getHeight() / 2));
    }
}
