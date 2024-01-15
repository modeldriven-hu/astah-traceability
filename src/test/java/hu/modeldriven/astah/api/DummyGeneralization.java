package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IGeneralization;

public class DummyGeneralization extends DummyNamedElement implements IGeneralization {

    private IClass superType;
    private IClass subType;

    public DummyGeneralization(String name, IClass superType, IClass subType) {
        super(name);
        this.superType = superType;
        this.subType = subType;
    }

    @Override
    public IClass getSuperType() {
        return superType;
    }

    @Override
    public IClass getSubType() {
        return subType;
    }

    @Override
    public void setSuperType(IClass iClass) throws InvalidEditingException {
        this.superType = iClass;
    }

    @Override
    public void setSubType(IClass iClass) throws InvalidEditingException {
        this.subType = iClass;
    }
}
