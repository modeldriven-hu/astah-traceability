package hu.modeldriven.astah.core;

import com.change_vision.jude.api.inf.model.IAssociation;
import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IClass;

import java.util.ArrayList;
import java.util.List;

public class AstahClass {

    private final IClass clazz;

    public AstahClass(IClass clazz) {
        this.clazz = clazz;
    }

    /**
     * Returns the associations of a class
     * <p>
     * https://members.change-vision.com/javadoc/astah-api/8_2_0/api/en/doc/reference.html
     */
    public List<IAssociation> associations() {
        List<IAssociation> associations = new ArrayList<>();
        IAttribute[] attributes = clazz.getAttributes();
        for (IAttribute iAttribute : attributes) {
            IAssociation association = iAttribute.getAssociation();
            if (association == null) {
                continue;
            }
            associations.add(association);
        }
        return associations;
    }

}
