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
          <title>
            Admin Login
          </title>
          <meta name="viewport" content="width=device-width">
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
          <link rel="stylesheet" href="resources/css/bootstrap.min.css">
          <style>
            body { padding-top: 50px; padding-bottom: 20px; }
          </style>
          <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
          <link rel="stylesheet" href="resources/css/main.css">
          <script type="text/javascript" src="resources/js/vendor/jquery-1.10.1.min.js">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
          </script>
          <script type="text/javascript" src="resources/js/vendor/bootstrap.min.js">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
          </script>
        </head>
<body onload='document.f.j_username.focus();'>
<div class="container">
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Sign In</div>
				<!-- <div
					style="float: right; font-size: 80%; position: relative; top: -10px">
					<a href="#">Forgot password?</a>
				</div> -->
			</div>

			<div style="padding-top: 30px" class="panel-body">

				<div style="display: none" id="login-alert"
					class="alert alert-danger col-sm-12">Your login attempt was not successful, try again.</div>
				
				<c:if test="${not empty error}">
					<div id="login-alert"
					class="alert alert-danger col-sm-12">Your login attempt was not successful, try again.</div>
					<!-- <div class="errorblock">
						Your login attempt was not successful, try again. </br>
					</div> -->
				</c:if>
				<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <input id="login-username"
							type="text" class="form-control" name="j_username" value=""
							placeholder="username">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
							type="password" class="form-control" name="j_password"
							placeholder="password">
					</div>



					<!-- <div class="input-group">
						<div class="checkbox">
							<label> <input id="login-remember" type="checkbox"
								name="remember" value="1"> Remember me
							</label>
						</div>
					</div> -->


					<div style="margin-top: 10px" class="form-group">
						<!-- Button -->

						<div class="col-sm-12 controls">
							<button type="submit" class="btn btn-success">Login</button>
							<!-- <a id="btn-login" href="#" class="btn btn-success">Login </a> --> 
							<!-- <a	id="btn-fblogin" href="#" class="btn btn-primary">Login with
								Facebook</a>
 -->
						</div>
					</div>


					<!-- <div class="form-group">
						<div class="col-md-12 control">
							<div
								style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
								Don't have an account! <a href="#"
									onClick="$('#loginbox').hide(); $('#signupbox').show()">
									Sign Up Here </a>
							</div>
						</div>
					</div> -->
				</form>



			</div>
		</div>
	</div>
	<div id="signupbox" style="display: none; margin-top: 50px"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Sign Up</div>
				<div
					style="float: right; font-size: 85%; position: relative; top: -10px">
					<a id="signinlink" href="#"
						onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign
						In</a>
				</div>
			</div>
			<div class="panel-body">
				<form id="signupform" class="form-horizontal" role="form">

					<div id="signupalert" style="display: none"
						class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>



					<div class="form-group">
						<label for="email" class="col-md-3 control-label">Email</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="email"
								placeholder="Email Address">
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">First
							Name</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="firstname"
								placeholder="First Name">
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-md-3 control-label">Last
							Name</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="lastname"
								placeholder="Last Name">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-md-3 control-label">Password</label>
						<div class="col-md-9">
							<input type="password" class="form-control" name="passwd"
								placeholder="Password">
						</div>
					</div>

					<div class="form-group">
						<label for="icode" class="col-md-3 control-label">Invitation
							Code</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="icode"
								placeholder="">
						</div>
					</div>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="button" class="btn btn-info">
								<i class="icon-hand-right"></i> &nbsp Sign Up
							</button>
							<span style="margin-left: 8px;">or</span>
						</div>
					</div>

					<div style="border-top: 1px solid #999; padding-top: 20px"
						class="form-group">

						<div class="col-md-offset-3 col-md-9">
							<button id="btn-fbsignup" type="button" class="btn btn-primary">
								<i class="icon-facebook"></i>   Sign Up with Facebook
							</button>
						</div>

					</div>



				</form>
			</div>
		</div>




	</div>
</div>
</body>
</html>
