package com.shpota.users.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;

public class AddForm extends Form<AddForm> {
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