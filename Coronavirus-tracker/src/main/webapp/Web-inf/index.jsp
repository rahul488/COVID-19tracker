<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CoronaVirus Tracker</title>
<link href="${contextPath }/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h1>CoronaVirus Tracker</h1>
	<p>This application lists the current number of cases reported across the globe</p>
	<div class="jumbotron">
	<h1 class="display-4">${totalReportedCases }</h1>
	<p class="lead">Total cases reported as of today</p>
	<hr class="my-4">
	<p>New Cases Reported Since Previous Day <span>${newCasesFromPrevDay}</span></p>
	</div>
	<div class="container">
<table class="table table-striped table-bordered table-dark">
	
		<thead>
			<tr>
				<th scope="col">Country</th>
				<th scope="col">Cases</th>
				<th scope="col">FromPreviousDay</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${locationStats }" var="cases">
				<tr>
					<td>${cases.country }</td>
					<td>${cases.latestTotalCases }</td>
					<td>${cases.diffFromPrevDay }</td>	
				</tr>
			</c:forEach>
			
		</tbody>
		
	
	
	</table>
	</div>
</body>
</html>