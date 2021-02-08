<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<form class="form-horizontal" action="manage" method="post">
	<fieldset>
		<legend>Managing ${app.getName()}</legend>
		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="appname">Application
				Name</label>
			<div class="col-md-4">
				<input id="name" name="name" type="text"
					placeholder="Application Name" class="form-control input-md"
					required="" value="${app.getName()}">

			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="appdescription">Application
				Description</label>
			<div class="col-md-4">
				<textarea id="description" name="description" type="text"
					placeholder="Application Description" class="form-control input-md"
					required="" value="${app.getDescription()}">
    			</textarea>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="website">Website</label>
			<div class="col-md-4">
				<input id="website" name="website" type="website" placeholder=""
					value="${app.getWebsite()}" class="form-control input-md">
			</div>
		</div>
		
		<!-- Number input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="version">Version</label>
			<div class="col-md-4">
				<input type="number" id="version" name="version" class="form-control input-md" value="${app.getVersion()}">
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
		<div class="card" style="">
			<div class="card-body">
				<h4 class="card-title">
					API Key<br>
				</h4>
				<p class="card-text">
					Regenerating the API key will disable any application with the old
					key.<br>
				</p>
				<div class="row" style="">
					<div class="col-md-9">
						<div class="form-group" style="">
							<input type="text" class="form-control"
								value="${app.getAPIKey()}" disabled>
						</div>
					</div>
					<div class="col-md-1">
						<button id="regenerate_api" name="regenerate_api"
							class="btn btn-primary" value="true">Regenerate</button>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
	<input type="hidden" id="manage" name="manage" value="${app.getID()}">
</form>
		<form class="form-horizontal" action="manage/licenses/list" method="get">
		<div class="form-group">
			<div class="col-md-4">
				<button id="manage_license" name="manage_license"
					class="btn btn-primary" value="true">Manage Licenses</button>
			</div>
		</div>
		</form>
<jsp:include page="../footer.jsp" />