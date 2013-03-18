<%@page import="fr.iut.javaee.appshop.service.local.PlatformServiceLocal"%>
<%@page import="fr.iut.javaee.appshop.commons.Platform"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add An Application</title>
        <link href="../../../resources/css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="../../../resources/js/bootstrap.js" rel="stylesheet" media="screen">
        <style type="text/css">
            body { padding-top: 60px; padding-bottom: 40px; }
        </style>
    </head>
    <body>
        <jsp:include page="/admin/template/common/commonHeader.xhtml"/>
        <div class="container">
            <div class="row">
                <div class="span6">
                    <h2>Add An Application</h2>
                    <form action="action=addApp" method="POST">
                        <label>Name : </label><input type="text" name="name" value="" size="25"/><br>
                        <label>WebSite :</label><input type="text" name="website" value="" size="25" /><br>
                        <label>Version : </label><input type="text" name="version" value="" size="25" /><br>
                        <label>Platform :</label>
                        <select name="platformId">
                            <c:forEach var="platform" begin="0" items="${requestScope.platformInstanceList}">
                                <option value="${platform.platformId}">${platform.platformName}&nbsp;&nbsp;${platform.platformVersion}</option>
                            </c:forEach>                           
                        </select>       

                        <br>
                        <label>Price : </label><input type="text" name="price" value="" size="25" /><br>
                        <label>Editor : </label>
                        <select name="editorId">
                            <c:forEach var="editor" begin="0" items="${requestScope.editorInstanceList}">
                                <option value="${editor.editorId}">${editor.editorName}</option>
                            </c:forEach>
                        </select>
                        </br></br>
                        <input type="submit" value="ADD" name="btnaddApp" class="btn btn-success" />
                        <input type="reset" value="RESET" name="btnreset" class="btn btn-warning"/>
                        <a href="index.xhtml"><input type="button" value="HOME" name="btnHome" class="btn btn-primary" /></a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/template/common/commonFooter.xhtml"/>
</body>
</html>
