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
			<h1>일정관리 애플리케이션</h1>
			<p>일정 추가 / 조회 / 일정 이력 / 참여자 이력</p>
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
