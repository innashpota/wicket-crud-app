package com.shpota.users.pages;

import com.shpota.users.User;
import com.shpota.users.UsersService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddPage extends WebPage {

    @SpringBean
    private UsersService service;

    public AddPage(final PageParameters parameters) {
        super(parameters);
        add(new SaveForm("saveForm"));
    }

    private class SaveForm extends Form<SaveForm> {
        private String lastName;
        private String firstName;
        private String middleName;

        SaveForm(String id) {
            super(id);
            setModel(new CompoundPropertyModel<>(this));
            add(new FeedbackPanel("feedback"));
            add(new RequiredTextField<String>("lastName"));
            add(new RequiredTextField<String>("firstName"));
            add(new RequiredTextField<String>("middleName"));
            add(new Link<Void>("cancelLink") {
                @Override
                public void onClick() {
                    setResponsePage(HomePage.class);
                }
            });
        }

        @Override
        protected void onSubmit() {
            service.createUser(new User(lastName, firstName, middleName));
            setResponsePage(HomePage.class);
        }
    }
}
