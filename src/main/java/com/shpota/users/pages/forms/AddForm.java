package com.shpota.users.pages.forms;

import com.shpota.users.pages.AddPage;
import com.shpota.users.pages.HomePage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;

public class AddForm extends Form<AddForm> {
    public AddForm(String id) {
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