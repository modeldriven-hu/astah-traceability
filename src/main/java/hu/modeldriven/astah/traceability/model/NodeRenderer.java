package hu.modeldriven.astah.traceability.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface NodeRenderer {

    void render(Graphics2D g, Rectangle bounds);

}
