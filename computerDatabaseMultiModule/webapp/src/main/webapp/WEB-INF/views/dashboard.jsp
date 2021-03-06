
<!DOCTYPE html>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
	<head>
		<title><fmt:message key="label.title.computerdatabase" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
		<!-- Bootstrap -->
		<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="resources/css/main.css" rel="stylesheet" media="screen">
	</head>
	<body>
		<header class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<a class="navbar-brand" href="dashboard"> <fmt:message key="label.applicationname"/></a>
				<a class="btn btn-default pull-right" href="logout">Logout</a>
			</div>
		</header>
	
		<section id="main">
			<div class="container text-center">
				<h1 id="homeTitle">
					<c:out value="${numberComputer}"></c:out> <fmt:message key="label.computersfound"/>
				</h1>
				<div id="actions" class="form-horizontal">
					<div class="pull-left pagination">
						<form id="searchForm" action="#" method="GET" class="form-inline">
							<input type="search" id="searchbox" name="search" class="form-control" placeholder="<fmt:message key="label.searchname" />" /> 
							<input type="submit" id="searchsubmit" value="<fmt:message key="label.button.filterbyname" />" class="btn btn-primary" />
						</form>
					</div>
					<div class="pull-right">
						<a class="btn btn-success" id="addComputer" href="addcomputer"><fmt:message key="label.button.addcomputer" /></a>
						<a class="btn btn-default" id="deleteComputer" href="#" onclick="$.fn.toggleEditMode();"><fmt:message key="label.button.edit" /></a>
					</div>
				</div>
			</div>
	
			<form id="deleteForm" action="dashboard?deleteComputer=deleteComputer" method="POST">
				<input type="hidden" name="selection" value="">
			</form>
	
			<div class="container" style="margin-top: 10px;">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th class="editMode" style="width: 60px; height: 22px;">
								<input type="checkbox" id="selectall" /> 
								<span style="vertical-align: top;"> - 
									<a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();"> 
										<i class="fa fa-trash-o fa-lg"></i>
									</a>
								</span>
							</th>
							<th><a href="dashboard?range=${range}&search=${search}&order=name&sort=${sort}change"  class="btn btn-light"><fmt:message key="label.computername" /></a></th>
							<th><a href="dashboard?range=${range}&search=${search}&order=introduced&sort=${sort}change"  class="btn btn-light"><fmt:message key="label.introduceddate" /></a></th>
							<th><a href="dashboard?range=${range}&search=${search}&order=discontinued&sort=${sort}change"  class="btn btn-light"><fmt:message key="label.discontinueddate" /></a></th>
							<th><a href="dashboard?range=${range}&search=${search}&order=company_id&sort=${sort}change"  class="btn btn-light"><fmt:message key="label.company" /></a></th>
						</tr>
					</thead>
					<tbody id="results">
						<c:forEach items="${computerList}" var="computer">
							<tr>
								<td class="editMode"><input type="checkbox" name="cb"
									class="cb" value="${computer.id}"></td>
								<td><a href="editcomputer?computerid=${computer.id}" onclick="">
								<c:out value="${computer.name}"></c:out></a></td>
								<td><c:out value="${computer.introduced}"></c:out></td>
								<td><c:out value="${computer.discontinued}"></c:out></td>
								<td><c:out value="${computer.company.name}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	
		<footer class="navbar-fixed-bottom">
			<div class="container text-center">		
				<div class="pull-left pagination">	
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><fmt:message key="label.language" />
						<span class="caret"></span></button>
						<ul class="dropdown-menu">
				  			<li>
				  				<a class="dropdown-item" href="?lang=en"><fmt:message key="label.lang.en" /></a>
				  			</li>
				  			<li>
				  				<a class="dropdown-item" href="?lang=fr"><fmt:message key="label.lang.fr" /></a>
				  			</li>
						</ul>
			  		</div> 
		    	</div>

				<ul class="pagination">
					<li>
						<a href="dashboard?page=${prevPage}&range=${range}&search=${search}&order=${order}&sort=${sort}" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<c:forEach var="i" begin="${incrementPage}" end="${incrementLastPage}" step="1">
						<li>
							<a href="dashboard?page=${i}&range=${range}&search=${search}&order=${order}&sort=${sort}">${i}</a>
						</li>
					</c:forEach>
					<li>
						<a href="dashboard?page=${nextPage}&range=${range}&search=${search}&order=${order}&sort=${sort}" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
				<div class="btn-group btn-group-sm pull-right" role="group">
					<button type="button" class="btn btn-default">
						<a href="dashboard?range=10&search=${search}&order=${order}&sort=${sort}">10</a>
					</button>
					<button type="button" class="btn btn-default">
						<a href="dashboard?range=50&search=${search}&order=${order}&sort=${sort}">50</a>
					</button>
					<button type="button" class="btn btn-default">
						<a href="dashboard?range=100&search=${search}&order=${order}&sort=${sort}">100</a>
					</button>
				</div>
			</div>
		</footer>
		<script src="resources/js/jquery.min.js"></script>
		<script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/dashboard.js"></script>
	</body>
</html>