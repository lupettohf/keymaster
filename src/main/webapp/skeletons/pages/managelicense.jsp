<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">Managing ${app.getName()} (${license.getName()})</h1>
	</div>
	<jsp:include page="../alert.jsp" />
	<form class="form-horizontal" action="manage" method="post">
		<fieldset>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="appname">License
					Name</label>
				<div class="col-md-4">
					<input id="name" name="name" type="text" placeholder="License Name"
						class="form-control input-md" required=""
						value="${license.getName()}">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="appdescription">License
					Description</label>
				<div class="col-md-4">
					<textarea id="description" name="description" type="text"
						placeholder="License Description" class="form-control input-md"
						required="" value="">${license.getDescription()}
    			</textarea>
				</div>
			</div>

			<!-- Number input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="duration">Duration</label>
				<div class="col-md-4">
					<input type="number" id="duration" name="duration"
						class="form-control input-md" value="${license.getDuration()}">
				</div>
			</div>

			<!-- Number input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="type">Type</label>
				<div class="col-md-4">
					<input type="number" id="type" name="type"
						class="form-control input-md" value="${license.getType()}">
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="update_details" name="update_details"
						class="btn btn-primary" value="true">Update</button>
					<a href="/app/manage/licenses/keys/manage" class="btn btn-primary">Manage
						Product Keys</a>
				</div>
			</div>
			<hr>
			<input type="hidden" id="manage" name="manage"
				value="${license.getID()}">
		</fieldset>
	</form>
	<hr>
</main>
<jsp:include page="../footer.jsp" />