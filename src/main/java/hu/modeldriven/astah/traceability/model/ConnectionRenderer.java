package hu.modeldriven.astah.traceability.model;

import java.awt.*;
import java.awt.geom.Point2D;

public interface ConnectionRenderer {

    void render(Graphics2D g, Point startPoint, Point endPoint);

}
