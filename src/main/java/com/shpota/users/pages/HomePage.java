package com.shpota.users.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.shpota.users.User;
import com.shpota.users.UsersService;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
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
        add(new ListView<User>("users", users) {
            public void populateItem(final ListItem<User> item) {
                final User data = item.getModelObject();
                item.add(new Label("lastName", data.getLastName()));
                item.add(new Label("firstName", data.getFirstName()));
                item.add(new Label("middleName", data.getMiddleName()));
            }
        });
    }
}
