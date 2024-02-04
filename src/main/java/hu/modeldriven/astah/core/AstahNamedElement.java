package hu.modeldriven.astah.core;

import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;

public class AstahNamedElement {

    private final INamedElement namedElement;

    public AstahNamedElement(INamedElement namedElement) {
        this.namedElement = namedElement;
    }

    public String asLog() {
        return namedElement.getName() + Arrays.toString(namedElement.getClass().getInterfaces());
    }

}
