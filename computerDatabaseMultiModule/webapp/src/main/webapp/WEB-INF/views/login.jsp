<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
	<head>
		<title>Login</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	
	<body style="margin: 10% 5% 10%;">	    
	   <h1>Login</h1>
	     
	     <c:if test="${param.error == 'true'}">
	         <div style="color:red;margin:10px 0px;">
	                Login Failed<br />
	                Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	         </div>
	    </c:if>
	     
	    <a class="btn btn-info" href="register">Register</a>
	     	       
	   <h3>Enter user name and password:</h3>  
	     
	   <form name='f' action="j_spring_security_check" method='POST'>			
			
			<input type="text" id="username" name="username" th:placeholder="Username or email" class="form-control" /> <br/> 
			<input type="password"  th:placeholder="Password" id="password" name="password" class="form-control" /> <br />
				
			<button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit" th:text="Login">Login</button>
	  </form>
	</body>
</html>