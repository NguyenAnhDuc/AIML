<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<style type="text/css">
	
</style>
<script type="text/javascript"
	src="../resources/js/vendor/jquery-1.10.1.min.js">
	
</script>
<script type="text/javascript"
	src="../resources/js/vendor/bootstrap.min.js">

</script>

</head>
<%@ include file="share/header.jsp"%>
<body>
	<input type="hidden" value="${username}" id="txtUsername">
	<div class="container">
		<div id="content-body">
			<section class="token">
				<div class="row">
					<div id="login-alert"
					class="alert alert-danger col-sm-12 hidden">Please <a href="/AIML/login">log in </a> to get access token!</div>
					<div class="form-horizontal">
						<div class="form-group">
						<label class="col-sm-2 control-label" for="txtAccessToken">Access Token: </label>
						<div class="col-sm-8">
					      <input type="email" class="form-control" id="txtAccessToken" readonly="readonly" >
					    </div>
						<button type="submit" class="btn btn-primary" id="btnGetToken">Get Access Token</button>
					</div>
				</div>
			</section>

			<section class="doc">
				<h4>Request URL:</h4>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Method Type</th>
							<th>Request URL</th>
							<th>Description </th>
							<th>Parameters </th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>GET</td>
							<td><code>http://tech.fpt.com.vn/AIML/api/bots</code></td>
							<td>Show all bot of a user </td>
							<td>token </td>
						</tr>
						<tr>
							<td>GET</td>
							<td><code>http://tech.fpt.com.vn/AIML/api/bots/{botID}</code></td>
							<td>Show detailed information </td>
							<td>botID, token </td>
						</tr>
						<tr>
							<td>GET</td>
							<td><code>http://tech.fpt.com.vn/AIML/api/bots/{botID}/chat</code></td>
							<td>Bot Chat API </td>
							<td>botID, data, token </td>
						</tr>
					</tbody>
				</table>
				<h4>Request parameters:</h4>

				<table class="table table-bordered table-striped">

					<thead>
						<tr>
							<th>Parameter</th>
							<th>Value</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>botID</td>
							<td>String</td>
							<td>Your botID</td>
						</tr>
						<tr>
							<td>token</td>
							<td>String</td>
							<td>Your access token key</td>
						</tr>
						<tr>
							<td>data</td>
							<td>String</td>
							<td>Data for chat API</td>
						</tr>
					</tbody>
				</table>

				<h4>Sample request URL:</h4>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Request Type</th>
							<th>Sample request URL</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Bots API</td>
							<td><code>http://tech.fpt.com.vn/AIML/api/bots?token=[your_token]</code></td>
						</tr>
						<tr>
							<td>Bot Detail API</td>
							<td><code>http://tech.fpt.com.vn/AIML/api/bots/[your_botID]?token=[your_token]</code></td>
						</tr>
						<tr>
							<td>Chat API</td>
							<td><code>http://tech.fpt.com.vn/AIML/api/bots/[your_botID]/chat?request=[your_request]&token=[your_token]</code></td>
						</tr>
					</tbody>
				</table>
			</section>

			<section class="doc">

				<h4>Response elements:</h4>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Element</th>
							<th>Value</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>result</td>
							<td>String</td>
							<td>Success<br>Failed(400-Bad Request | 401-Unauthorized | 404-NotFound | 500-Server Error)
							</td>
						</tr>
						<!-- <tr>
							<td>time_stamp</td>
							<td>Integer</td>
							<td>Response time</td>
						</tr>
						<tr>
							<td>response</td>
							<td>String</td>
							<td>Response message(you can get only if returning result is
								100)</td>
						</tr>
						<tr>
							<td>msg</td>
							<td>String</td>
							<td>Result msg(Description of result code)</td>
						</tr> -->
					</tbody>
				</table>
				<h4>Sample requests and responses:</h4>
				<h5>Bot Requests: </h5>
				<code> GET: http://tech.fpt.com.vn/AIML/api/bots?token=fa13b7cb-c876-168a-a69d-2cc41c4186eb </code></br>
				<code> {"result":"success","bots":[{"id":"5355acb2acdb015239030beb","name":"FPT_EN","language":"EN"},{"id":"5355b250acdb015239030bec","name":"FPT_VN","language":"VN"},{"id":"535eeaffacdb6c8b2ae05125","name":"FPT_TEST","language":"VN"}],"time_stamp":"1402010389151"} </code>
				<h5>Chat Requests: </h5>
				<code> GET: http://tech.fpt.com.vn/AIML/api/bots/539acb62e4b09a0b687150a3/chat?request=hello&token=fa13b7cb-c876-168a-a69d-2cc41c4186eb </code></br>
				<code> {"status":"success","request":"hello","response":"Hi!  I can really feel your smile today.","botname":"FPT_VN","time_stamp":"1402913123001"} </code>
				
				<h5><div class="alert alert-danger col-sm-10"> You must replace your request with your access token and your botID!</div> </h5>
			</section>
		</div>
	</div>
	<%@ include file="share/footer.jsp"%>
	<script type="text/javascript">
$("#btnGetToken")
.click(
		function() {
			//alert("searchtext");
			var url = "/AIML/api/token";
			$.ajax({
			    url: "/AIML/api/token",
			    type: 'GET',
			    success: function(data){ 
			    	 if (data === "null")  $("#login-alert").removeClass("hidden");
			    	 else $("#txtAccessToken").val(data);
			    },
			    error: function(data) {
			        $("login-alert").removeClass("hidden");//or whatever
			    }
			});
		
		});
</script>
</body>
</html>
