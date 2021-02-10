<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">My Licenses</h1>
	</div>
	<form class="form-horizontal" action="manage" method="post">
		<fieldset>

			<table class="table" style="">
				<thead>
					<tr>
						<th>Application<br></th>
						<th>License Name<br></th>
						<th>Description<br></th>
						<th>Days Left<br></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="owned" items="${ownedlicenses}">
						<tr>
							<th scope="row">${owned.getApplication().getName()}</th>
							<td>${owned.getLicense().getName()}</td>
							<td>${owned.getLicense().getDescription()}</td>
							<td>${owned.getRemaningDays()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</form>
</main>
<jsp:include page="../footer.jsp" />