<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<form class="form-horizontal" action="manage" method="post">
	<fieldset>
		<legend>Managing ${app.getName()} (${license.getName()})</legend>
		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="appname">License Name</label>
			<div class="col-md-4">
				<input id="name" name="name" type="text"
					placeholder="License Name" class="form-control input-md"
					required="" value="${license.getName()}">

			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="appdescription">License
				Description</label>
			<div class="col-md-4">
				<textarea id="description" name="description" type="text"
					placeholder="License Description" class="form-control input-md"
					required="" value="${license.getDescription()}">
    			</textarea>
			</div>
		</div>
		
		<!-- Number input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="version">Duration</label>
			<div class="col-md-4">
				<input type="number" id="version" name="version" class="form-control input-md" value="${license.getDuration()}">
			</div>
		</div>
		
		<!-- Number input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="version">Type</label>
			<div class="col-md-4">
				<input type="number" id="version" name="version" class="form-control input-md" value="${license.getType()}">
			</div>
		</div>
		
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="create">Update</label>
			<div class="col-md-4">
				<button id="update_details" name="update_details"
					class="btn btn-primary" value="true">Update</button>
			</div>
		</div>
		<hr>
		<input type="hidden" id="manage" name="manage" value="${license.getID()}">
	</fieldset>
	</form>
<jsp:include page="../footer.jsp" />