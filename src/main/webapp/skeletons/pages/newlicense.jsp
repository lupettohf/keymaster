<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   

<jsp:include page="../header.jsp" />
<form class="form-horizontal" action="new" method="post">
<fieldset>

<!-- Form Name -->
<legend>(Managing ${app.getName()}) Create a new license</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="appname">License Name</label>  
  <div class="col-md-4">
  <input id="name" name="name" type="text" placeholder="Application Name" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="appdescription">License Description</label>  
  <div class="col-md-4">
  <textarea id="description" name="description" type="text" placeholder="Application Description" class="form-control input-md" required="">
    </textarea>
  </div>
</div>

		<!-- Number input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="duration">Duration (Days)</label>
			<div class="col-md-4">
				<input type="number" id="duration" name="duration" class="form-control input-md">
			</div>
		</div>
				<!-- Number input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="duration">Type</label>
			<div class="col-md-4">
				<input type="number" id="type" name="type" class="form-control input-md">
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
<jsp:include page="../footer.jsp" />