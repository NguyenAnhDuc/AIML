
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
<link rel="stylesheet" href="../../resources/css/bootstrap.min.css">

</head>
<body>
    <jsp:include page="../share/header.jsp" />
	<div class="container">
		<form role="form" method="post" action="/AIML/bot/saveFile">
			<div class="form-group">
				<h3>${botName} => ${filename} </h3>
				<input type="hidden" name="botID" value="${botID}">
				<input type="hidden" name="filename" value="${filename}">
				<textarea rows="20" type="text" class="form-control"
					name="content">
    	${content} 
    </textarea>
			</div>
			<div class="row">
				<div class="col-md-2 col-md-offset-5">
					<button type="submit" class="btn btn-success">Submit</button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>