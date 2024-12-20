package hu.modeldriven.astah.traceability.layout.impl.core;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.view.IProjectViewManager;
import com.change_vision.jude.api.inf.view.IViewManager;
import hu.modeldriven.astah.core.AstahRepresentation;
import hu.modeldriven.astah.core.AstahRuntimeException;

public class DefaultAstahRepresentation implements AstahRepresentation {

    @Override
    public IPackage rootPackage() {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            return projectAccessor.getProject();
        } catch (Exception e) {
            throw new AstahRuntimeException(e);
        }
    }

    @Override
    public void selectModelElement(String id) {
        try {
            AstahAPI api = AstahAPI.getAstahAPI();

            ProjectAccessor projectAccessor = api.getProjectAccessor();
            INamedElement[] elements = projectAccessor.findElements(iNamedElement -> iNamedElement.getId().equals(id));

            if (elements.length == 1) {
                IViewManager manager = api.getViewManager();
                IProjectViewManager projectViewManager = manager.getProjectViewManager();
                projectViewManager.showInStructureTree(elements[0]);
            }

        } catch (Exception e) {
            throw new AstahRuntimeException(e);
        }
    }


}
