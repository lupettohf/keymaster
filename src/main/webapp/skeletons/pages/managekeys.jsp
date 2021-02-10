
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">Managing keys for ${license.getName()}
			(${app.getName()})</h1>
	</div>
	<div class="card" style="">
		<div class="card-body">
			<h4 class="card-title">
				Total Keys: ${totalkeys} <small>(${totalredeemed} redeemed)</small><br>
			</h4>
			<p class="card-text">
			<form class="form-horizontal" action="manage" method="post">
				<fieldset>
					<!-- Number input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="keys_togen">Keys
							To Generate</label>
						<div class="col-md-4">
							<input type="number" id="keys_togen" name="keys_togen"
								class="form-control input-md" placeholder="10" required>
						</div>
					</div>

					<!-- Button -->
					<div class="form-group">
						<div class="col-md-4">
							<button id="action" name="action" class="btn btn-primary"
								value="generate">Generate</button>
							<a href="download" target="_blank" class="btn btn-primary">Download
								Keys CSV</a>
						</div>
					</div>

				</fieldset>
			</form>
			</p>
		</div>
	</div>
	<hr>
	<c:if test="${keys != null}">
		<div class="card" style="">
			<div class="card-body">
				<table class="table" style="">
					<thead>
						<tr>
							<th>License Key<br></th>
							<th>Redeemed</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="key" items="${keys}">
							<tr>
								<th>${key.getLicenseKey()}</th>
								<td scope="row"><c:if test="${key.isRedeemed()}">
										<span data-feather="check"></span>
									</c:if> <c:if test="${!key.isRedeemed()}">
										<span data-feather="x"></span>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="btn-toolbar mb-2 mb-md-0btn-toolbar mb-2 mb-md-0">
					<div class="btn-group mr-2">
						<c:forEach var="i" begin="0" end="${pages}">
							<c:if test="${ curpage == i }">
								<a href="#" class="btn btn-sm btn-outline-secondary active">${i}</a>
							</c:if>
							<c:if test="${ curpage != i }">
								<a href="manage?page=${i}"
									class="btn btn-sm btn-outline-secondary">${i}</a>
							</c:if>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</c:if>
</main>
<jsp:include page="../footer.jsp" />