<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container-fluid">
	<div class="row">
		<nav class="col-md-2 d-none d-md-block bg-light sidebar">
			<div class="sidebar-sticky">
				<h6
					class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
					<span>User Zone</span>
				</h6>
				<ul class="nav flex-column">

					<c:if test="${user == null }">
						<li class="nav-item"><a class="nav-link" href="/register">
								<span data-feather="user-plus"></span> Register
						</a></li>
					</c:if>

					<c:if test="${user == null }">
						<li class="nav-item"><a class="nav-link" href="/login"> <span
								data-feather="log-in"></span> Login
						</a></li>
					</c:if>

					<c:if test="${user != null }">
						<li class="nav-item"><a class="nav-link" href="/user"> <span
								data-feather="home"></span> User Dashboard
						</a></li>
					</c:if>
					
					<c:if test="${user != null }">
						<li class="nav-item"><a class="nav-link" href="/destroy"> <span
								data-feather="user-x"></span> Logout
						</a></li>
					</c:if>

					<c:if test="${user != null }">
						<li class="nav-item"><a class="nav-link" href="/user/licenses">
								<span data-feather="activity"></span> My Licenses
						</a></li>
					</c:if>

					<c:if test="${user != null }">
						<li class="nav-item"><a class="nav-link" href="/user/licenses/activate">
								<span data-feather="key"></span> Activate License
						</a></li>
					</c:if>

				</ul>
				<c:if test="${user.isDeveloper()}">
					<h6
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
						<span>Developer Zone</span>
					</h6>

					<ul class="nav flex-column mb-2">
						<li class="nav-item"><a class="nav-link" href="/app/list">
								<span data-feather="terminal"></span> My Applications
						</a>
						<li class="nav-item"><a class="nav-link" href="/app/new">
								<span data-feather="plus"></span> Add Application
						</a></li>
					</ul>
				</c:if>
				<c:if test="${!user.isDeveloper() && user != null}">
					<h6
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
						<span>Developer Zone</span>
					</h6>

					<ul class="nav flex-column mb-2">
						<li class="nav-item"><a class="nav-link" href="/user/upgrade">
								<span data-feather="terminal"></span> Become a Developer!
						</a>
					</ul>
				</c:if>
			</div>
		</nav>