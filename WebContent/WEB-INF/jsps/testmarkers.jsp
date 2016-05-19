<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Driver Routes Admin Panel</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<spring:url value="/static/css/main.css" var="mainCss" />
<spring:url value="/static/js/jquery.classyqr.min.js" var="classyqrJs" />

<script type="text/javascript" src="${classyqrJs}"></script>
<link rel="stylesheet" type="text/css" href="${mainCss}" />

<link rel="icon" 
      type="image/png" 
      href="http://www.favicon.cc/logo3d/152571.png">

<script type="text/javascript">
//	var muleNumber;
//	var muleURL;
//	var muleURLTemplate = 'muleteer.herokuapp.com/tracking/mule-';
	
	
$(document).ready(function() {

		$("[id*='conn-']").click(function() {
			muleNumber = $(this).attr("id").substring(5);
			updateMule(muleNumber);
		});
		
		function updateMule(n) {
			var lat = $('#lat-'+n).val();
			var lng = $('#lng-'+n).val();
			
			console.log(lat);
			console.log(lng);
			
			var addr = '/muleteer/admin/test/mule-'+n;
			
			$.post(addr,
				{
					lat: lat,
					lng: lng
				},
				function(data, status){
			 });
		}
	});
</script>

</head>
<body>
	<br>
	<div align="center">
		<h1>Muleteer Test Panel</h1>
	</div>
	<br>
	<br>
	<div class="row">
		<div class="col-xs-4"></div>
		<div class="col-xs-4">
			<table class="table">
				<c:forEach items="${mules}" var="mule">
					<tr>
						<td><input id="lat-${mule.id}" maxlength="15" type="text" class="form-control" value=""></td>
						<td><input id="lng-${mule.id}" maxlength="15" type="text" class="form-control" value=""></td>
						<td><button id="conn-${mule.id}" type="button" class="btn btn-success">set coords</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-xs-2"></div>
	</div>
</body>
</html>
