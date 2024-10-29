package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.IPresentation;

public class DummyAttribute implements IAttribute {

    private final String name;
    private final IClass type;

    public DummyAttribute(String name, IClass type){
        this.name = name;
        this.type = type;
    }

    @Override
    public IClass getType() {
        return type;
    }

    @Override
    public String getTypeExpression() {
        return "";
    }

    @Override
    public String getQualifiedTypeExpression() {
        return "";
    }

    @Override
    public String getInitialValue() {
        return "";
    }

    @Override
    public boolean isChangeable() {
        return false;
    }

    @Override
    public IAssociation getAssociation() {
        return null;
    }

    @Override
    public IAttribute[] getQualifiers() {
        return new IAttribute[0];
    }

    @Override
    public IMultiplicityRange[] getMultiplicity() {
        return new IMultiplicityRange[0];
    }

    @Override
    public boolean isDerived() {
        return false;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public boolean isAggregate() {
        return false;
    }

    @Override
    public boolean isEnable() {
        return false;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public void setType(IClass iClass) throws InvalidEditingException {

    }

    @Override
    public void setTypeExpression(String s) throws InvalidEditingException {

    }

    @Override
    public void setQualifiedTypeExpression(String s) throws InvalidEditingException {

    }

    @Override
    public void setInitialValue(String s) throws InvalidEditingException {

    }

    @Override
    public void setChangeable(boolean b) throws InvalidEditingException {

    }

    @Override
    public void setAggregation() throws InvalidEditingException {

    }

    @Override
    public void setComposite() throws InvalidEditingException {

    }

    @Override
    public void setAggregationKind(AggregationKind aggregationKind) throws InvalidEditingException {

    }

    @Override
    public void setStatic(boolean b) throws InvalidEditingException {

    }

    @Override
    public void setMultiplicity(int[][] ints) throws InvalidEditingException {

    }

    @Override
    public void setMultiplicityStrings(String[][] strings) throws InvalidEditingException {

    }

    @Override
    public void setDerived(boolean b) throws InvalidEditingException {

    }

    @Override
    public void setEnable(boolean b) throws InvalidEditingException {

    }

    @Override
    public String getNavigability() {
        return "";
    }

    @Override
    public void setNavigability(String s) throws InvalidEditingException {

    }

    @Override
    public IConnector[] getConnectors() {
        return new IConnector[0];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName(String s) {
        return "";
    }

    @Override
    public String getFullNamespace(String s) {
        return "";
    }

    @Override
    public IConstraint[] getConstraints() {
        return new IConstraint[0];
    }

    @Override
    public IDependency[] getSupplierDependencies() {
        return new IDependency[0];
    }

    @Override
    public IDependency[] getClientDependencies() {
        return new IDependency[0];
    }

    @Override
    public IRealization[] getSupplierRealizations() {
        return new IRealization[0];
    }

    @Override
    public IRealization[] getClientRealizations() {
        return new IRealization[0];
    }

    @Override
    public IUsage[] getSupplierUsages() {
        return new IUsage[0];
    }

    @Override
    public IUsage[] getClientUsages() {
        return new IUsage[0];
    }

    @Override
    public String getDefinition() {
        return "";
    }

    @Override
    public boolean isPublicVisibility() {
        return false;
    }

    @Override
    public boolean isProtectedVisibility() {
        return false;
    }

    @Override
    public boolean isPrivateVisibility() {
        return false;
    }

    @Override
    public boolean isPackageVisibility() {
        return false;
    }

    @Override
    public IDiagram[] getDiagrams() {
        return new IDiagram[0];
    }

    @Override
    public void setName(String s) throws InvalidEditingException {

    }

    @Override
    public void setDefinition(String s) throws InvalidEditingException {

    }

    @Override
    public void setVisibility(String s) throws InvalidEditingException {

    }

    @Override
    public String getAlias1() {
        return "";
    }

    @Override
    public String getAlias2() {
        return "";
    }

    @Override
    public void setAlias1(String s) throws InvalidEditingException {

    }

    @Override
    public void setAlias2(String s) throws InvalidEditingException {

    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public IElement getOwner() {
        return null;
    }

    @Override
    public IElement getContainer() {
        return null;
    }

    @Override
    public IElement[] getContainers() {
        return new IElement[0];
    }

    @Override
    public IComment[] getComments() {
        return new IComment[0];
    }

    @Override
    public String[] getStereotypes() {
        return new String[0];
    }

    @Override
    public boolean hasStereotype(String s) {
        return false;
    }

    @Override
    public void removeStereotype(String s) throws InvalidEditingException {

    }

    @Override
    public void addStereotype(String s) throws InvalidEditingException {

    }

    @Override
    public ITaggedValue[] getTaggedValues() {
        return new ITaggedValue[0];
    }

    @Override
    public String getTaggedValue(String s) {
        return "";
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public String getTypeModifier() {
        return "";
    }

    @Override
    public IPresentation[] getPresentations() throws InvalidUsingException {
        return new IPresentation[0];
    }

    @Override
    public void setTypeModifier(String s) throws InvalidEditingException {

    }

    @Override
    public IHyperlink[] getHyperlinks() {
        return new IHyperlink[0];
    }

    @Override
    public IHyperlink createFileHyperlink(String s, String s1, String s2) throws InvalidEditingException {
        return null;
    }

    @Override
    public IHyperlink createURLHyperlink(String s, String s1) throws InvalidEditingException {
        return null;
    }

    @Override
    public IHyperlink createElementHyperlink(IElement iElement, String s) throws InvalidEditingException {
        return null;
    }

    @Override
    public void deleteHyperlink(IHyperlink iHyperlink) throws InvalidEditingException {

    }
}
