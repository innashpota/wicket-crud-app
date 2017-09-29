package com.shpota.users.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SearchForm extends Form<SearchForm> {
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