package hu.modeldriven.astah.traceability.ui;

import hu.modeldriven.astah.traceability.layout.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class TraceabilityDiagramPanel extends JPanel {

    private final TraceabilityDiagramActionListener listener;
    private final JPopupMenu popupMenu;

    private TraceabilityModel model;

    public TraceabilityDiagramPanel(TraceabilityDiagramActionListener listener) {
        super();
        this.listener = listener;
        this.popupMenu = createMenu();
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                selectOnMousePress(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                displayPopupOnMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                displayPopupOnMousePress(e);
            }
        });
    }

    private JPopupMenu createMenu() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Select in Structure Tree");
        menuItem.addActionListener(this::onSelectInStructurePressed);
        menu.add(menuItem);
        return menu;
    }

    private void onSelectInStructurePressed(ActionEvent actionEvent) {
        Identifiable element = this.model.layout().selection().selectedElement();

        if (element != null) {
            listener.onElementSelectInTreeRequested(element.id().value());
        }
    }

    private void displayPopupOnMousePress(MouseEvent e) {
        if (this.model != null && this.model.layout().selection().hasSelection() && e.isPopupTrigger()) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private void selectOnMousePress(MouseEvent e) {
        if (this.model != null && e.getButton() == MouseEvent.BUTTON1) {

            Point2D point = new Point2D.Double(e.getX(), e.getY());

            Layout layout = this.model.layout();
            LayoutSelection selection = layout.selection();

            Node node = layout.findNodeByLocation(point);
            if (node != null) {
                selection.select(node);
            }

            Connection connection = layout.findConnectionByLocation(point);
            if (connection != null) {
                selection.select(connection);
            }

            if (node == null && connection == null) {
                selection.deselectAll();
            }

            this.repaint();
        }
    }

    public void setModel(TraceabilityModel model) {
        this.model = model;
        this.setPreferredSize(this.model.layout().size());
        this.invalidate();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (this.model != null) {
            model.renderer().render((Graphics2D) g);
        }
    }
}
