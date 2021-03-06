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
<title>Show Bots</title>
<meta name="viewport" content="width=device-width">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <script src="../resources/js/vendor/jquery-1.9.1.min.js"></script>

</script>
<script type="text/javascript"
	src="../resources/js/vendor/jquery.confirm.min.js">
</script>
<style>
/* body {
	padding-top: 50px;
	padding-bottom: 20px;
}
 */
.row {
	/* margin-top: 40px; */
	padding: 0 10px;
}

.clickable {
	cursor: pointer;
}

.panel-heading div {
	margin-top: -18px;
	font-size: 15px;
}

.panel-heading div span {
	margin-left: 5px;
}

.panel-body {
	display: none;
}
</style>


<script type="text/javascript">
	/**
	 *   I don't recommend using this plugin on large tables, I just wrote it to make the demo useable. It will work fine for smaller tables 
	 *   but will likely encounter performance issues on larger tables.
	 *
	 *		<input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Filter Developers" />
	 *		$(input-element).filterTable()
	 *		
	 *	The important attributes are 'data-action="filter"' and 'data-filters="#table-selector"'
	 */
	(function() {
		'use strict';
		var $ = jQuery;
		$.fn
				.extend({
					filterTable : function() {
						return this
								.each(function() {
									$(this)
											.on(
													'keyup',
													function(e) {
														$(
																'.filterTable_no_results')
																.remove();
														var $this = $(this), search = $this
																.val()
																.toLowerCase(), target = $this
																.attr('data-filters'), $target = $(target), $rows = $target
																.find('tbody tr');
														if (search == '') {
															$rows.show();
														} else {
															$rows
																	.each(function() {
																		var $this = $(this);
																		$this
																				.text()
																				.toLowerCase()
																				.indexOf(
																						search) === -1 ? $this
																				.hide()
																				: $this
																						.show();
																	})
															if ($target
																	.find(
																			'tbody tr:visible')
																	.size() === 0) {
																var col_count = $target
																		.find(
																				'tr')
																		.first()
																		.find(
																				'td')
																		.size();
																var no_results = $('<tr class="filterTable_no_results"><td colspan="'+col_count+'">No results found</td></tr>')
																$target
																		.find(
																				'tbody')
																		.append(
																				no_results);
															}
														}
													});
								});
					}
				});
		$('[data-action="filter"]').filterTable();
	})(jQuery);

	$(function() {
		// attach table filter plugin to inputs
		$('[data-action="filter"]').filterTable();

		$('.container').on('click', '.panel-heading span.filter', function(e) {
			var $this = $(this), $panel = $this.parents('.panel');

			$panel.find('.panel-body').slideToggle();
			if ($this.css('display') != 'none') {
				$panel.find('.panel-body input').focus();
			}
		});
		$('[data-toggle="tooltip"]').tooltip();
	})

	
</script>
	<c:choose>
      <c:when test="${isAdmin}">
      	<%@ include file="../share/AdminHeader.jsp"%>   
      </c:when>

      <c:otherwise>
		<%@ include file="../share/header.jsp"%>   
      </c:otherwise>
    </c:choose>
</head>


<body>
    

	<div class="container">
		<div class="row">
			<div class="col-md-2 col-md-offset-5"></div>
		</div>
		<div class="row">
			<div class="col-md-6">
			<h4>
				Click the filter icon <small>(<i
					class="glyphicon glyphicon-filter"></i>)
				</small> to filter by bot name
			</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-md-7">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Bots</h3>
						<div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip"
								title="Toggle table filter" data-container="body"> <i
								class="glyphicon glyphicon-filter"></i>
							</span>
						</div>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="dev-table-filter"
							data-action="filter" data-filters="#dev-table"
							placeholder="Filter Bots" />
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th>#</th>
								<th>Bot Name</th>
								<!-- <th>Language</th> -->
								<th>Action</th>
								<th>Action</th>
								<th>Action</th>
								<!-- <th> Action </th> -->


							</tr>
						</thead>
						<tbody>
							<%
								int i = 0;
							%>
							<c:forEach var="botinfo" items="${botsinfo}">
								<%
									i++;
								%>
								<tr>
									<td><%=i%></td>
									<td>${botinfo.botname}</td>
									<td><a href="chat?botID=${botinfo.id}">Chat</a></td>
									<td><a href="train?botID=${botinfo.id}">Train</a></td>
									<td>
										<a href="dataFiles?type=aiml&botID=${botinfo.id}">AIML</a>
										/<a href="dataFiles?type=config&botID=${botinfo.id}">Config</a>
										<c:if test="${isAdmin}">
										   /<a href="dataFiles?type=aimlif&botID=${botinfo.id}">AIMLIF</a>
										   /<a href="dataFiles?type=maps&botID=${botinfo.id}">Maps</a>
										   /<a href="dataFiles?type=sets&botID=${botinfo.id}">Sets</a>
										</c:if> 
									</td>

									<td>
										<div class="pull-right action-buttons">
											
											<a  href="delete?botID=${botinfo.id}" class="simpleConfirm trash"><span
												class="glyphicon glyphicon-trash"></span></a> 
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<div class = "row">
			<div class="col-md-6">
				<a class="btn btn-info" href="new">Add new AIML bot</a>
			</div>
		</div>
		
		
		
	</div>
	  <script src="../resources/js/vendor/bootstrap.min.js"></script>
     <script src="../resources/js/vendor/run_prettify.js"></script> 
    <script>
    $(".simpleConfirm").confirm();
    </script>
    <%@ include file="../share/footer.jsp" %>
</body>
</html>