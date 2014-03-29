<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!--[if lt IE 7]>
  <html class="no-js lt-ie9 lt-ie8 lt-ie7">
  <![endif]-->
<!--[if IE 7]>
    <html class="no-js lt-ie9 lt-ie8">
    <![endif]-->
<!--[if IE 8]>
      <html class="no-js lt-ie9">
      <![endif]-->
<!--[if gt IE 8]>
        <!-->
<html class="no-js">
<!--<![endif]-->
<!-- Built with Divshot - http://www.divshot.com -->

<head>
<title>Training Bot</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<script src="../resources/js/vendor/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="../resources/js/vendor/jquery.confirm.min.js">
</script>    
<style>

.custab {
	border: 1px solid #ccc;
	padding: 5px;
	margin: 5% 0;
	box-shadow: 3px 3px 2px #ccc;
	transition: 0.5s;
}

.custab:hover {
	box-shadow: 3px 3px 0px transparent;
	transition: 0.5s;
}
</style>

</head>
<body>
	<jsp:include page="../share/header.jsp" />

	<div class="container">
		<div class="row col-md-6 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<thead>
					<a href="upload?botID=${botID}" class="btn btn-primary btn-xs pull-right"><b>+</b>
						Upload AIML Files</a>
					<tr>
						<th>#</th>
						<th>Filename</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>
				<%
					int i = 0;
				%>
				<c:forEach var="filename" items="${files}">
					<%
						i++;
					%>
					<tr>
						<td><%=i%></td>
						<td>${filename}</td>
						<td class="text-center"><a class='btn btn-info btn-xs'
							href="data/edit?filename=${filename}&botID=${botID}" ><span class="glyphicon glyphicon-edit"></span> Edit</a>
							<a href="deleteAIML?filename=${filename}&botID=${botID}" class="simpleConfirm btn btn-danger btn-xs"><span
								class="glyphicon glyphicon-remove"></span> Del</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
 <script src="../resources/js/vendor/bootstrap.min.js"></script>
     <script src="../resources/js/vendor/run_prettify.js"></script> 
    <script>
    $(".simpleConfirm").confirm();
    </script>
</body>
</html>