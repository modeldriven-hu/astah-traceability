package hu.modeldriven.astah.traceability.layout;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface ConnectionRenderer {

    void render(Graphics2D g, Path path);

    Dimension labelSize();

}
