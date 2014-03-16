<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<html>  
<head>  
<script type="text/javascript" src="/resources/js/vendor/jquery-1.10.1.min.js"></script>
<title>Upload bot data file</title>  
</head>  
<body>  
  
 <center>   
  <h3>Please select a file to upload !</h3>  
    
  
  <form:form method="post" enctype="multipart/form-data"  
   modelAttribute="uploadedFile" action="fileUpload">  
   <input type="hidden" name="botName" value=${botName} }>
   <table>  
     
    <tr>  
     <td> UserId:  </td>  
     <td><input type="text" name="userId" />  
     </td>  
    </tr>  
    <tr>  
     <td> BotId:  </td>  
     <td><input type="text" name="botId" />  
     </td>  
    </tr> 
    <tr>  
     <td> Token:  </td>  
     <td><input type="text" name="token" />  
     </td>  
    </tr>
    <tr>  
     <td>Upload File: </td>  
     <td><input type="file" name="file" />  
     
     </td>  
     <td style="color: red; font-style: italic;"><form:errors  
       path="file" />  
     </td>  
    </tr> 
    <tr>  
     <td> </td>  
     <td><input type="submit" value="Upload" />  
     </td>  
    </tr>  
   </table>  
  </form:form>  
 </center>  
</body>  
</html>  