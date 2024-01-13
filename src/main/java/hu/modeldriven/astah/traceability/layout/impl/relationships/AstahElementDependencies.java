//package hu.modeldriven.astah.traceability.layout.impl.relationships;
//
//import com.change_vision.jude.api.inf.model.IDependency;
//import com.change_vision.jude.api.inf.model.INamedElement;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class AstahElementDependencies extends AbstractAstahElementRelationships<IDependency> {
//
//    @Override
//    protected List<IDependency> getIncomingRelationships(INamedElement element) {
//        return Arrays.asList(element.getSupplierDependencies());
//    }
//
//    @Override
//    public List<IDependency> getOutgoingRelationships(INamedElement element) {
//        return Arrays.asList(element.getClientDependencies());
//    }
//
//    @Override
//    protected INamedElement getIncomingElement(IDependency relation) {
//        return relation.getClient();
//    }
//
//    @Override
//    public INamedElement getOutgoingElement(IDependency relation) {
//        return relation.getSupplier();
//    }
//}
