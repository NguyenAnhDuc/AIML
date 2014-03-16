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
<title>Create Bot</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<style>
.container {
	padding-top: 50px;
	padding-bottom: 20px;
}
</style>
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="../resources/js/vendor/jquery-1.10.1.min.js">
	
</script>
<script type="text/javascript"
	src="../resources/js/vendor/bootstrap.min.js">
	
</script>
<%@ include file="share/header.jsp" %>
</head>
<body>
	<div class="container">
		
		<div class="row-fluid">
			<form class="form-horizontal" role="form" method="post" action="create">
				<div id="error-alert" class="alert alert-danger col-sm-12 hide">
					Bot name is exists! Try with different name!
				</div>
				<div id="bot-error-alert" class="alert alert-warning col-sm-12 hide">
					Bot name is required!
				</div>
				<div id="success-alert" class="alert alert-success col-sm-12 hide">
					Create Bot Successfully!
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Bot Name</label>
					<div class="col-sm-10">
						<input id="botname" type="text" class="form-control" id="inputBotName" name="name"
							placeholder="Bot Name" required>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Language</label>
					<div class="col-sm-10">
						<input id="language" type="text" class="form-control" id="inputLanguage" name="language"
							placeholder="Language" required>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-default" type="button" id="createBotBtn"
						onclick="createbot()">Create Bot</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	//alert("123");
	function createbot() {
		//alert("searchtext");
		var name = $('#botname').val();
		if (!name) {
			$('#bot-error-alert').removeClass('hide');
			return;
		}
		var language = $("#language").val();
		$.ajax({
			type : "POST",
			url: "/AIML/bot/create",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : "name=" + encodeURIComponent(name) + "&language="
					+ encodeURIComponent(language),
			success : function(result) {
				if (result.status === "error"){
					$('#error-alert').removeClass('hide');
					$('#success-alert').addClass('hide');
					$('#bot-error-alert').addClass('hide');
					
				}
				else {
					$('#success-alert').removeClass('hide');
					$('#error-alert').addClass('hide');
					$('#bot-error-alert').addClass('hide');
				}
			},
			error : function(result) {
				alert("Error");
			}
		});
	};
</script>
</body>
</html>