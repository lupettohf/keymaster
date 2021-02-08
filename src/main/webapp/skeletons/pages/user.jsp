<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   

<jsp:include page="../header.jsp" />
<form class="form-horizontal" action="user" method="post">
<fieldset>

<!-- Form Name -->
<legend>Hello ${user.getUsername()}</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="firstname">First Name</label>  
  <div class="col-md-4">
  <input id="firstname" name="firstname" type="text" placeholder="First Name" class="form-control input-md" required="" value="${user.getFirstName()}">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="lastname">Last Name</label>  
  <div class="col-md-4">
  <input id="lastname" name="lastname" type="text" placeholder="Last Name" class="form-control input-md" required="" value="${user.getLastName()}">
    
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="oldpassword">Old Password</label>
  <div class="col-md-4">
    <input id="password" name="oldpassword" type="password" placeholder="" class="form-control input-md">
    
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">New Password</label>
  <div class="col-md-4">
    <input id="password" name="password" type="password" placeholder="" class="form-control input-md">
    
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password_repeat">New Repeat Password</label>
  <div class="col-md-4">
    <input id="passwordrepeat" name="passwordrepeat" type="password" placeholder="" class="form-control input-md">
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="Update">Update</label>
  <div class="col-md-4">
    <button id="register" name="register" class="btn btn-primary">Update</button>
  </div>
</div>

</fieldset>
</form>
<jsp:include page="../footer.jsp" />