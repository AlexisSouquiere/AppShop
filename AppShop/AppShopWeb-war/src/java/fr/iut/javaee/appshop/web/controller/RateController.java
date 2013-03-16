/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Rate;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.RateServiceLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Alexis
 */
@ManagedBean(name = "rateController")
@RequestScoped
public class RateController 
{
    @EJB
    private RateServiceLocal service;
    
    @ManagedProperty(value = "#{userController.user}")
    private Users user;
    
    @ManagedProperty(value = "#{applicationController.application}")
    private Application application;
    
    private int rate = 0;
    private double average = 0.0;
    
    @PostConstruct
    public void findAverageRate()
    {
        average = service.findApplicationRateAverage(application.getApplicationId());
    }
    
    public void rateApplication()
    {
        Rate r = new Rate();
        r.setRate(rate);
        r.setRateApplication(application);
        if(user.getUserId() != null) {
            r.setRateUser(user);
        }
        
        service.persist(r);
        
        List<Rate> rates = application.getRateList();
        rates.add(r);
        application.setRateList(rates);
    }
    
    public void setRate(int rate)
    {
        this.rate = rate;
    }
    
    public int getRate()
    {
        return rate;
    }
    
    public Double getAverage()
    {
        return average;
    }
    
    public Users getUser() {
        return user;
    }

    public Application getApplication() {
        return application;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
