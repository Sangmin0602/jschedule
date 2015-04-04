<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" pageEncoding="utf-8"%>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>내가 등록한 장소들</title>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
		<!-- Main component for a primary marketing message or call to action -->
		사용자가 등록한 장소들
		<c:forEach var="p" items="${places}">
		<li><span class="span3" >${p.placeName}</span><button class="btn" id="btn-place-edit">수정</button>
		<!-- <button class="btn" id="btn-place-del">삭제</button> --></li>
		</c:forEach>
		

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
