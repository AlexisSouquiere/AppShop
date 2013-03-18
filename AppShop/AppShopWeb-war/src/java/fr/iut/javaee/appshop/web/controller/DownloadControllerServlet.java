/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.controller;

import fr.iut.javaee.appshop.commons.Application;
import fr.iut.javaee.appshop.commons.Download;
import fr.iut.javaee.appshop.commons.Users;
import fr.iut.javaee.appshop.service.local.ApplicationServiceLocal;
import fr.iut.javaee.appshop.service.local.DownloadServiceLocal;
import fr.iut.javaee.appshop.service.local.UserServiceLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Audrey
 */
public class DownloadControllerServlet extends HttpServlet 
{   
    @EJB
    private ApplicationServiceLocal serviceApp; 
    @EJB
    private UserServiceLocal serviceUser;
    @EJB 
    private DownloadServiceLocal serviceDownload;

    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String servletPath =  request.getRequestURL().toString();
        if (servletPath.contains("DlApp")) {  
            doPost(request, response);
            return;
        }
    
        if (servletPath.contains("index")) {
            RequestDispatcher rd = request.getRequestDispatcher("/index.xhtml");
            rd.forward(request, response);
        }
    }
  
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        processRequest(request, response);
    }
  
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        String servletPath =  request.getRequestURL().toString(); 

        if (servletPath.contains("DlApp")) {   
            memberDownloadApp(request, response);
        }
    }
       
    private void memberDownloadApp(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
    {
        RequestDispatcher rd;
        boolean purchase = false;
        Application application;
        
        String userId = (String)request.getParameter("userId");

        String applicationId = (String)request.getParameter("applicationId");
        if(applicationId == null) {
            purchase = true;
            applicationId = (String)request.getParameter("app");
            userId = (String)request.getParameter("user");
        }
        application = serviceApp.findOneById(Integer.parseInt(applicationId));  

        if(application.getApplicationPrice() == 0 || purchase) {
            Download dl = new Download();
            if(userId != "") {
                Users user = serviceUser.findOneById(Integer.parseInt(userId));
                dl.setDownloadUser(user);                
            }
            dl.setDownloadDate(new Date());
            dl.setDownloadApplication(application);
            serviceDownload.persist(dl);
            
            
            Integer num = serviceDownload.findDownloadNumberByApplicationId(application.getApplicationId());
            request.setAttribute("numberDl", num);
            
            List<Users> members = serviceDownload.findMemberByApplicationId(application.getApplicationId());
            
            List<String> membersName = new ArrayList<String>();        
            for(Users mem : members) {
                if(mem != null) {
                    membersName.add(mem.getUserUsername());
                }
            }
            String myListMembers = membersName.toString().replace("[","").replace("]", "");
            request.setAttribute("listMembersName", myListMembers);
            
            Integer numberMembers = members.size();
            request.setAttribute("numberMembers", numberMembers);
            request.setAttribute("myApplication", application);
       
            rd = request.getRequestDispatcher("/download/confirmDownload.jsp");  
        } 
        else {
            if(userId != "") {
                rd = request.getRequestDispatcher("/download/purchase.xhtml");
            }
            else {
                rd = request.getRequestDispatcher("/login.xhtml");                
            }
        }

        rd.forward(request, response);
    }    
}
