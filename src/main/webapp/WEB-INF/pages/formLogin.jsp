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
<title>Login</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<style>
body {
	padding-top: 50px;
	padding-bottom: 20px;
}
.page-header {
    border-bottom: 1px solid #FFFFFF;
    margin: 20px 0;
    padding-bottom: 9px;
    text-align: center;
}

.form-control {
    border: 1px solid #D6D6D6;
    border-radius: 0;
    box-shadow: none;
    height: 50px;
    padding: 6px 15px;
}

body {
    background-color: #ee7778;
}

.row {
    background: #ededed;
    padding: 20px 50px 20px 50px;
    margin: 100px;
}

legend {
    border: medium none;
    color: #7F7F7F;
    display: block;
    font-size: 20px;
    line-height: inherit;
    margin-bottom: 15px;
    padding: 0;
    text-align: center;
    width: 100%;
}

.btn {
    padding: 10px;
    border-radius: 0;
    border: none;
    font-size: 21px;
}
</style>
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<script type="text/javascript"
	src="resources/js/vendor/jquery-1.10.1.min.js">
	
</script>
<script type="text/javascript"
	src="resources/js/vendor/bootstrap.min.js">
	
</script>
</head>
<body onload='document.f.j_username.focus();'>
	<div class="container">
		<div class="row">
			<div class="page-header">
				<h2>Login Form</h2>
			</div>
			<c:if test="${not empty error}">
				<div id="login-alert" class="alert alert-danger col-sm-12">Your
					login attempt was not successful, try again!</div>
			</c:if>
			<form class="form-horizontal" name='f'
				action="<c:url value='j_spring_security_check' />" method='POST'>
				<fieldset>

					<!-- Form Name -->
					<legend>
						<h3>Have an account? Sign In</h3>
					</legend>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-1 control-label" for="username"></label>
						<div class="col-md-10">
							<input id="username" name="j_username" type="text"
								placeholder="Username" class="form-control input-md">
						</div>
					</div>

					<!-- Password input-->
					<div class="form-group">
						<label class="col-md-1 control-label" for="password"></label>
						<div class="col-md-10">
							<input id="password" name="j_password" type="password"
								placeholder="Password" class="form-control input-md">
						</div>
					</div>

					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="login"></label>
						<div class="col-md-4">
							<button type="submit" id="login" name="login"
								class="btn btn-block btn-success">Login</button>
						</div>
						<!-- <div class="col-md-5">
							<button id="forpass" name="forpass"
								class="btn btn-block btn-warning">I forgot my password</button>
						</div> -->
					</div>
				</fieldset>
			</form>
		</div>
		<!--./row -->
	</div>
	<!--./container -->
</body>
</html>