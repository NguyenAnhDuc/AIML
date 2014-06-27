<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!doctype html>
[if lt IE 7]>
  <html class="no-js lt-ie9 lt-ie8 lt-ie7">
  <![endif]
[if IE 7]>
    <html class="no-js lt-ie9 lt-ie8">
    <![endif]
[if IE 8]>
      <html class="no-js lt-ie9">
      <![endif]
[if gt IE 8]>
        <!
<html class="no-js">
<![endif]
Built with Divshot - http://www.divshot.com

<head>
<title>Show Bots</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="../resources/js/vendor/jquery-1.10.1.min.js">
	
</script>

</head> -->
<body>
	<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/AIML/admin/users">Users</a>
      <a class="navbar-brand" href="/AIML/bot/show">Bots</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <%-- <ul class="nav navbar-nav"> 
 	   <li class="active"><a href="/admin/users">Users</a></li>
        <li><a href="admin/bots">Bots</a></li>	
      </ul>
      --%>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/admin/logs" />">View Logs</a></li>
        <li><a href="<c:url value="/j_spring_security_logout" />">Sign out</a></li>
        
        
      </ul> 
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	
</body>
<!-- </html> -->