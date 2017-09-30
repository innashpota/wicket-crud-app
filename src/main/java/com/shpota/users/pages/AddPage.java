package com.shpota.users.pages;

import com.shpota.users.pages.forms.SaveForm;
import com.shpota.users.service.UsersService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddPage extends WebPage {

    @SpringBean
    private UsersService service;

    public AddPage(PageParameters parameters) {
        super(parameters);
        add(new SaveForm("saveForm"));
    }
}
