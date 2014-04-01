<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Upload</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="../resources/js/vendor/jquery-1.10.1.min.js">
</script>
<style>
	.alertMessage{
		margin-top: 10px;
	}
</style>
<%@ include file="share/header.jsp" %>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Upload</h3>
			</div>
			<div class="panel-body">
				<form:form enctype="multipart/form-data"
					modelAttribute="uploadedFile" action="fileUpload">
					<input type="hidden" name="botID" value="${botID}">
					<input type="hidden" name="type" id="fileType" value="${type}">

					<div class="form-group">
						<label for="exampleInputFile">File input</label> <input
							type="file" id="exampleInputFile" name="file" mutiple>
					</div>
					
					<button type="submit" class="btn btn-default hide" id="submitBtn"></button>
					<button class="btn btn-default" type="button" id="searchbtn"
						onclick="Submit()">Submit</button>
					<br>
					<div class = "alertMessage">
						<div class="alert alert-danger hide" id="errorMessage">
               		  	Upload failed! Must be aiml file!
           		   		</div>
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</body>
<script>
	function Submit(){
		if ($("#fileType").val() != "aiml" ||  $("#exampleInputFile").val().toString().indexOf(".aiml") != -1){
			$("#submitBtn").click();
		}
		else{
			$("#errorMessage").removeClass("hide");
		}
	}
</script>
</html>
