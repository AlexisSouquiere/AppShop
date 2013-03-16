<%-- 
    Document   : param
    Created on : 13 mars 2013, 10:42:26
    Author     : Audrey
--%>
<%@tag import="fr.iut.javaee.appshop.service.local.impl.DownloadService"%>
<%@tag import="fr.iut.javaee.appshop.service.local.DownloadServiceLocal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag body-content="scriptless"%>
<%@tag description="tag param" pageEncoding="UTF-8"%>

<%@attribute name="numberDlTot" %>
<%@attribute name="numberDlMembers" %>
<%@attribute name="membersList" %>

<div style="float: right; padding-top: 150px; position: absolute; padding-left: 700px;">
    <fieldset class="style_fieldset">
       <br>        
        <h1> Download Stats </h1>
       <br>
       <br>
       Number total of download : ${numberDlTot}
        <br> 
        <br> 
       Number of members who are downloaded this application : ${numberDlMembers} 
        <br>
        <br> 
       List of members (UserName) :  
        <br>
       <c:forEach var="mem" items="${membersList}">
       <ul>   
            <li>${mem}</li>      
       </ul>
       </c:forEach>
       <br>
       <br>
    </fieldset>
   <br>
   <br>
   <br>
</div>   


