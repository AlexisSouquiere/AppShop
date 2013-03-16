package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Purchase;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.PurchaseServiceLocal;
import fr.iut.javaee.appshop.service.local.UserServiceLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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
    /*@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_33412/AppShopWeb-war/ValidatorWS.wsdl")
    private ValidatorWS_Service service;*/
    
    @EJB
    private PurchaseServiceLocal purchaseService;
    
    private Purchase purchase = new Purchase();    
    private String numberCard = new String();
       
    @ManagedProperty(value = "#{memberController.member}")
    private Users member;
    
    @ManagedProperty(value = "#{applicationController.application}")
    private Application application;
    
    @PostConstruct
    public void init()
    {
        purchase = new Purchase();  
        numberCard = new String();
    }
    
    
    public void doVerification()
    {   
        try {                      
            purchase.setPurchaseUser(member); 
            purchase.setPurchaseApplication(application);
            purchase.setPurchaseDate(new Date());            
            purchase.setPurchasePrice(application.getApplicationPrice());

            purchaseService.persist(purchase);

            //After persit, redirect in the servlet for download.          
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.setAttribute("purchaseApplicationId", application.getApplicationId());
            request.setAttribute("purchaseUserId", member.getUserId());
            context.getExternalContext().redirect(request.getContextPath()+ "/DownloadControllerServlet/action=DlApp");        
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
    
    public Users getMember() {
        return member;
    }

    public Application getApplication() {
        return application;
    }

    public void setMember(Users member) {
        this.member = member;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    
    private boolean validation(java.lang.String card) {       
        
       /* ValidatorWS_Service service = new ValidatorWS_Service();
        fr.iut.javaee.appshop.web.service.soap.client.ValidatorWS port = service.getValidatorWSPort();
        return port.validation(card);*/
        return true;
    }
 
            
}
