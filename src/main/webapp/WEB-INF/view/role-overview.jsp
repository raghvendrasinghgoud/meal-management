<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<%@include file="cdn.jsp" %>

<title>Roles Overview</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/home.css" >

</head>
<body>

<!-- header  -->
<%@ include file="header.jsp" %>

<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <form:form action="${pageContext.request.contextPath}/role/save" method="POST" modelAttribute="role" >
        
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Edit Role</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
        	<!-- Role id field -->
			<div class="form-group d-none">
			<label for="rName" >Role ID</label>
			<form:input  class="form-control" path="id" id="ID" />
			</div>
			
			<!-- Role name field -->
			<div class="form-group">
			<label for="rName" >Role Name</label>
			<form:input  class="form-control" path="name" id="rName" />
			<form:errors path="name" class="text-danger" />
			</div>
			
			
      </div>
      <div class="modal-footer">
        <a href="${pageContext.request.contextPath}/role/roles"><button type="button" class="btn btn-secondary" >Close</button></a>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
        </form:form>
    </div>
  </div>
</div>

<div class="container-fluid mt-5">
	<h2 class="mb-5">Role Overview</h2>
	<div>
	<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong" id="editPage" >Add Role</button>
	</div>
	<table class="table mt-3">
  <thead class="thead-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">Created On</th>
      <th scope="col">Created By</th>
      <th scope="col">Modified On</th>
      <th scope="col">Modified By</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
  
  <c:forEach items="${roles}" var="role">
    <tr>
      <th scope="row">${role.id}</th>
      <td>${role.name}</td>
      <td>${role.createdDate}</td>
      <td>${role.createdBy.contact.email}</td>
      <td>${role.modifiedDate}</td>
      <td>${role.modifiedBy.contact.email}</td>
      <td><a href="${pageContext.request.contextPath}/role/update/${role.id}"><button type="submit" class="btn btn-light btn-sm">Edit</button></a>
      <a href="${pageContext.request.contextPath}/role/delete/${role.id}"><button class="btn btn-danger btn-sm">Delete</button></a></td>
    </tr>
    </c:forEach>
    
 
  </tbody>
</table>
</div>

<script type="text/javascript">

let id=document.getElementById('ID').value;
console.log(`id=${id}`)
let hasError=false;
hasError=${hasError};
if(id>0 || hasError){
	
	document.getElementById('editPage').click();
}

</script>

<!-- <script type="text/javascript" src="<c:url value="resources/static/js/home.js" />"> 

</script>  -->
</body>
</html>