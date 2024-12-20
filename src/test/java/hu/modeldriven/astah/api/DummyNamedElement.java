package hu.modeldriven.astah.api;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.IPresentation;

import java.util.ArrayList;
import java.util.List;

public class DummyNamedElement implements INamedElement {

    private static int CURRENT_ID = 0;

    private final String id;
    private final String name;
    private final List<IDependency> clientDependencies;

    private final List<IDependency> supplierDependencies;

    private final List<IDiagram> diagrams;

    private final List<IRealization> clientRealizations;

    private final List<IRealization> supplierRealizations;


    private final List<IUsage> clientUsages;

    public DummyNamedElement(String name) {
        this.id = getClass().getCanonicalName() + CURRENT_ID++;
        this.name = name;
        this.clientDependencies = new ArrayList<>();
        this.supplierDependencies = new ArrayList<>();
        this.clientUsages = new ArrayList<>();
        this.diagrams = new ArrayList<>();
        this.clientRealizations = new ArrayList<>();
        this.supplierRealizations = new ArrayList<>();
    }

    public void addClientDependency(IDependency dependency) {
        this.clientDependencies.add(dependency);
    }

    public void addSupplierDependency(IDependency dependency) {
        this.supplierDependencies.add(dependency);
    }

    public void addClientRealization(IRealization realization) {
        this.clientRealizations.add(realization);
    }

    public void addSupplierRealization(IRealization realization) {
        this.supplierRealizations.add(realization);
    }


    public void addDiagram(IDiagram diagram) {
        this.diagrams.add(diagram);
    }

    public void addClientUsage(IUsage usage) {
        this.clientUsages.add(usage);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName(String s) {
        return null;
    }

    @Override
    public String getFullNamespace(String s) {
        return null;
    }

    @Override
    public IConstraint[] getConstraints() {
        return new IConstraint[0];
    }

    @Override
    public IDependency[] getSupplierDependencies() {
        return supplierDependencies.toArray(new IDependency[0]);
    }

    @Override
    public IDependency[] getClientDependencies() {
        return clientDependencies.toArray(new IDependency[0]);
    }

    @Override
    public IRealization[] getSupplierRealizations() {
        return supplierRealizations.toArray(new IRealization[0]);
    }

    @Override
    public IRealization[] getClientRealizations() {
        return clientRealizations.toArray(new IRealization[0]);
    }

    @Override
    public IUsage[] getSupplierUsages() {
        return new IUsage[0];
    }

    @Override
    public IUsage[] getClientUsages() {
        return clientUsages.toArray(new IUsage[0]);
    }

    @Override
    public String getDefinition() {
        return null;
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
        return diagrams.toArray(new IDiagram[0]);
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
        return null;
    }

    @Override
    public String getAlias2() {
        return null;
    }

    @Override
    public void setAlias1(String s) throws InvalidEditingException {

    }

    @Override
    public void setAlias2(String s) throws InvalidEditingException {

    }

    @Override
    public String getId() {
        return id;
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
        return null;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public String getTypeModifier() {
        return null;
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
