package hu.modeldriven.astah.component.modelselector;

import hu.modeldriven.astah.api.DummyAstahRepresentation;

import javax.swing.*;

public class TestModelElementSelectorDialog {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            DummyAstahRepresentation testModel = new DummyAstahRepresentation();

            ModelElementSelectorDialog dialog = new ModelElementSelectorDialog(
                    null,
                    testModel.rootPackage(),
                    element -> System.err.println("Selected element " + element.getName()));

            dialog.show();
        });
    }

}
