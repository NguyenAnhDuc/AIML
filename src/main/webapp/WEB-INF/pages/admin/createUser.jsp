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
<link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
<style>
.container {
	padding-top: 50px;
	padding-bottom: 20px;
}
</style>
<link rel="stylesheet" href="../../resources/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="../../resources/js/vendor/jquery-1.10.1.min.js">
	
</script>
<script type="text/javascript"
	src="../../resources/js/vendor/bootstrap.min.js">
	
</script>
<%@ include file="../share/AdminHeader.jsp" %>
</head>
<body>
	<div class="container">
		
		<div class="row-fluid">
			<form class="form-horizontal" role="form" method="post" action="create">
				<div id="error-alert" class="alert alert-danger col-sm-12 hide">
					user name is exists! Try with different name!
				</div>
				<div id="user-error-alert" class="alert alert-warning col-sm-12 hide">
					user name is required!
				</div>
				<div id="success-alert" class="alert alert-success col-sm-12 hide">
					Create User Successfully!
				</div>
				<div class="form-group">
					<label  class="col-sm-2 control-label">User Name</label>
					<div class="col-sm-10">
						<input id="username" type="text" class="form-control" id="inputUserName" name="name"
							placeholder="User Name" required>
					</div>
					
				</div>
				
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-default" type="button" id="createBotBtn"
						onclick="createuser()">Create User</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	//alert("123");
	function createuser() {
		//alert("searchtext");
		var name = $('#username').val();
		if (!name) {
			$('#user-error-alert').removeClass('hide');
			return;
		}
		
	
		
		$.ajax({
			type : "POST",
			url: "/AIML/admin/user/create",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : "username=" + encodeURIComponent(name),
			success : function(result) {
				if (result.status === "error"){
					$('#error-alert').removeClass('hide');
					$('#success-alert').addClass('hide');
					$('#user-error-alert').addClass('hide');
					
				}
				else {
					$('#success-alert').removeClass('hide');
					$('#error-alert').addClass('hide');
					$('#user-error-alert').addClass('hide');
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