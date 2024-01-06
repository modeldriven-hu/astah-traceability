package hu.modeldriven.astah.traceability.model;

import java.awt.*;

public class DummyConnectionRenderer implements ConnectionRenderer{

    @Override
    public void render(Graphics2D g, Point startPoint, Point endPoint) {
        g.setColor(Color.PINK);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
