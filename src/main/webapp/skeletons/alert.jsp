<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${ alert != null }">
<div class="alert alert-${alert.getType()} alert-dismissible fade show" role="alert">
  ${alert.getMessage()}
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
<% session.removeAttribute("alert"); %>
</c:if>