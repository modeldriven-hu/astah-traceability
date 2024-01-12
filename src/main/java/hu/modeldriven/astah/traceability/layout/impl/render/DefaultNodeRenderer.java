package hu.modeldriven.astah.traceability.layout.impl.render;

import hu.modeldriven.astah.traceability.layout.Node;
import hu.modeldriven.astah.traceability.layout.NodeRenderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DefaultNodeRenderer implements NodeRenderer {

    private final Node node;

    private final String label;
    private final Dimension labelSize;

    public DefaultNodeRenderer(Node node) {
        this.node = node;
        this.label = node.name();
        this.labelSize = new TextLabel(label).size();
    }

    @Override
    public void render(Graphics2D g, Rectangle2D bounds) {

        if (node.isSelected()) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(new Color(77, 170, 109, 100));
        }

        g.fill(bounds);

        //g.setColor(Color.BLACK);
        //g.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 5f));
        //g.draw(bounds);

        g.setColor(Color.BLACK);

        float posX = (float) (bounds.getX() + 5);
        float posY = (float) (bounds.getY() + 5 + bounds.getHeight() / 2);

        g.drawString(label, posX, posY);
    }

    @Override
    public Dimension size() {

        int margin = 5;

        int width = margin + (int) labelSize.getWidth() + margin;
        int height = margin + (int) labelSize.getHeight() + margin;

        return new Dimension(width, height);
    }

}
