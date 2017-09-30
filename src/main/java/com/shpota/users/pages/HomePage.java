package com.shpota.users.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.shpota.users.domain.User;
import com.shpota.users.pages.forms.AddForm;
import com.shpota.users.pages.forms.SearchForm;
import com.shpota.users.service.UsersService;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import java.util.ArrayList;
import java.util.List;

@WicketHomePage
@AuthorizeInstantiation("USER")
public class HomePage extends WebPage {

    @SpringBean
    private UsersService service;

    public HomePage(PageParameters parameters) {
        super(parameters);

        List<User> users = new ArrayList<>();
        if (parameters.isEmpty()) {
            users.addAll(service.getUsers());
        } else {
            List<StringValue> nameSearch = parameters.getValues("nameSearch");
            StringValue nameValue = nameSearch.get(0);
            String name = nameValue.toString();
            users.addAll(service.getUsersByName(name));
        }
        add(usersList(users));
        add(new SearchForm("searchForm"));
        add(new AddForm("addForm"));
    }

    private ListView<User> usersList(List<User> users) {
        return new ListView<User>("users", users) {
            public void populateItem(ListItem<User> item) {
                final User user = item.getModelObject();
                item.add(nameLabel(user));
                item.add(deleteLink(item));
                item.add(editLink(item));
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

            private Link<Void> editLink(ListItem<User> item) {
                return new Link<Void>("editLink") {
                    @Override
                    public void onClick() {
                        int userId = item.getModelObject().getId();
                        PageParameters parameters = new PageParameters();
                        parameters.add("id", userId);
                        setResponsePage(EditPage.class, parameters);
                    }
                };
            }
        };
    }
}
