<!DOCTYPE html>

<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="resources/css/main.css" rel="stylesheet" media="screen">
	</head>
	<body>
	    <header class="navbar navbar-inverse navbar-fixed-top">
	        <div class="container">
	            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
	        </div>
	    </header>
	    <section id="main">
	        <div class="container">
	            <div class="row">
	                <div class="col-xs-8 col-xs-offset-2 box">
	                    <div class="label label-default pull-right">
	                        Id : <c:out value="${computer.id}"></c:out>
	                    </div>
	                    <h1>Edit Computer</h1>
	                      <c:choose>
						    <c:when test="${Success == true}">
		                  		<div class="alert alert-success">
								  <strong>Success!</strong> <c:out value="${UpdateComputerName}"></c:out> updated to database
								</div>    
							</c:when>
						</c:choose>
						<c:choose>
						    <c:when test="${Success == false}">
						    	<div class="alert alert-danger">
								  <strong>Failure !</strong> <c:out value="${UpdateComputerName}"></c:out> can't be updated to database
								</div>    
							</c:when>
						</c:choose>
	                    <form action="editComputer" method="POST">
	                        <input type="hidden" value="${computer.id}" id="computerId" name="computerId"/>
	                        <fieldset>
	                            <div class="form-group">
	                                <label for="computerName">Computer name</label>
	                                <input type="text" class="form-control" id="computerName" name="computerName" 
	                                placeholder="${computer.name}" value="${computer.name}">
	                            </div>
	                            <div class="form-group">
	                                <label for="introduced">Introduced date</label>
	                                <input type="date" class="form-control" id="introduced" name="introduced" 
	                                placeholder="${computer.introduced}" value="${computer.introduced}">
	                            </div>
	                            <div class="form-group">
	                                <label for="discontinued">Discontinued date</label>
	                                <input type="date" class="form-control" id="discontinued" name="discontinued" 
	                                placeholder="${computer.discontinued}" value="${computer.discontinued}">
	                            </div>
	                            <div class="form-group">
	                                <label for="companyId">Company</label>
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
	                            <input type="submit" value="Edit" class="btn btn-primary">
	                            or
	                            <a href="dashboard" class="btn btn-default">Cancel</a>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	</body>
</html>