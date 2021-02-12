<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">Create a new application</h1>
	</div>
	<jsp:include page="../alert.jsp" />
	<form class="form-horizontal" action="new" method="post">
		<fieldset>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="appname">Application
					Name</label>
				<div class="col-md-4">
					<input id="name" name="name" type="text"
						placeholder="Application Name" class="form-control input-md"
						required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="appdescription">Application
					Description</label>
				<div class="col-md-4">
					<textarea id="description" name="description" type="text"
						placeholder="Application Description"
						class="form-control input-md" required="">
    </textarea>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="website">Website</label>
				<div class="col-md-4">
					<input id="website" name="website" type="website" placeholder=""
						class="form-control input-md">
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="create">Create</label>
				<div class="col-md-4">
					<button id="create" name="create" class="btn btn-primary">Create</button>
				</div>
			</div>
		</fieldset>
	</form>
</main>
<jsp:include page="../footer.jsp" />