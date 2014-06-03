<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<style type="text/css">

body {
	background: url(http://mymaplist.com/img/parallax/back.png);
	background-color: #444;
	background: url(http://mymaplist.com/img/parallax/pinlayer2.png),
		url(http://mymaplist.com/img/parallax/pinlayer1.png),
		url(http://mymaplist.com/img/parallax/back.png);
}

.vertical-offset-100 {
	padding-top: 100px;
}
</style>
<script type="text/javascript"
	src="resources/js/vendor/jquery-1.10.1.min.js">
	
</script>
<script type="text/javascript"
	src="resources/js/vendor/bootstrap.min.js">
	
</script>
<script type="text/javascript">

$(document).ready(function(){

	

	  $(document).mousemove(function(evt){
	     TweenLite.to($('body'), 
	        .5, 
	        { css: 
	            {
	                backgroundPosition: ""+ parseInt(evt.pageX/8) + "px "+parseInt(evt.pageY/'12')+"px, "+parseInt(evt.pageX/'15')+"px "+parseInt(evt.pageY/'15')+"px, "+parseInt(evt.pageX/'30')+"px "+parseInt(evt.pageY/'30')+"px"
	            }
	        });
	  });
	});
</script>
</head>
<script src="http://mymaplist.com/js/vendor/TweenLite.min.js"></script>
<!-- This is a very simple parallax effect achieved by simple CSS 3 multiple backgrounds, made by http://twitter.com/msurguy -->

<div class="container">
	<div class="row vertical-offset-100">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please sign in</h3>
				</div>
				<div class="panel-body">
					<c:if test="${not empty error}">
					<div id="login-alert"
					class="alert alert-danger col-sm-12">Your login attempt was not successful, try again.</div>
					
					</c:if>
					<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="Username" name="j_username"
									type="text">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password"
									name="j_password" type="password" value="">
							</div>
							<!-- <div class="checkbox">
								<label> <input name="remember" type="checkbox"
									value="Remember Me" name="_spring_security_remember_me"> Remember Me
								</label>
							</div> -->
							<input class="btn btn-lg btn-success btn-block" type="submit"
								value="Login">
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

</html>