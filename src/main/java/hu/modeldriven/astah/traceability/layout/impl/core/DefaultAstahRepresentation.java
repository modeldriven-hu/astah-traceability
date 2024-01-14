package hu.modeldriven.astah.traceability.layout.impl.core;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.view.IProjectViewManager;
import com.change_vision.jude.api.inf.view.IViewManager;
import hu.modeldriven.astah.core.AstahException;
import hu.modeldriven.astah.core.AstahRepresentation;

public class DefaultAstahRepresentation implements AstahRepresentation {

    @Override
    public IPackage rootPackage() throws AstahException {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            return projectAccessor.getProject();
        } catch (Exception e) {
            throw new AstahException(e);
        }
    }


    @Override
    public void selectModelElement(String id) throws AstahException {
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
            throw new AstahException(e);
        }
    }


}
