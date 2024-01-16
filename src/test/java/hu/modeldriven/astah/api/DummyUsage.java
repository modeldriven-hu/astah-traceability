package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.IPresentation;

public class DummyUsage extends DummyNamedElement implements IUsage {

    private final INamedElement client;

    private final INamedElement supplier;

    public DummyUsage(String name, INamedElement client, INamedElement supplier){
        super(name);
        this.client = client;
        this.supplier = supplier;
    }

    @Override
    public INamedElement getSupplier() {
        return supplier;
    }

    @Override
    public INamedElement getClient() {
        return client;
    }

}
