package hu.modeldriven.astah.core;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AstahNamedElement {

    private final INamedElement namedElement;

    public AstahNamedElement(INamedElement namedElement) {
        this.namedElement = namedElement;
    }

    public String asLog() {
        return namedElement.getName() + Arrays.toString(namedElement.getClass().getInterfaces());
    }

    public List<IDiagram> getDiagrams() {
        try {

            var list = new ArrayList<IDiagram>();

            if (namedElement.getDiagrams() != null) {
                Collections.addAll(list, namedElement.getDiagrams());
            }

            for (var diagram : allDiagrams()) {

                if (list.contains(diagram)) {
                    continue;
                }

                for (var presentation : diagram.getPresentations()) {
                    if (presentation.getModel() == namedElement) {
                        list.add(diagram);
                        break;
                    }

                    // handle IBD diagrams where a presentation element's model is an IAttribute
                    // typed with the named element
                    if (presentation.getModel() instanceof IAttribute attribute){
                        if (attribute.getType() == namedElement){
                            list.add(diagram);
                            break;
                        }
                    }
                }

            }

            return list;
        } catch (Exception e) {
            throw new AstahRuntimeException(e);
        }
    }

    private List<IDiagram> allDiagrams() {
        try {
            var list = new ArrayList<IDiagram>();

            var projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            var currentProject = projectAccessor.getCurrentProject();
            var diagrams = currentProject.getDiagrams();

            // add all diagrams owned by a package

            if (diagrams != null) {
                Collections.addAll(list, diagrams);
            }

            // add all diagrams owned by a model element which is not a package (like IBDs)

            var allElements = projectAccessor.findElements(INamedElement.class);

            if (allElements != null) {
                for (var element : allElements) {
                    if (element.getDiagrams() != null) {
                        Collections.addAll(list, element.getDiagrams());
                    }
                }
            }

            return list;
        } catch (Exception e) {
            throw new AstahRuntimeException(e);
        }
    }
}
