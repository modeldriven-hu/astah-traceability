package hu.modeldriven.astah.traceability.layout.impl.render;

import hu.modeldriven.astah.traceability.layout.NodeRenderer;
import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AstahNodeRenderer implements NodeRenderer {

    private static final int MARGIN = 10;
    private static final int PADDING_ICON_TEXT = 5;
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

        Color backgroundColor;
        Color borderColor;
        Color labelColor;

        if (node.isSelected()) {
            backgroundColor = theme.getSelectedNodeBackgroundColor(node);
            borderColor = theme.getSelectedNodeBorderColor(node);
            labelColor = theme.getSelectedNodeLabelColor(node);
        } else {
            backgroundColor = theme.getNodeBackgroundColor(node);
            borderColor = theme.getNodeBorderColor(node);
            labelColor = theme.getNodeLabelColor(node);
        }

        g.setColor(backgroundColor);
        g.fill(bounds);

        g.setColor(borderColor);
        g.draw(bounds);

        Image image = theme.getNodeIcon(node);
        g.drawImage(image, (int) bounds.getX() + MARGIN, (int) bounds.getY() + MARGIN, null);

        float posX = (float) (bounds.getX() + MARGIN + image.getWidth(null) + PADDING_ICON_TEXT);
        float posY = (float) (bounds.getY() + bounds.getHeight() / 2);

        FontMetrics metric = g.getFontMetrics(g.getFont());
        g.setColor(labelColor);

        // Strings are drawn not at the top left position but on the baseline, 2 is a magic constants :D
        g.drawString(label, posX, posY + metric.getAscent() - metric.getDescent() - metric.getLeading() - 2);
    }

    @Override
    public Dimension size() {

        int iconSize = theme.getNodeIcon(this.node).getWidth(null);

        int width = MARGIN + iconSize + PADDING_ICON_TEXT + (int) labelSize.getWidth() + MARGIN;
        int height = MARGIN + iconSize + MARGIN;

        return new Dimension(width, height);
    }

}
