package com.shpota.users.pages.forms;

import com.shpota.users.domain.User;
import com.shpota.users.pages.HomePage;
import com.shpota.users.pages.links.HomeLink;
import com.shpota.users.service.UsersService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EditForm extends Form<EditForm> {
    private FormComponent lastName;
    private FormComponent firstName;
    private FormComponent middleName;
    private int userId;

    @SpringBean
    private UsersService service;

    public EditForm(String id, int userId) {
        super(id);
        this.userId = userId;
        User oldUser = service.getUserById(userId);
        lastName = new TextField<>(
                "lastName",
                new PropertyModel<>(oldUser, "lastName")
        );
        firstName = new TextField<>(
                "firstName",
                new PropertyModel<>(oldUser, "firstName")
        );
        middleName = new TextField<>(
                "middleName",
                new PropertyModel<>(oldUser, "middleName")
        );
        add(lastName);
        add(firstName);
        add(middleName);
        add(new HomeLink("cancelLink"));
    }

    @Override
    protected void onSubmit() {
        User user = new User(
                lastName.getInput(),
                firstName.getInput(),
                middleName.getInput()
        );
        service.updateUser(userId, user);
        setResponsePage(HomePage.class);
    }
}
