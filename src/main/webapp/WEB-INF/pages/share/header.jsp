<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default" role="navigation">
<!--   <div class="container-fluid"> -->
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/AIML/welcome">HOME</a>
      <a class="navbar-brand" href="/AIML/api/guide">API Guide</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/j_spring_security_logout" />">Sign out</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
 <!--  </div>/.container-fluid -->
</nav>
