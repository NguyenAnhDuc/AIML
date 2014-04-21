
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
  
	
	
	
  
     
    $( "#btnGetToken" ).click(function() {
      $("#usernameRow").hide();
	  $("#tokenRow").hide();
	  $("#botIdRow").hide();
	  $("#dataRow").hide();
	  $("#methodType").text("GET");
	  $("#apiName").text("GET ACCESS TOKEN");
	  $("#myForm").attr("method","GET");
	  requestType = "getToken";
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
	
	
	$( "#chatResponse" ).click(function() {
		$("#usernameRow").show();
		  $("#tokenRow").show();
		  $("#botIdRow").show();
		  $("#dataRow").show();
		  $("#txtResult").val('');	 
		  $("#methodType").text("POST");
		  $("#apiName").text("CHAT/RESPONSE");
		  $("#myForm").attr("method","POST");
		  requestType = "chatResponse";
		  $("#txtResult").val('');
		  $("#txtUserId").val("");
		  $('#txtToken').val('');
		  $("#txtData").val("");
	});
	
	 $("#submitbtn").click(function() {
		
	    //var url = "http://localhost:8080/AIML/"; // the script where you handle the form input.
	    var url = "/AIML/API/";
	    var username=$("#txtUsername").val();
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
	    	  url += "getToken" ;
	    	  methodType = "GET";
	    	  break;
	    	  
	    case 'chatResponse':
	    	  if (botId === '' || datajson === ''){
		    	  $("#submitFormBtn").click();  
		    	  isValid = false;
		    	  break;	      	  
		      }
	    	  //data = "botId=" + botId +  '&data='+ JSON.stringify(eval("(" + datajson + ")"));
	    	  data = "botID=" + botId + "&userID=" + userId + "&token=" + accessToken + '&data='+ datajson;
		      url += "chatapi";
	    	  methodType = "POST";
		      break;
	    case 'bots':
		      methodType = "GET";
		      url += "bots" + "?userID=" + userId  + "&token=" + accessToken;
		      break;
	    case 'botId':
	    	  url += "botdetail" + "?userID=" + userId  + "&botID=" + botId + "&token=" + accessToken;
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
	

