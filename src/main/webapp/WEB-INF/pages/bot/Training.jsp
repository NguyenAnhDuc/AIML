<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="true"%>
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
            Training Bot
          </title>
          <meta name="viewport" content="width=device-width">
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
          <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
          
          <link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
          <script type="text/javascript" src="../resources/js/vendor/jquery-1.10.1.min.js">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
          </script>
          <script type="text/javascript" src="../resources/js/vendor/bootstrap.min.js">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
          </script>
        </head>
        
        <body>
          <jsp:include page="../share/header.jsp" />
          <input type="hidden" value="${botID}" id="botIDtxt">
          <input type="hidden" id="templateText">
          <input type="hidden" id="newAnswerText">
          <input type="hidden" id="filenameText">
          <div class="container">
           
            <div class="row">
            </div>
            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">
                  Training
                </h3>
              </div>
              <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                      <div class="input-group">
                        <input type="text" class="form-control" id="questiontxt">
                        
                      <span class="input-group-btn">
                        <button class="btn btn-default" type="button" id="askbtn">Ask!</button>
                      </span>
                    </div><!-- /input-group -->
                  </div><!-- /.col-lg-6 -->
                </div><!-- /.row -->
                <br>
              </div>
              <div class="well well-sm" id="botchat"></div>
              <div class="alert alert-info">
                Matched:
                <a href="#" class="alert-link" id="filename"> </a>
              </div>
              <div class="row">
                <div class="col-lg-6">
                  <div class="input-group">
                    <input type="text" class="form-control" id="trainText">
                    <span class="input-group-btn" >

                        <button class="btn btn-default" type="button" id="trainbtn">Train</button>

                      </span>
                  </div>
                  <!-- /input-group -->
                </div>
                <!-- /.col-lg-6 -->
              </div>
              <!-- /.row -->
              <br>
              <div class="alert alert-success hide" id="successMessage">
                Training Successfully!
                <a href="#" class="alert-link"></a>
              </div>
              <div class="alert alert-danger hide" id="errorMessage">
                Training Error!
                <a href="#" class="alert-link"></a>
              </div>
            </div>
          </div>
        </body>
        <script type="text/javascript">
          $("#askbtn").click(function(){  
	         //alert("searchtext");
	         var searchtext = $('#questiontxt').val();  
	         var botID = $("#botIDtxt").val();
	         $.ajax({  
	           type: "POST",  
	           url: "/AIML/bot/chattrain",
	           contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	           data: "question="+encodeURIComponent( searchtext )+"&botID=" + encodeURIComponent(botID),  
	           success: function(result){  
	        	   var htmlResult = "Bot: " + result.bot ;
	        	   $('#filename').html(result.filename)
	               $('#botchat').html(htmlResult);
	               $('#templateText').val(result.template);
	               $('#newAnswerText').val(result.bot);
	               $('#filenameText').val(result.filename);
	           }, 
	           error: function(result){  
		            alert("Error");
		       } 
	         });  
	       });    
        
          $("#trainbtn").click(function(){
        	 var searchtext = $('#questiontxt').val();  
        	 var botID = $('#botIDtxt').val();
        	 var filename = $('#filenameText').val();
        	 var template = $('#templateText').val();
        	 var newAnswer = $('#trainText').val();
 	         $.ajax({  
 	           type: "POST",  
 	           url: "/AIML/bot/train",   
 	           contentType: "application/x-www-form-urlencoded;charset=UTF-8",
 	           data: "question="+encodeURIComponent( searchtext ) + 
 	           					"&newAnswer=" + encodeURIComponent(newAnswer) +"&botID=" + encodeURIComponent(botID),  
 	           success: function(result){  
 	        	   if (result.status = "success"){
 	        		  $('#successMessage').removeClass('hide');
 	        		  $('#errorMessage').addClass('hide');
 	        	   }	                
 	           } ,
 	           error: function(result){  
 	        	  $('#errorMessage').removeClass('hide');
 	        	 $('#successMessage').addClass('hide');
 		       } 
 	         });  
          });
        </script>
        
        </html>