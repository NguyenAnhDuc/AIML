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
<title>Chat</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<style>

</style>
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="../resources/js/vendor/jquery-1.10.1.min.js">
	
</script>
<script type="text/javascript"
	src="../resources/js/vendor/bootstrap.min.js">
	
</script>
<script>
	function onSearch() {
		//alert("searchtext");
		var searchtext = $('#searchtext').val();
		var botId = $("#botID").val();
		$.ajax({
			type : "POST",
			url : "/AIML/bot/chat",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : "question=" + encodeURIComponent(searchtext) + "&botID="
					+ encodeURIComponent(botId),
			success : function(result) {
				var htmlResult = "Human: " + result.human + "<br>";
				htmlResult +=  result.botname + ": " + result.bot + "<br><br>";

				$('#content').prepend(htmlResult);
			},
			error : function(result) {
				alert("Error");
			}
		});
	};
</script>
</head>

<%@ include file="../share/header.jsp" %>
<body>
	<div class="container">
		
		<div class="row">
		    <div class="col-md-2 col-md-offset-5">
		    	<h2>
				CHAT
				</h2>
		    </div>
		</div>
				
		

		<div class="row">
			<input type="hidden" id="botID" value="${botID}">
			<div class="col-xs-6 col-md-3"></div>
			<div class="input-group col-xs-6 col-md-6 ">


				<input type="text" class="form-control" id="searchtext"
					onkeyup="if (event.keyCode == 13) document.getElementById('searchbtn').click();"
					size="50" /> <span class="input-group-btn">
					<button class="btn btn-default" type="button" id="searchbtn"
						onclick="onSearch()">Chat</button>
				</span>
			</div>
			<div class="col-xs-6 col-md-3"></div>
		</div>
		<br> <br>
		<div id="content"
			style="margin-left: auto; margin-right: auto;; width: 60%; font-family: Courier New, Helvetica, sans-serif;">

		</div>
	</div>
	<%@ include file="../share/footer.jsp" %>
</body>
</html>