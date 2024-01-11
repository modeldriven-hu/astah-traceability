package hu.modeldriven.astah.component.modelselector;

import hu.modeldriven.astah.api.TestAstah;

import javax.swing.*;

public class TestModelElementSelectorDialog {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            TestAstah testModel = new TestAstah();

            ModelElementSelectorDialog dialog = new ModelElementSelectorDialog(
                    null,
                    testModel.rootPackage(),
                    element -> System.err.println("Selected element " + element.getName()));

            dialog.show();
        });
    }

}
