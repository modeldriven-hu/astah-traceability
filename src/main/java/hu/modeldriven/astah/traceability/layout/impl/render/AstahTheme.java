package hu.modeldriven.astah.traceability.layout.impl.render;

import hu.modeldriven.astah.traceability.layout.impl.AstahNode;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class AstahTheme {

    public Color getSelectedNodeBackgroundColor(AstahNode node){
        return Color.YELLOW;
    }

    public Color getNodeBackgroundColor(AstahNode node){
        return new Color(77, 170, 109, 100);
    }

    public Color getSelectedNodeLabelColor(AstahNode node){
        return Color.BLACK;
    }

    public Color getNodeLabelColor(AstahNode node){
        return Color.BLACK;
    }

    public Color getConnectionEdgeColor(){
        return Color.BLACK;
    }

    public Color getSelectedConnectionEdgeColor(){
        return Color.BLUE;
    }

    public Color getConnectionLabelColor(){
        return Color.BLACK;
    }

    public Color getSelectedConnectionLabelColor(){
        return Color.RED;
    }

}
