<jsp:include page="../header.jsp" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
		<h1 class="h2">Product Activation</h1>
	</div>
	<form class="form-horizontal" action="activate" method="post">
		<fieldset>
			<!-- Text input-->
			<div class="form-group">
				<div class="col-md-4">
					<input id="ProductKey" name="ProductKey" type="text"
						placeholder="Product Key" class="form-control input-md" required>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<div class="col-md-4">
					<button id="activate" name="activate" class="btn btn-primary">Activate</button>
				</div>
			</div>

		</fieldset>
	</form>
</main>
<jsp:include page="../footer.jsp" />