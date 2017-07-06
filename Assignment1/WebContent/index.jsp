<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
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

<% 
	String question = config.getInitParameter("question");
	String inst = config.getInitParameter("inst");
%>

<p><%=question%> <%= new java.util.Date() %> </p>
<p><%=inst%> </p>
<form action=PageLoader method="get">
	<input type="password" name='decryptionCode'/>
    <input type='submit' name='pageparam' value='Start'/>
</form>

</body>
</html>