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
							<td><code>http://sandbox.api.simsimi.com/request.p</code></td>
							<td>Show all bot of a user </td>
							<td>userID, token </td>
						</tr>
						<tr>
							<td>GET</td>
							<td><code>http://sandbox.api.simsimi.com/request.p</code></td>
							<td>Show detailed information </td>
							<td>userID, botID, token </td>
						</tr>
						<tr>
							<td>GET</td>
							<td><code>http://sandbox.api.simsimi.com/request.p</code></td>
							<td>Bot Chat API </td>
							<td>userID, botID, data, token </td>
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
							<td>userID</td>
							<td>String</td>
							<td>Your userID</td>
						</tr>
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
							<td><code>http://sandbox.api.simsimi.com/request.p?key=your_trial_key&amp;lc=en&amp;ft=1.0&amp;text=hi</code></td>
						</tr>
						<tr>
							<td>Bot Detail API</td>
							<td><code>http://api.simsimi.com/request.p?key=your_paid_key&amp;lc=en&amp;ft=1.0&amp;text=hi</code></td>
						</tr>
						<tr>
							<td>Chat API</td>
							<td><code>http://api.simsimi.com/request.p?key=your_paid_key&amp;lc=en&amp;ft=1.0&amp;text=hi</code></td>
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
							<td>Integer</td>
							<td>100-OK.<br>400-Bad Request.<br>401-Unauthorized.<br>404-Not
								found.<br>500-Server Error.
							</td>
						</tr>
						<tr>
							<td>id</td>
							<td>Integer</td>
							<td>Response id. (you can get only if returning result is
								100)</td>
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
						</tr>
					</tbody>
				</table>
				<h4>Sample response:</h4>
				<code> { "result": 100, "response": "Who are you?!", "id":
					13185569, "msg": "OK." } </code>
			</section>
		</div>
	</div>
	<%@ include file="share/footer.jsp"%>
	<script type="text/javascript">
$("#btnGetToken")
.click(
		function() {
			//alert("searchtext");
			var username = $("#txtUsername").val();
			var url = "/AIML/api/" + username + "/token";
			$.get(url,function(data){
			    $("#txtAccessToken").val(data);
			  });
		});
</script>
</body>
</html>
