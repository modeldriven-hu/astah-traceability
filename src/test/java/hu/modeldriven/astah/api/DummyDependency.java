package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.model.*;

public class DummyDependency extends DummyNamedElement implements IDependency {

    private final INamedElement client;

    private final INamedElement supplier;


    public DummyDependency(String name, INamedElement client, INamedElement supplier){
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
