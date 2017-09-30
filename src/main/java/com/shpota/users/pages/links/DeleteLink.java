package com.shpota.users.pages.links;

import com.shpota.users.domain.User;
import com.shpota.users.pages.HomePage;
import com.shpota.users.service.UsersService;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class DeleteLink extends Link<Void> {
    private ListItem<User> item;

    @SpringBean
    private UsersService service;

    public DeleteLink(String id, ListItem<User> item) {
        super(id);
        this.item = item;
    }

    @Override
    public void onClick() {
        int userId = item.getModelObject().getId();
        service.deleteUser(userId);
        setResponsePage(HomePage.class);
    }
}
