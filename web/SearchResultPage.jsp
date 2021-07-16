<%-- 
    Document   : Header
    Created on : Apr 11, 2019, 8:37:36 PM
    copyright by fu hub
    link group https://www.facebook.com/groups/498752080529382/
--%>
<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">                
                    <c:forEach items="${list}" var="x">
                        <div class="tittle">
                             <a href="DetailControl?id=${x.id}">      
                                ${x.title}
                            </a>
                        </div>
                        <div class="image_search">
                           <img src="${x.image}" alt=""/>
                        </div>
                        <div class="text_search">
                          ${x.shortDes}
                        </div>
                        <br>
                    </c:forEach>
                    <div class="paging">
                         <c:if test="${maxPage < 1}">
                            <h3>Not Found !!</h3>
                        </c:if>
                        <c:if test="${maxPage > 1}">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==index?"active":""}" href="SearchControl?index=${i}&txtSearch=${txt}">${i}</a>
                            </c:forEach>
                        </c:if>
                    </div>
                </div> 
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>

    </body>
</html>
