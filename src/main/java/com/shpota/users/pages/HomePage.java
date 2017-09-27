package com.shpota.users.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.shpota.users.User;
import com.shpota.users.UsersService;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

@WicketHomePage
@AuthorizeInstantiation("USER")
public class HomePage extends WebPage {

    @SpringBean
    private UsersService service;

    public HomePage() {
        List<User> users = new ArrayList<>();
        users.addAll(service.getUsers());
        add(getComponents(users));
        add(new AddForm("addForm"));
    }

    private ListView<User> getComponents(List<User> users) {
        return new ListView<User>("users", users) {
            public void populateItem(final ListItem<User> item) {
                final User user = item.getModelObject();
                item.add(nameLabel(user));
                item.add(deleteLink(item));
            }

            private Label nameLabel(User user) {
                return new Label(
                        "name",
                        user.getLastName() + " " + user.getFirstName() + " " + user.getMiddleName()
                );
            }

            private Link<Void> deleteLink(ListItem<User> item) {
                return new Link<Void>("deleteLink") {
                    @Override
                    public void onClick() {
                        int userId = item.getModelObject().getId();
                        service.deleteUser(userId);
                        setResponsePage(HomePage.class);
                    }
                };
            }
        };
    }

    private class AddForm extends Form<AddForm> {
        AddForm(String id) {
            super(id);
        }

        @Override
        protected void onSubmit() {
            setResponsePage(AddPage.class);
        }
    }
}
