package hu.modeldriven.astah.traceability.model;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface ConnectionRenderer {

    void render(Graphics2D g, Path path);

    Dimension labelSize();

}
