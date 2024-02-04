package hu.modeldriven.astah.core;

import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.Arrays;

public class AstahNamedElement {

    private final INamedElement namedElement;

    public AstahNamedElement(INamedElement namedElement){
        this.namedElement = namedElement;
    }

    public String asLog(){
        StringBuilder sb = new StringBuilder();

        sb.append(namedElement.getName());
        sb.append(Arrays.toString(namedElement.getClass().getInterfaces()));

        return sb.toString();
    }

}
