package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.model.*;

import java.util.ArrayList;
import java.util.List;

public class DummyClass extends DummyNamedElement implements IClass {

    List<IGeneralization> generalizations = new ArrayList<>();
    List<IGeneralization> specializations = new ArrayList<>();
    List<IAttribute> attributes = new ArrayList<>();

    public DummyClass(String name) {
        super(name);
    }

    public void addGeneralization(IGeneralization generalization) {
        this.generalizations.add(generalization);
    }

    public void addSpecialization(IGeneralization generalization) {
        this.specializations.add(generalization);
    }

    public void addAttribute(IAttribute attribute) {
        this.attributes.add(attribute);
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public IAttribute[] getAttributes() {
        return attributes.toArray(new IAttribute[0]);
    }

    @Override
    public IOperation[] getOperations() {
        return new IOperation[0];
    }

    @Override
    public IClass[] getNestedClasses() {
        return new IClass[0];
    }

    @Override
    public IGeneralization[] getGeneralizations() {
        return generalizations.toArray(new IGeneralization[0]);
    }

    @Override
    public IGeneralization[] getSpecializations() {
        return specializations.toArray(new IGeneralization[0]);
    }

    @Override
    public IClassifierTemplateParameter[] getTemplateParameters() {
        return new IClassifierTemplateParameter[0];
    }

    @Override
    public ITemplateBinding[] getTemplateBindings() {
        return new ITemplateBinding[0];
    }

    @Override
    public IPort[] getPorts() {
        return new IPort[0];
    }

    @Override
    public void setAbstract(boolean b) throws InvalidEditingException {

    }

    @Override
    public void setLeaf(boolean b) throws InvalidEditingException {

    }

    @Override
    public void setActive(boolean b) throws InvalidEditingException {

    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }
}
