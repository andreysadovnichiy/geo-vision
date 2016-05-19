<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Driver Routes</title>
<spring:url value="/static/js/jquery.min.js" var="jqueryJs" />
<spring:url value="/static/js/muleteer/gmaps.proccessing.js" var="gmapsProccessing" />
<spring:url value="/static/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/static/css/main.css" var="mainCss" />
<spring:url value="/static/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/static/css/bootstrap-theme.min.css" var="bootstrapThemeCss" />

<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${gmapsProccessing}"></script>

<link rel="stylesheet" type="text/css" href="${mainCss}" />
<link rel="stylesheet" type="text/css" href="${bootstrapCss}" />
<link rel="stylesheet" type="text/css" href="${bootstrapThemeCss}" />

<link rel="icon" 
      type="image/png" 
      href="<spring:url value="/static/images/favicon.png" />">

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCZD9ucMQ4k0pnOFxE7lzgoVI2z_WC24w&signed_in=true&callback=initMap" async defer></script>

<style type="text/css">
#footer {
	height: 50px;
}
body {
	padding-top: 70px;
}
</style>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>

				<a class="navbar-brand" href="<spring:url value="/" />">Driver</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<spring:url value="/" />">Map <span class="sr-only">(current)</span></a></li>
					<li><a href="<spring:url value="/admin"/>">Truck settings</a></li>
				</ul>
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" id="address" name="address" placeholder="Destination" />
					</div>
					<button id="dest" class="btn btn-success" type="button">Route</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<!--
					<li><a href="<spring:url value="/log" />">Daily logs</a></li>
					-->
					<li><security:authorize access="isAuthenticated()">
							<a href="<spring:url value="/logout" />">Exit</a>
						</security:authorize></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="row">
		<div class="col-xs-3">
			<div class="row">
				<div class="col-xs-2"></div>
				<div class="col-xs-10">
					<br>
					<table class="table">
						<tr>
							<td><p class="text-left">Route distance: </p></td>
							<td ><p class="text-right"><span id="route"></span></p></td>
						</tr>
						<tr>
							<td><p class="text-left">Total Distance: </p></td>
							<td ><p class="text-right"><span id="total"></span></p></td>
						</tr>
					</table>
					<br>
					<form id="myForm">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Tracking for:</th>
								</tr>
							</thead>
							<c:forEach items="${mules}" var="mule">
								<tr class="success">
									<td><input id="mule-${mule.id}" type="radio" name="mules" value="${mule.id}">&nbsp;&nbsp;&nbsp;${mule.name}</td>
								</tr>
							</c:forEach>
						</table>
					</form>
					<div class="row">
						<div class="col-xs-6">
							<button id="find" class="btn btn-success btn-block">Search</button>
						</div>
						<div class="col-xs-6">
							<button id="refresh" class="btn btn-primary btn-block">Refresh</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-9">
			<div id="map"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div id="footer"></div>
			<div class="row">
				<div class="col-xs-3"></div>
				<div class="col-xs-7">
					<p align="center">
						<a href="http://solveast.com/">(c) Solveast Development LLC 2016</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
