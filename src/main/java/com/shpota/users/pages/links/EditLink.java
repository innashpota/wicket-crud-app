package com.shpota.users.pages.links;

import com.shpota.users.domain.User;
import com.shpota.users.pages.EditPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class EditLink extends Link<Void> {
    private ListItem<User> item;

    public EditLink(String id, ListItem<User> item) {
        super(id);
        this.item = item;
    }

    @Override
    public void onClick() {
        int userId = item.getModelObject().getId();
        PageParameters parameters = new PageParameters();
        parameters.add("id", userId);
        setResponsePage(EditPage.class, parameters);
    }
}
