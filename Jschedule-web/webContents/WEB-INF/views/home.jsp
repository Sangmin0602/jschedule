<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" pageEncoding="utf-8"%>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Navbar Template for Bootstrap</title>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp"></jsp:include>
</head>

<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>Navbar example</h1>
			<p>This example is a quick exercise to illustrate how the
				default, static navbar and fixed to top navbar work. It includes the
				responsive CSS and HTML, so it also adapts to your viewport and
				device.</p>
			<p>
				<a class="btn btn-lg btn-primary" href="../../components/#navbar"
					role="button">View navbar docs &raquo;</a>
			</p>
		</div>

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
