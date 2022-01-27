<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">Hello ${user.getUsername()}</h1>
	</div>
	<jsp:include page="../alert.jsp" />
	<form class="form-horizontal" action="upgrade" method="post">
		<fieldset>

			<div
				class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
				<h1 class="display-4">
					Developer? Join here!<br>
				</h1>
				<p class="lead">Quickly build an effective pricing table for
					your potential customers with this Bootstrap example. It's built
					with default Bootstrap components and utilities with little
					customization.</p>
			</div>

			<div class="container">
				<div class="card-deck mb-3 text-center">
					<div class="card mb-4 box-shadow">
						<div class="card-header">
							<h4 class="my-0 font-weight-normal"
								style="text-decoration-line: none;">
								Free <small class="text-muted">(during Beta)</small><i></i><br>
							</h4>
						</div>
						<div class="card-body">
							<h1 class="card-title pricing-card-title">
								$0 <small class="text-muted">/ mo</small>
							</h1>
							<ul class="list-unstyled mt-3 mb-4">
								<li>Unlimited Applications<br></li>
								<li>Multi-License Support<br></li>
								<li>Automatic Product Key Generatorion<br></li>
								<li>Easy API<br></li>
							</ul>
							<div class="container">
								<div class="row">
									<div class="col text-center">
										<button class="btn btn-success">Upgrade Now!</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</fieldset>
	</form>
</main>
<jsp:include page="../footer.jsp" />