<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<form class="form-horizontal" action="manage" method="post">
	<fieldset>
		<legend>(Managing ${app.getName()}) Active Licenses</legend>

		<table class="table" style="">
			<thead>
				<tr>
					<th>License Name<br></th>
					<th>Description</th>
					<th>Duration<br></th>
					<th>Type<br></th>
					<th>Manage<br></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="license" items="${licenses}">
				<tr>
					<th scope="row">${license.getName()}</th>
					<td>${license.getDescription()}</td>
					<td>${license.getDuration()}</td>
					<td>${license.getType()}</td>
					<td>
						<button id="manage" name="manage" value="${license.getID()}" class="btn btn-primary">Manage</button>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</fieldset>
</form>
		<form class="form-horizontal" action="new" method="post">
		<div class="form-group">
			<div class="col-md-4">
				<button id="newlicense" name="newlicense"
					class="btn btn-primary" value="true">New License</button>
			</div>
		</div>
		</form>
<jsp:include page="../footer.jsp" />