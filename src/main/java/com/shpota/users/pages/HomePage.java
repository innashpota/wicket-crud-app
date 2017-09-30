package com.shpota.users.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.shpota.users.domain.User;
import com.shpota.users.pages.forms.AddForm;
import com.shpota.users.pages.forms.SearchForm;
import com.shpota.users.pages.table.UsersList;
import com.shpota.users.service.UsersService;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
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
        add(new UsersList("users", users));
        add(new SearchForm("searchForm"));
        add(new AddForm("addForm"));
    }
}
