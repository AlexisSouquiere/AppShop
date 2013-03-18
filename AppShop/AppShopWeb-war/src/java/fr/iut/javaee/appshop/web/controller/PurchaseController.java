package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Purchase;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.PurchaseServiceLocal;
import fr.iut.javaee.appshop.service.local.UserServiceLocal;
import fr.iut.javaee.appshop.web.service.soap.client.CreditCardValidator_Service;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Audrey
 */
@ManagedBean(name = "purchaseController")
@SessionScoped
public class PurchaseController implements Serializable
{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CreditCardValidator/CreditCardValidator.wsdl")
    private CreditCardValidator_Service service;
    
    @EJB
    private PurchaseServiceLocal purchaseService;
    
    private Purchase purchase = new Purchase();    
    private String numberCard = new String();
       
    @ManagedProperty(value = "#{userController.user}")
    private Users user;
    
    @ManagedProperty(value = "#{applicationController.application}")
    private Application application;
    
    @PostConstruct
    public void init()
    {
        purchase = new Purchase();  
        numberCard = new String();
    }
       
    public List<Purchase> findAll()
    {
        return purchaseService.findAll();
    }
    
    public void doVerification()
    {   
        try {                      
            purchase.setPurchaseUser(user); 
            purchase.setPurchaseApplication(application);
            purchase.setPurchaseDate(new Date());            
            purchase.setPurchasePrice(application.getApplicationPrice());

            purchaseService.persist(purchase);

            //After persit, redirect in the servlet for download.          
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            context.getExternalContext().setRequest(request);
            context.getExternalContext().redirect(request.getContextPath()+ 
                    "/applications/downloads/action=DlApp?app=" + application.getApplicationId() +
                    "&user=" + user.getUserId());        
        } 
        catch (IOException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public Purchase getPurchase()
    {
        return purchase;
    }
    
    public String getNumberCard()
    {
        return numberCard;
    }
    
    public void setNumberCard(String num) {
        this.numberCard = num;
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

    public boolean validation(java.lang.String card) {
        fr.iut.javaee.appshop.web.service.soap.client.CreditCardValidator port = service.getCreditCardValidatorPort();
        return port.validation(card);
    }            
}
