<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="d" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="../../resources/css/bootstrap.css" rel="stylesheet" media="screen">  
         <link href="../../resources/js/bootstrap.js" rel="stylesheet" media="screen">
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
                padding-left: 100px;
            }
            
            .style_fieldset
            {
                border: 1px #006dcc solid; 
                padding: 20px; 
                border-radius: 5px;
                
            }
        </style> 
         <title>Download Application</title>
    </head>
    <body>
        
        <jsp:include page="/template/common/commonHeader.xhtml"/>
        <d:param numberDlTot='${requestScope.numberDl}' numberDlMembers='${requestScope.numberMembers}' membersList="${requestScope.listMembersName}" />
        
            <br>
            <br>
            <h1>The application has been successfully downloaded !</h1>
            <br>
            
            <div style="width: 500px;">
                 
            <fieldset class="style_fieldset">
            <h1> Application </h1>
            <br>
            ID Application : ${requestScope.myApplication.applicationId}
            <br>          
            Application Name : ${requestScope.myApplication.applicationName} 
            <br>           
            Application Date : ${requestScope.myApplication.applicationReleaseDate} 
            <br>
            Application WebSite : ${requestScope.myApplication.applicationWebsite} 
            <br>
            Application Version : ${requestScope.myApplication.applicationVersion}
            <br>
            Application Price : ${requestScope.myApplication.applicationPrice} 
            
          </fieldset>    
            <br>
            <br>
            <br>  
                              
           <fieldset class="style_fieldset">
                <h1> Membre </h1>
                <br>
                <jsp:useBean id="member" scope="request" class="fr.iut.javaee.appshop.commons.Users" />
                Username : <jsp:getProperty name="member" property="userUsername"/>
                 
                 <%--${requestScope.myMember.memberUsername} --%>
                 <br>
                 
                 Mail : <jsp:getProperty name="member" property="userEmail" />
                 <%-- ${requestScope.myMember.memberEmail} --%>
                <br>
            </fieldset>              
                
                                     
            <br>
            <br>
            
            <a href="/AppShopWeb-war/index.xhtml"><input type="button" value="HOME" name="btnHome" class="btn btn-primary" /></a>
        </div>
                
        
    </body>
</html>
<jsp:include page="/template/common/commonFooter.xhtml"/>
