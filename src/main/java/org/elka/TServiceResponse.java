package org.elka;

import org.restframework.web.annotations.Template;
import org.restframework.web.core.templates.ClassTypes;
import org.restframework.web.core.templates.ServiceTemplateUtils;
import org.restframework.web.core.templates.SpringComponents;

import java.util.List;

@Template(
        templateName = "Service",
        rule = SpringComponents.SERVICE,
        type = ClassTypes.CLASS
)
public interface TServiceResponse <ID, DTO> {
    ServiceTemplateUtils utils = new ServiceTemplateUtils();

    Response<?> insert(DTO entity);
    Response<List<DTO>> getAll();
    boolean removeById(ID id);
    boolean update(ID id, DTO dto);
}
