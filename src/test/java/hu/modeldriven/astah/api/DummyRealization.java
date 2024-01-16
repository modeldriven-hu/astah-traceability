package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IRealization;

public class DummyRealization extends DummyNamedElement implements IRealization {

    private final INamedElement client;

    private final INamedElement supplier;


    public DummyRealization(String name, INamedElement client, INamedElement supplier){
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
