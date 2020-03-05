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
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" />
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
	                    <h1>Add Computer</h1>
                    	<c:choose>
						    <c:when test="${Success == true}">
						    	<div class="alert alert-success">
								  <strong>Success!</strong> <c:out value="${newComputerName}"></c:out> added to database
								</div>    
							</c:when>
						</c:choose>
						<c:choose>
						    <c:when test="${Success == false}">
						    	<div class="alert alert-danger">
								  <strong>Failure !</strong> <c:out value="${newComputerName}"></c:out> can't be added to database
								</div>    
							</c:when>
						</c:choose>
	                    <form action="addcomputer" method="POST">
	                        <fieldset>
	                            <div class="form-group">
	                                <label for="computerName">Computer name</label>
	                                <input type="text" class="form-control" name="computerName" id="computerName"
	                                placeholder="mycomputer" required maxlength="255">
	                            </div>
	                            <div class="form-group">
	                                <label for="introduced">Introduced date</label>
	                                <input type="date" class="form-control" name="introduced" id="introduced"
	                                placeholder="1970-01-01" min="1970-01-01" max="2030-01-01">
	                            </div>
	                            <div class="form-group">
	                                <label for="discontinued">Discontinued date</label>
	                                <input type="date" class="form-control" name="discontinued" id="discontinued"
	                                placeholder="1970-01-01" min="1970-01-01" max="2030-01-01">
	                            </div>
	                            <div class="form-group">
	                                <label for="companyId">Company</label>
	                                <select	class="form-control" name="companyId" id="companyId">
	                                <option value="0" selected></option>
										<c:forEach items="${companyList}" var="company">
											<option value="${company.id}">
												<c:out value="${company.name}"></c:out></option>
										</c:forEach>
	                                </select>
	                            </div>                  
	                        </fieldset>
	                        <div class="actions pull-right">
	                            <input type="submit" value="Add" class="btn btn-primary">
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