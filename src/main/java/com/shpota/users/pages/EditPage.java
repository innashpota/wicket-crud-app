package com.shpota.users.pages;

import com.shpota.users.pages.forms.EditForm;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import java.util.List;

public class EditPage extends WebPage {
    private int userId;

    public EditPage(PageParameters parameters) {
        super(parameters);
        List<StringValue> idParameters = parameters.getValues("id");
        StringValue idValue = idParameters.get(0);
        userId = idValue.toInt();
        add(new EditForm("editForm", userId));
    }
}
