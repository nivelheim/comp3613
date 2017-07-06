<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%!  
	public void jspInit() { 
		ServletConfig config = getServletConfig(); 
 	} 
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>COMP3613 Assignment 2</title>
<LINK REL=STYLESHEET
      HREF="mainstyle.css"
      TYPE="text/css">
</head>
<body>

<div id="header"> 
	<H1>COMP 3613 Assignment 2</H1>
	<H3>Ki Jun Joseph Jung, A00918606</H3>
</div>

<div id="menuwrap">
	<ul id="menu">
		<li><a href="PageLoader?pageparam=loader">Main Page</a></li>
		<li><a href="PageLoader?pageparam=about">About</a></li>
		<li><a href="PageLoader?pageparam=history">History</a></li>
	</ul>
</div>

<h4>${htitle}</h4>
<p>${emptyqueries}</p>

<table>
	<tr>
		<td>${hcol1}</td>
		<td>${hcol2}</td>
	</tr>
<c:forEach var="query" items="${history}" varStatus="status">
	<tr>
		<td>${query}</td>	
		<td>${stamps[status.index]}</td>	
	</tr>
</c:forEach>
</table>

</body>
</html>