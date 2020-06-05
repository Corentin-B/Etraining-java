<!DOCTYPE html>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
	<head>
		<title><fmt:message key="label.title.computerdatabase" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="resources/css/main.css" rel="stylesheet" media="screen">
	</head>
	<body>
	    <header class="navbar navbar-inverse navbar-fixed-top">
	        <div class="container">
				<a class="navbar-brand" href="dashboard"> <fmt:message key="label.applicationname" /></a>
	        </div>
	    </header>
	    <section id="main">
	        <div class="container">
	            <div class="row">
	                <div class="col-xs-8 col-xs-offset-2 box">
	                    <div class="label label-default pull-right">
	                        Id : <c:out value="${computer.id}"></c:out>
	                    </div>
	                    <h1><fmt:message key="label.editcomputer" /></h1>
	                      <c:choose>
						    <c:when test="${success == true}">
		                  		<div class="alert alert-success">
								  <strong><fmt:message key="label.success.success" /></strong> <c:out value="${updateComputerName}"></c:out> <fmt:message key="label.success.updatedatabase" />
								</div>    
							</c:when>
						</c:choose>
						<c:choose>
						    <c:when test="${success == false}">
						    	<div class="alert alert-danger">
								  <strong><fmt:message key="label.error.failure" /></strong> <c:out value="${updateComputerName}"></c:out> <fmt:message key="label.error.cantbeupdate" />
								</div>    
							</c:when>
						</c:choose>
	                    <form action="editcomputer" method="POST">
	                        <input type="hidden" value="${computer.id}" id="computerId" name="computerId"/>
	                        <fieldset>
	                            <div class="form-group">
	                                <label for="computerName"> <fmt:message key="label.computername" /></label>
	                                <input type="text" class="form-control" id="computerName" name="computerName" 
	                                placeholder="${computer.name}" value="${computer.name}">
	                            </div>
	                            <div class="form-group">
	                                <label for="introduced"> <fmt:message key="label.introduceddate" /></label>
	                                <input type="date" class="form-control" id="introduced" name="introduced" 
	                                placeholder="${computer.introduced}" value="${computer.introduced}" min="1970-01-02" max="2038-01-19">
	                            </div>
	                            <div class="form-group">
	                                <label for="discontinued"> <fmt:message key="label.discontinueddate" /></label>
	                                <input type="date" class="form-control" id="discontinued" name="discontinued" 
	                                placeholder="${computer.discontinued}" value="${computer.discontinued}" min="1970-01-02" max="2038-01-19">
	                            </div>
	                            <div class="form-group">
	                                <label for="companyId"> <fmt:message key="label.company" /></label>
	                                <select	class="form-control" name="companyId" id="companyId">
		                                <option value="${computer.company.id}" selected>
		                                	<c:out value="${computer.company.name}"></c:out>
		                                </option>
										<c:forEach items="${companyList}" var="company">
											<option value="${company.id}">
												<c:out value="${company.name}"></c:out>
											</option>
										</c:forEach>
	                                </select>
	                            </div>            
	                        </fieldset>
	                        <div class="actions pull-right">
	                            <input type="submit" value="<fmt:message key="label.button.edit" />" class="btn btn-primary">
	                            <fmt:message key="label.or" />
	                            <a href="dashboard" class="btn btn-default"><fmt:message key="label.button.cancel" /></a>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	</body>
</html>