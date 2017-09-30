package com.shpota.users.pages.table;

import com.shpota.users.domain.User;
import com.shpota.users.pages.links.DeleteLink;
import com.shpota.users.pages.links.EditLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import java.util.List;

public class UsersList extends ListView<User> {
    public UsersList(String id, List<User> users) {
        super(id, users);
    }

    public void populateItem(ListItem<User> item) {
        final User user = item.getModelObject();
        item.add(nameLabel(user));
        item.add(new DeleteLink("deleteLink", item));
        item.add(new EditLink("editLink", item));
    }

    private Label nameLabel(User user) {
        return new Label(
                "name",
                user.getLastName() + " " + user.getFirstName() + " " + user.getMiddleName()
        );
    }
}
