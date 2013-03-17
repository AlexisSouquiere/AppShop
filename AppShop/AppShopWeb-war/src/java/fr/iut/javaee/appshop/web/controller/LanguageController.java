package fr.iut.javaee.appshop.web.controller;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "languageController")
@SessionScoped
public class LanguageController implements Serializable
{ 
    public void changeCountryLocale(String language)
    {
        Locale locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
