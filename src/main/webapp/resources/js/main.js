
  var file;
  $(document).on('change', '.btn-file :file', function() {
      var input = $(this),
          numFiles = input.get(0).files ? input.get(0).files.length : 1,
          label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
      	  file = input.get(0).files.item(0);
}); 
  
  
  $("#usernameRow").hide();
  $("#passwordRow").hide();
  $("#botIdRow").hide();
  $("#dataRow").hide();	
  $("#tokenRow").hide();
  var requestType = "";
  
	/*$( "#userAuthenticateBtn" ).click(function() {
	  $("#usernameRow").show();
	  $("#passwordRow").show();
	  $("#botIdRow").hide();
	  $("#dataRow").hide();
	  $("#dataFileRow").hide();
	  $("#methodType").text("POST");
	  $("#apiName").text("USER/AUTHENTICATE");
	  requestType = "userAuthenticate";
	  $("#txtUsername").val("");
	  $("#txtPassword").val("");
	  $("#txtbotId").val("nodata");
	  $("#txtData").val("nodata");
	  $("#txtResult").val('');
	  $("#txtPassword").attr("type","password");	  
	});
	
	$( "#userRegisterBtn" ).click(function() {
		  $("#usernameRow").show();
		  $("#passwordRow").show();
		  $("#botIdRow").hide();
		  $("#dataRow").hide();
		  $("#dataFileRow").hide();
		  $("#methodType").text("POST");
		  $("#apiName").text("USER/REGISTER");
		  requestType = "userRegister";
		  $("#txtResult").val('');
		  $("#txtUsername").val("");
		  $("#txtPassword").val("");
		  $("#txtbotId").val("nodata");
		  $("#txtData").val("nodata");
		  $("#txtResult").val('');
		  $("#txtPassword").attr("type","password");
		});
	
	$( "#userActiveBtn" ).click(function() {
		  $("#usernameRow").show();
		  $("#passwordRow").show();
		  $("#botIdRow").hide();
		  $("#dataRow").hide();
		  $("#dataFileRow").hide();
		  $("#methodType").text("POST");
		  $("#apiName").text("USER/ACTIVE");
		  requestType = "userActive";
		  $("#txtResult").val('');
		  $("#txtUsername").val("");
		  $("#txtPassword").val("");
		  $("#txtbotId").val("nodata");
		  $("#txtData").val("nodata");
		  $("#txtResult").val('');
		  $("#txtPassword").attr("type","password");
		});
	
	$( "#botIdDataPut" ).click(function() {
		  $("#usernameRow").hide();
		  $("#passwordRow").hide();
		  $("#botIdRow").show();
		  $("#dataRow").hide();
		  $("#dataFileRow").show();
		  $("#methodType").text("POST");
		  $("#apiName").text("bot/ID/PUT");
		  requestType = "botIdDataPut";
		  $("#txtUsername").val("nodata");
		  $("#txtPassword").val("nodata");
		  $("#txtbotId").val("");
		  $("#txtData").val("nodata");
		  $("#txtResult").val('');
		  $("#txtPassword").attr("type","text");
		  $("#apiForm").attr("method","POST");
		  $("#apiForm").attr("action","/AbChat/Upload"); 
		  $("#apiForm").attr("enctype","multipart/form-data"); 
		  
		  
	});
	
	
	$( "#botIdDataGet" ).click(function() {
		$("#usernameRow").hide();
		  $("#passwordRow").hide();
		  $("#botIdRow").show();
		  $("#dataRow").hide();
		  $("#dataFileRow").show();
		  $("#methodType").text("POST");
		  $("#apiName").text("bot/ID/PUT");
		  requestType = "botIdDataPut";
		  $("#txtUsername").val("nodata");
		  $("#txtPassword").val("nodata");
		  $("#txtbotId").val("");
		  $("#txtData").val("");
		  $("#txtResult").val('');
		  $("#txtPassword").attr("type","text");
		  
	});*/
  
     
    $( "#btnGetToken" ).click(function() {
      $("#usernameRow").show();
	  $("#passwordRow").show();
	  $("#tokenRow").hide();
	  $("#botIdRow").hide();
	  $("#dataRow").hide();
	  $("#methodType").text("GET");
	  $("#apiName").text("GET ACCESS TOKEN");
	  $("#myForm").attr("method","GET");
	  requestType = "getToken";
	  $("#txtUserId").val('');
	  $("#txtPassword").val('');
	  $("#txtResult").val('');
	  $("#txtData").val('');
	});

  
	$( "#bots" ).click(function() {
		  $("#usernameRow").show();
		  $("#passwordRow").hide();
		  $("#botIdRow").hide();
		  $("#dataRow").hide();
		  $("#tokenRow").show();
		  $("#methodType").text("GET");
		  $("#apiName").text("botS");
		  $("#myForm").attr("method","GET");
		  requestType = "bots";
		  $("#txtUserId").val();
		  $("#txtResult").val('');
		  $("#txtPassword").attr("type","text");
	});
	
	$( "#botId" ).click(function() {
		  $("#usernameRow").show();
		  $("#passwordRow").hide();
		  $("#botIdRow").show();
		  $("#tokenRow").show();
		  $("#dataRow").hide();
		  $("#methodType").text("GET");
		  $("#apiName").text("bot/ID");
		  $("#myForm").attr("method","GET");
		  requestType = "botId";
		  $("#txtResult").val('');
		  $("#txtUserId").val("");		  
		  $("#txtbotId").val("");
		  $("#txtToken").val("");
		  $("#txtPassword").attr("type","text");
	});
	
	$( "#createBot" ).click(function() {
		  $("#usernameRow").show();
		  $("#passwordRow").hide();
		  $("#botIdRow").show();
		  $("#tokenRow").show();
		  $("#methodType").text("GET");
		  $("#apiName").text("CREATE BOT");
		  $("#myForm").attr("method","GET");
		  requestType = "createBot";
		  $("#txtResult").val('');
		  $("#txtUserId").val("");		  
		  $("#txtbotId").val("");
		  $("#txtPassword").attr("type","text");
	});
	

	
	$( "#chatResponse" ).click(function() {
		$("#usernameRow").hide();
		  $("#passwordRow").hide();
		  $("#tokenRow").hide();
		  $("#botIdRow").show();
		  $("#dataRow").show();
		  $("#txtResult").val('');	 
		  $("#methodType").text("POST");
		  $("#apiName").text("CHAT/RESPONSE");
		  $("#myForm").attr("method","POST");
		  requestType = "chatResponse";
		  $("#txtResult").val('');
		  $("#txtUserId").val("nodata");
		  $("#txtPassword").val("nodata");
		  $('#txtToken').val('nodata');
		  $("#txtData").val("");
		  $("#txtPassword").attr("type","text");
	});
	
	
	 $("#submitbtn").click(function() {
		
	    //var url = "http://localhost:8080/AIML/"; // the script where you handle the form input.
	    var url = "http://10.1.12.79:8080/AIML/";
	    var username=$("#txtUsername").val();
	    var password=$("#txtPassword").val();
	    var botId=$("#txtbotId").val();
	    var datajson=$("#txtData").val();
	    var userId = $("#txtUserId").val();
	    var accessToken = $("#txtToken").val();
	    var data = "";
	    var methodType = "POST";
		var isValid = true;	    
	    switch(requestType)
	    {
	    case 'getToken':
	    	  if (username === '' || password === ''){
		    	  $("#submitFormBtn").click();  
		    	  isValid = false;
		    	  break;	      	  
		      }
	    	  url += "API/getToken" + "?userID=" + userId + "&password=" + password;
	    	  methodType = "GET";
	    	  break;
	    	  
	    case 'userAuthenticate':
	    	  if (username === '' || password === ''){
		    	  $("#submitFormBtn").click();  
		    	  isValid = false;
		    	  break;	      	  
		      }
	    	  url += "UserAuthenticate";
		      methodType = "POST";
	    	  data = 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password);
		      break;
	    case 'userRegister':
	    	  if (username === '' || password === ''){
		    	  $("#submitFormBtn").click();  
		    	  isValid = false;
		    	  break;	      	  
		      }
	    	  url += "UserRegister";
	    	  methodType = "POST";
	    	  data = 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password);
		      break;

	    case 'userActive':
	    	  if (username === '' || password === ''){
		    	  $("#submitFormBtn").click();  
		    	  isValid = false;
		    	  break;	      	  
		      }
	    	  url += "UserActive";
	    	  data = 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password);
	    	  break;
	    case 'chatResponse':
	    	  if (botId === '' || datajson === ''){
		    	  $("#submitFormBtn").click();  
		    	  isValid = false;
		    	  break;	      	  
		      }
	    	  //data = "botId=" + botId +  '&data='+ JSON.stringify(eval("(" + datajson + ")"));
	    	  data = "botID=" + botId +  '&data='+ datajson;
		      url += "API/chatapi";
	    	  methodType = "POST";
		      break;
	    case 'bots':
		      methodType = "GET";
		      url += "API/bots" + "?userID=" + userId  + "&token=" + accessToken;
		      break;
	    case 'botId':
	    	  url += "API/bot" + "?userID=" + userId  + "&botID=" + botId + "&token=" + accessToken;
		      methodType = "GET";
		      break;
	    case 'createBot':
	    	  url += "user/newBot" + userId + "?botId=" + botId + "&token=" + accessToken;
	    	  methodType = "GET";
	          break;
	          
	    case 'dataFile':
	    	 
	    	 $("#submitFormBtn").click(); 
		      isValid = false;
	    	 break;
	     
	    default:
	      
	    }
	    /*if (requestType != "botIdDataPut") data += "&requestType=" + requestType;*/
	    
	   
	    if (isValid){
		    $.ajax({
		           type: methodType,
		           url: url,
		           data: data, // serializes the form's elements.
		           success: function(data)
		           {
		        	   $("#txtResult").val(JSON.stringify(data));	     
		           },
		   		   error: function(data)
		   		   {
		   			   alert("error");
		   		   }
		         });
	    }
	    return false; // avoid to execute the actual submit of the form.
	});  
	

