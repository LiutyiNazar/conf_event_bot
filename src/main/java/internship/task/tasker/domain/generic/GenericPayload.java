package internship.task.tasker.domain.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.domain.plain.models.ElementPlainImpl;
import internship.task.tasker.interfaces.Payload;

import java.util.List;


public class GenericPayload implements Payload{
    @JsonProperty("template_type")
    private String templateType;

    private List<ElementPlainImpl> elements;

    public String getTemplateType() {
        return templateType;
    }

    public GenericPayload setTemplateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public List<ElementPlainImpl> getElements() {
        return elements;
    }

    public GenericPayload setElements(List<ElementPlainImpl> elements) {
        this.elements = elements;
        return this;
    }
}
