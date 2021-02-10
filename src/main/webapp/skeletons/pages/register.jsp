<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">Register</h1>
	</div>
	<form class="form-horizontal" action="register" method="post">
		<fieldset>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">E-Mail</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text"
						placeholder="joe@example.com" class="form-control input-md"
						required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="username">Username</label>
				<div class="col-md-4">
					<input id="username" name="username" type="text"
						placeholder="Username" class="form-control input-md" required="">

				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Password</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password" placeholder=""
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password_repeat">Repeat
					Password</label>
				<div class="col-md-4">
					<input id="password_repeat" name="password_confirm" type="password"
						placeholder="" class="form-control input-md" required="">

				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="register">Register</label>
				<div class="col-md-4">
					<button id="register" name="register" class="btn btn-primary">Register</button>
				</div>
			</div>

		</fieldset>
	</form>
</main>
<jsp:include page="../footer.jsp" />