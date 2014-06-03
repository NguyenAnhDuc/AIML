<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
	session="true"%>
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
<title>Training Bot</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">

<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<style type="text/css">
	#trainContent{
		padding-top : 10px;
		line-height : 170%;
		border-top: 1px solid #EEEEEE;
	}
	.row{
		margin-bottom: 20px;
	}
</style>
<script type="text/javascript"
	src="../resources/js/vendor/jquery-1.10.1.min.js">
	
</script>
<script type="text/javascript"
	src="../resources/js/vendor/bootstrap.min.js">
	
</script>
</head>

<body>
	<jsp:include page="../share/header.jsp" />
	<input type="hidden" value="${botID}" id="botIDtxt">
	<input type="hidden" value="${botName}" id="botNametxt">
	<input type="hidden" id="templateText">
	<input type="hidden" id="newAnswerText">
	<input type="hidden" id="filenameText">
	<div class="container">

		<div class="row">
			<h4>${botName}>> Training</h4>
			<h5>Type in questions below that you want your bot to be
				able to answer. If you don't like the answer, type in the response
				that you would like your bot to give instead.</h5>
			<div class="alert alert-success hide" id="successMessage">
                Training Successfully!
                <a href="#" class="alert-link"></a>
              </div>
              <div class="alert alert-danger hide" id="errorMessage">
                Training Error!
                <a href="#" class="alert-link"></a>
              </div>

			<div class=" col-md-6 form-inline ">

				<div class="form-group">
					<input type="text" class="form-control" id="questiontxt"
						onkeyup="if (event.keyCode == 13) document.getElementById('askbtn').click();"
						size="50" />
				</div>
				<button class="btn btn-primary" type="button" id="askbtn">Ask!</button>
			</div>
		</div>
		
		<div id="trainContent" class="hidden">
			<div id="question"></div>
			<div id="filenameMatch"></div>
			<div id="answer"></div>
			<div class="row">
            	<div id="trainRow" class=" col-md-6 form-inline ">
					<div class="form-group">
						<input type="text" class="form-control" id="trainText"
							size="50" />
					</div>
					<button class="btn btn-primary" type="button" id="trainbtn">Train!</button>
				</div>    
            </div>
		</div>
	</div>
		<jsp:include page="../share/footer.jsp" />
</body>
<script type="text/javascript">
	$("#askbtn")
			.click(
					function() {
						//alert("searchtext");
						var searchtext = $('#questiontxt').val();
						var botID = $("#botIDtxt").val();

						$
								.ajax({
									type : "POST",
									url : "/AIML/bot/chattrain",
									contentType : "application/x-www-form-urlencoded;charset=UTF-8",
									data : "question="
											+ encodeURIComponent(searchtext)
											+ "&botID="
											+ encodeURIComponent(botID),
									success : function(result) {
										var link = "data/edit?filename="
												+ result.filename
												+ "&type=aiml" + "&botID="
												+ botID;
										$('#filenameMatch').html('<strong>Match: </strong>' + '<a href="'  + link + '">' + result.filename + '</a>');
										$("#filenameMatch").attr("href", link);
										$('#question').html('<strong>Human: </strong>' + result.human);
										$('#answer').html('<strong> Bot: </strong>' + result.bot);
										$('#templateText').val(result.template);
										$('#newAnswerText').val(result.bot);
										$('#filenameText').val(result.filename);
										$('#trainContent').removeClass('hidden');

									},
									error : function(result) {
										alert("Error");
									}
								});
					});

	$("#trainbtn")
			.click(
					function() {
						var searchtext = $('#questiontxt').val();
						var botID = $('#botIDtxt').val();
						var filename = $('#filenameText').val();
						var template = $('#templateText').val();
						var newAnswer = $('#trainText').val();
						$
								.ajax({
									type : "POST",
									url : "/AIML/bot/train",
									contentType : "application/x-www-form-urlencoded;charset=UTF-8",
									data : "question="
											+ encodeURIComponent(searchtext)
											+ "&newAnswer="
											+ encodeURIComponent(newAnswer)
											+ "&botID="
											+ encodeURIComponent(botID),
									success : function(result) {
										if (result.status = "success") {
											$('#successMessage').removeClass(
													'hide');
											$('#errorMessage').addClass('hide');
										}
									},
									error : function(result) {
										$('#errorMessage').removeClass('hide');
										$('#successMessage').addClass('hide');
									}
								});
					});
</script>

</html>