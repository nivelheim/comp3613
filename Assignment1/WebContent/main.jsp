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

<script>
function clicked()
{
	return confirm('clicked');
}
</script>

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

<div class="table">
	<div class="tr">
        <span class="td">${v_id}</span>
        <span class="td">${v_manuf}</span>
        <span class="td">${v_name}</span>
        <span class="td">${v_year}</span>
        <span class="td">${v_mile}</span>
        <span class="td">${v_add}</span>
        <span class="td">${v_price}</span>
        <span class="td"> </span>
        <span class="td"> </span>
    </div>

<c:forEach var="list" items= "${vehicles}">
    <form class="tr" method="get" action="DBManager" style="width: 50px;">
   		<input type="hidden" name="id" value="${list.id}" />
        <span class="td"><input type="text" name="id" value="${list.id}" readonly/></span>
        <span class="td"><input type="text" name="manuf" value="${list.manufacturer}"/></span>
        <span class="td"><input type="text" name="model" value="${list.model}"/></span>
        <span class="td"><input type="text" name="year" value="${list.year}"/></span>
        <span class="td"><input type="text" name="mile" value="${list.mileage}"/></span>
        <span class="td"><input type="text" name="addon" value="${list.addon}"/></span>
        <span class="td"><input type="text" name="price" value="${list.price}"/></span>
        <span class="td"><input type='submit' name='button' value='Update' onclick="return confirm('Please confirm your action: UPDATE')"/></span>
        <span class="td"><input type='submit' name='button' value='Delete' onclick="return confirm('Please confirm your action: DELETE')"/></span>
    </form>   
</c:forEach>
	<form class="tr" method="get" action="DBManager">
	    <span class="td"><input type="text" name="id" value="Autofill" disabled="disabled"/></span>
	    <span class="td"><input type="text" name="manuf" value="${list.manufacturer}"/></span>
	    <span class="td"><input type="text" name="model" value="${list.model}"/></span>
	    <span class="td"><input type="text" name="year" value="${list.year}"/></span>
	    <span class="td"><input type="text" name="mile" value="${list.mileage}"/></span>
	    <span class="td"><input type="text" name="addon" value="${list.addon}"/></span>
	    <span class="td"><input type="text" name="price" value="${list.price}"/></span>
	    <span class="td"><input type='submit' name='button' value='Insert' onclick="return confirm('Please confirm your action: INSERT')"/></span>
	    <span class="td"><input type='reset' name='button' value='Reset'/></span>
    </form>
</div>


</body>
</html>