package com.shpota.users.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import com.shpota.users.pages.forms.LoginForm;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@WicketSignInPage
public class LoginPage extends WebPage {

    public LoginPage(PageParameters parameters) {
        super(parameters);

        if (((AbstractAuthenticatedWebSession) getSession()).isSignedIn()) {
            continueToOriginalDestination();
        }
        add(new LoginForm("loginForm"));
    }
}
