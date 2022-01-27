
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">Revoke ${app.getName()}</h1>
	</div>
	<jsp:include page="../alert.jsp" />
	<form class="form-horizontal" action="revoke" method="post">
		<fieldset>
			<div class="card" style="">
				<br>
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="appname">Application
						Name</label>
					<div class="col-md-4">
						<input id="name" name="name" type="text"
							placeholder="Application Name" class="form-control input-md"
							required="" value="">

					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="userpass">Your
						Password</label>
					<div class="col-md-4">
						<input id="userpass" name="userpass" type="password"
							placeholder="You Password" class="form-control input-md"
							required="" value="">

					</div>
				</div>


				<!-- Button -->
				<div class="form-group">
					<div class="col-md-4">
						<button id="revoke" name="revoke" class="btn btn-danger"
							value="true">Revoke</button>
					</div>
				</div>
			</div>
			<hr>
		</fieldset>
		<input type="hidden" id="manage" name="manage" value="${app.getID()}">
	</form>
</main>
<jsp:include page="../footer.jsp" />