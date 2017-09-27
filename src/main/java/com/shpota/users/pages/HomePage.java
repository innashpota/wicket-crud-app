package com.shpota.users.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.shpota.users.User;
import com.shpota.users.UsersService;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
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

    private class SearchForm extends Form<SearchForm> {
        private String nameSearch;

        SearchForm(String id) {
            super(id);
            setModel(new CompoundPropertyModel<>(this));
            add(new FeedbackPanel("feedback"));
            add(new RequiredTextField<String>("nameSearch"));
        }

        @Override
        protected void onSubmit() {
            PageParameters parameters = new PageParameters();
            parameters.add("nameSearch", nameSearch);
            setResponsePage(HomePage.class, parameters);
        }
    }

    private class AddForm extends Form<AddForm> {
        AddForm(String id) {
            super(id);
            add(new Link<Void>("homeLink") {

                @Override
                public void onClick() {
                    setResponsePage(HomePage.class);
                }
            });
        }

        @Override
        protected void onSubmit() {
            setResponsePage(AddPage.class);
        }
    }
}
