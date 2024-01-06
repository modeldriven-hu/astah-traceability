package hu.modeldriven.astah.traceability.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public interface NodeRenderer {

    void render(Graphics2D g, Rectangle bounds);

}
