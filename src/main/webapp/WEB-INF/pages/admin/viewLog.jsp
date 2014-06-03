<%@ page contentType="text/html;charset=UTF-8" %>
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

</head>

<body>

    <jsp:include page="../share/AdminHeader.jsp" />

	<div class="container">
			<div class="form-group">
				<textarea rows="20" type="text" class="form-control"
					name="content" readonly>
    	${content}
    </textarea>
    
			</div>

	</div>
</body>
</html>