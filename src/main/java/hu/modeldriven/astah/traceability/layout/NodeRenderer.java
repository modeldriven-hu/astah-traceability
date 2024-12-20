package hu.modeldriven.astah.traceability.layout;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface NodeRenderer {

    void render(Graphics2D g, Rectangle2D bounds);

    Dimension size();

}
