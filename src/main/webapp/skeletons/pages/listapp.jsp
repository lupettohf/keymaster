<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<form class="form-horizontal" action="manage" method="post">
	<fieldset>
		<legend>Active Applications</legend>

		<table class="table" style="">
			<thead>
				<tr>
					<th>Application Name<br></th>
					<th>Description</th>
					<th>Version<br></th>
					<th>Manage<br></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="app" items="${applist}">
				<tr>
					<th scope="row">${app.getName()}</th>
					<td>${app.getDescription()}</td>
					<td>${app.getVersion()}</td>
					<td>
						<button id="manage" name="manage" value="${app.getID()}" class="btn btn-primary">Manage</button>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</fieldset>
</form>
<jsp:include page="../footer.jsp" />