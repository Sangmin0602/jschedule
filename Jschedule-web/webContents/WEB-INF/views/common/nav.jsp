<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="utf-8"%>
<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"></c:set>
<c:set var="user" value="${user}"/>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${ctxpath}">스케쥴</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${ctxpath}">Home</a></li>
				<li>
				<c:if test="${empty user}"><a href="" data-target="#LoginPanel" data-toggle="modal">Login</a></c:if>
				<c:if test="${not empty user}"><a href="logout">Logout</a></c:if></li>
				<li class="dropdown"><a class="dropdown-toggle"	data-toggle="dropdown" role="button" aria-expanded="false"href="${ctxpath}/view">일정<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${ctxpath}/view/month">월단위</a></li>
						<li><a href="${ctxpath}/view/week">주단위</a></li>
						<li><a href="${ctxpath}/view/day">일단위</a></li>
						<!-- 
						<li class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
						 -->
					</ul>
				</li>
				<li><a href="${ctxpath}/places">내 장소</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty user}"><li><button class="btn btn-primary header-btn">가입</button></li></c:if>
				<c:if test="${not empty user}"><li><button class="btn btn-primary header-btn" >${user.email}</button></c:if>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>
<div id="LoginPanel" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="loginHeader">
	<div class="modal-dialog login-box-size">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="loginHeader">로그인</h3>
			</div>
			<div class="modal-body">
				<form id="frmLogin" role="form">
					<div class="alert alert-danger alert-error" id="login-error">ddd</div>
					<div class="form-group">
						<input class="form-control" id="userid" name="userid" type="text" placeHolder="your@email.here"/>
						<span class="help-block"></span>
					</div>
					<div class="form-group">
						<input class="form-control" id="pass"  name="pass" type="password" placeHolder="비밀번호"/>
						<span class="help-block"></span>
					</div>
					<button type="button" id="btnLogin" class="btn btn-success">로그인</button>
				</form>
			</div>
			<!-- 
			<div class="modal-footer">
			</div>
			 -->
		</div>
	</div>
</div>