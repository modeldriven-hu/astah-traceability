package hu.modeldriven.astah.traceability.layout.impl.render;

import hu.modeldriven.astah.traceability.layout.NodeRenderer;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

public class AstahNodeRenderer implements NodeRenderer {

    private final AstahNode node;

    private final String label;
    private final Dimension labelSize;

    private final AstahTheme theme;

    public AstahNodeRenderer(AstahNode node, AstahTheme theme) {
        this.node = node;
        this.label = node.name();
        this.labelSize = new TextLabel(label).size();
        this.theme = theme;
    }

    @Override
    public void render(Graphics2D g, Rectangle2D bounds) {

        if (node.isSelected()) {
            g.setColor(theme.getSelectedNodeBackgroundColor(node));
        } else {
            g.setColor(theme.getNodeBackgroundColor(node));
        }

        g.fill(bounds);

        Image image = theme.getNodeIcon(node);
        g.drawImage(image, (int)bounds.getX(), (int)bounds.getY(), null);

        if (node.isSelected()) {
            g.setColor(theme.getSelectedNodeLabelColor(node));
        } else {
            g.setColor(theme.getNodeLabelColor(node));
        }

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
