package com.shpota.users.pages.forms;

import com.shpota.users.domain.User;
import com.shpota.users.pages.HomePage;
import com.shpota.users.pages.links.HomeLink;
import com.shpota.users.service.UsersService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SaveForm extends Form<SaveForm> {
    private String lastName;
    private String firstName;
    private String middleName;

    @SpringBean
    private UsersService service;

    public SaveForm(String id) {
        super(id);
        setModel(new CompoundPropertyModel<>(this));
        add(new FeedbackPanel("feedback"));
        add(new RequiredTextField<String>("lastName"));
        add(new RequiredTextField<String>("firstName"));
        add(new RequiredTextField<String>("middleName"));
        add(new HomeLink("cancelLink"));
    }

    @Override
    protected void onSubmit() {
        service.createUser(new User(lastName, firstName, middleName));
        setResponsePage(HomePage.class);
    }
}
