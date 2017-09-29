package com.shpota.users.pages;

import com.shpota.users.User;
import com.shpota.users.UsersService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import java.util.List;

public class EditPage extends WebPage {
    private int userId;

    @SpringBean
    private UsersService service;

    public EditPage(PageParameters parameters) {
        super(parameters);
        List<StringValue> idParameters = parameters.getValues("id");
        StringValue idValue = idParameters.get(0);
        userId = idValue.toInt();
        add(new EditForm("editForm"));
    }

    private class EditForm extends Form<EditForm> {
        private FormComponent lastName;
        private FormComponent firstName;
        private FormComponent middleName;

        public EditForm(String id) {
            super(id);
            User oldUser = service.getUserById(userId);
            lastName = new TextField("lastName", new PropertyModel(oldUser, "lastName"));
            firstName = new TextField("firstName", new PropertyModel(oldUser, "firstName"));
            middleName = new TextField("middleName", new PropertyModel(oldUser, "middleName"));
            add(lastName);
            add(firstName);
            add(middleName);
            add(new Link<Void>("cancelLink") {
                @Override
                public void onClick() {
                    setResponsePage(HomePage.class);
                }
            });
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
}
