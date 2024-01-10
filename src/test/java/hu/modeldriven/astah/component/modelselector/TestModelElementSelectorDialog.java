package hu.modeldriven.astah.component.modelselector;

import hu.modeldriven.astah.api.TestModel;

import javax.swing.*;

public class TestModelElementSelectorDialog {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            TestModel testModel = new TestModel();

            ModelElementSelectorDialog dialog = new ModelElementSelectorDialog(
                    null,
                    testModel.rootElement(),
                    element -> System.err.println("Selected element " + element.getName()));

            dialog.show();
        });
    }

}
