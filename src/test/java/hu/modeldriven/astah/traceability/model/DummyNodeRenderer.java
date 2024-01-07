package hu.modeldriven.astah.traceability.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DummyNodeRenderer implements NodeRenderer {

    private final String name;

    public DummyNodeRenderer(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics2D g, Rectangle bounds) {
        g.setColor(Color.BLUE);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        g.setColor(Color.WHITE);
        g.drawString(name, bounds.x, bounds.y + bounds.height / 2);
    }
}
