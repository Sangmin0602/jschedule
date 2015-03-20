<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" pageEncoding="utf-8"%>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Navbar Template for Bootstrap</title>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp"></jsp:include>
	<link href="${ctxpath}/resources/css/calender.css" rel="stylesheet">
</head>

<body>

	<div class="container">
		<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>

		<!-- Main component for a primary marketing message or call to action -->
		<div id="cal-month">
		<div class="cal-row">
			<div class="cal-cell">00</div>
			<div class="cal-cell">01</div>
			<div class="cal-cell">02</div>
			<div class="cal-cell">03</div>
			<div class="cal-cell">04</div>
			<div class="cal-cell">05</div>
			<div class="cal-cell">06</div>
		</div>
		<div class="cal-row">
			<div class="cal-cell">07</div>
			<div class="cal-cell">08</div>
			<div class="cal-cell">09</div>
			<div class="cal-cell">10</div>
			<div class="cal-cell">11</div>
			<div class="cal-cell">12</div>
			<div class="cal-cell">13</div>
		</div>
		<div class="cal-row">
			<div class="cal-cell">14</div>
			<div class="cal-cell">15</div>
			<div class="cal-cell">16</div>
			<div class="cal-cell">17</div>
			<div class="cal-cell">18</div>
			<div class="cal-cell">19</div>
			<div class="cal-cell">20</div>
		</div>
		<div class="cal-row">
			<div class="cal-cell">21</div>
			<div class="cal-cell">22</div>
			<div class="cal-cell">23</div>
			<div class="cal-cell">24</div>
			<div class="cal-cell">25</div>
			<div class="cal-cell">26</div>
			<div class="cal-cell">27</div>
		</div>
		<div class="cal-row">
			<div class="cal-cell">28</div>
			<div class="cal-cell">29</div>
			<div class="cal-cell">30</div>
			<div class="cal-cell">31</div>
			<div class="cal-cell">32</div>
			<div class="cal-cell">33</div>
			<div class="cal-cell">34</div>
		</div>
		</div>
		<!-- 
		<div class="jumbotron">
			<h3>${user.name}님의 일정</h3>
			<p>This example is a quick exercise to illustrate how the
				default, static navbar and fixed to top navbar work. It includes the
				responsive CSS and HTML, so it also adapts to your viewport and
				device.</p>
			<p>
				<a class="btn btn-lg btn-primary" href="../../components/#navbar"
					role="button">View navbar docs &raquo;</a>
			</p>
		</div>
		 -->

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${ctxpath}/resources/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="${ctxpath}/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
