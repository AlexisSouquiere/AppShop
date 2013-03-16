<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<jsp:include page="/template/common/commonHeader.xhtml"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD Application</title>
        <link href="../resources/css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="../resources/css/default.css" rel="stylesheet" media="screen">
        <link href="../resources/js/bootstrap.js" rel="stylesheet" media="screen">
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
                padding-left: 100px;
            }
        </style>
    </head>
    <body>
            <br>
            <br>
            <h1>The application has been successfully added !</h1>
            <br>
            <br>
            <br>
            <%= "Details : "%>
            <br>
            <br>
            Name : <%= request.getParameter("name") %>
            <br>
            WebSite : <%= request.getParameter("website") %>
            <br>
            Version : <%= request.getParameter("version") %>
            <br>
            Price : <%= request.getParameter("price") %>
            <br>
            <br>
            <a href="index.xhtml"><input type="button" value="HOME" name="btnHome" class="btn btn-primary" /></a>
      
    </body>
</html>
<jsp:include page="/template/common/commonFooter.xhtml"/>
