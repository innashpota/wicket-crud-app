package com.shpota.users.pages.links;

import com.shpota.users.pages.HomePage;
import org.apache.wicket.markup.html.link.Link;

public class HomeLink extends Link<Void> {
    public HomeLink(String id) {
        super(id);
    }

    @Override
    public void onClick() {
        setResponsePage(HomePage.class);
    }
}
