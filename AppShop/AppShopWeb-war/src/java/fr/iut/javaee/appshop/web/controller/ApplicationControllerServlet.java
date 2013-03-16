/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Editor;
import fr.iut.javaee.appshop.commons.Platform;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.EditorServiceLocal;
import fr.iut.javaee.appshop.service.local.PlatformServiceLocal;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Audrey
 */

//@WebServlet(name="ApplicationControllerServlet", urlPatterns={"/ApplicationContollerServlet", "*.admin"}, initParams= { @WebInitParam(name="default_locale", value="en_US")})

@WebServlet(name="ApplicationControllerServlet", urlPatterns={"/ApplicationContollerServlet"})

public class ApplicationControllerServlet extends HttpServlet {
    
  @EJB
  private ApplicationServiceLocal serviceApp; 
  @EJB
  private PlatformServiceLocal servicePlatform;
  @EJB
  private EditorServiceLocal serviceEditor;
  
  
    /** 
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

  
   String servletPath =  request.getRequestURL().toString();    
    
    if (servletPath.contains("add")) {  

     request.setAttribute("platformInstanceList", servicePlatform.findAll()); 
     
     request.setAttribute("editorInstanceList", serviceEditor.findAll()); 


     RequestDispatcher rd = request.getRequestDispatcher("../new.jsp");
     rd.forward(request, response);
     return;  
    }

    else if (servletPath.contains("index")) {

     RequestDispatcher rd = request.getRequestDispatcher("/admin/applications/list.xhtml");
     rd.forward(request, response);
     return; 
    }
   
  }
  
    /** 
   * Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

      processRequest(request, response);

  }
  
  /** 
   * Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
   // processRequest(request, response);
      
    String servletPath =  request.getRequestURL().toString();      
       
     if (servletPath.contains("addApp")) {  
       addApp(request, response);
       return;  
      }
     
  }
    
     public void addApp(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException           
  {
        String name = (String)request.getParameter("name");
        String website = (String)request.getParameter("website");
        Date date = new Date();
        String version = (String) request.getParameter("version");
        Double price = (Double) Double.parseDouble(request.getParameter("price").toString());
           
        Application app = new Application();
        app.setApplicationName(name);
        app.setApplicationReleaseDate(date);
        app.setApplicationWebsite(website);
        app.setApplicationVersion(version);
        app.setApplicationPrice(price);
        
        //Object plateform --> id param
        Platform pla = servicePlatform.findOneById(Integer.parseInt(request.getParameter("platformId").toString()));
        //set the platform
        app.setApplicationPlatform(pla);
         
        //Object Editor --> id param
        Editor editor = serviceEditor.findOneById(Integer.parseInt(request.getParameter("editorId").toString()));
        //Set the editor        
        app.setApplicationEditor(editor);
     
        //add the application
        serviceApp.persist(app);
     
    
        RequestDispatcher rd = request.getRequestDispatcher("../list.xhtml");
        rd.forward(request, response);
              
    }
   
    
}
