<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<%@include file="cdn.jsp" %>

<title>Consumed Meal Overview</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/home.css" >

</head>
<body>

<!-- header  -->
<%@ include file="header.jsp" %>

<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <form:form action="${pageContext.request.contextPath}/consumedMeal/save" method="POST" modelAttribute="consumedMeal" >
        
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Edit Consume Meal</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
        	<!-- Consumed meal id field -->
			<div class="form-group d-none">
			<label for="rName" >Consumed Meal ID</label>
			<form:input  class="form-control" path="id" id="ID" />
			</div>
			
			<!-- Meal name field -->
			<div class="form-group">
			<label for="rName" >Meals</label>
			<form:select  class="form-control" path="meal" id="rName" >
				<c:forEach items="${meals }" var="meal" >
					<form:option value="${meal.id}"  >${meal.name }</form:option>
				</c:forEach>
			</form:select>
			<form:errors path="meal" class="text-danger" />
			</div>
			
			<div class="form-group d-none">
			<label for="rName" >Meal Account ID</label>
			<form:input  class="form-control" path="mealAccount.accNo" id="maID" />
			</div>
			
      </div>
      <div class="modal-footer">
        <a href="${pageContext.request.contextPath}/consumedMeal/consumedMeals"><button type="button" class="btn btn-secondary" >Close</button></a>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
        </form:form>
    </div>
  </div>
</div>

<div class="container-fluid mt-5">
	<h2 class="mb-5">Consumed Meals Overview</h2>
	<div class="container m-auto row">
	<div class="col-6">
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Account Number</p></div>
			<div class="col-8"><p class="font-weight-normal">${consumedMeal.mealAccount.accNo}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Current Balance</p></div>
			<div class="col-8"><p class="font-weight-normal">${consumedMeal.mealAccount.currentBalance}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Consumed Balance</p></div>
			<div class="col-8"><p class="font-weight-normal">${consumedMeal.mealAccount.consumedBalance}</p></div>
		</div>
	</div>
	<div class="col-6">
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Created By</p></div>
			<div class="col-8"><p class="font-weight-normal">${consumedMeal.mealAccount.createdBy.contact.email}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Created On</p></div>
			<div class="col-8"><p class="font-weight-normal">${consumedMeal.mealAccount.createdDate}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Modified By</p></div>
			<div class="col-8"><p class="font-weight-normal">${consumedMeal.mealAccount.modifiedBy.contact.email}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Modified on</p></div>
			<div class="col-8"><p class="font-weight-normal">${consumedMeal.mealAccount.modifiedDate}</p></div>
		</div>
	</div>
</div>
<sec:authorize access="hasAnyAuthority('admin','manager')">
	<div>
	<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong" id="editPage" >Consume Meal</button>
	</div>
	</sec:authorize>
	<table class="table mt-3">
  <thead class="thead-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Meal Name</th>
      <th scope="col">Meal Price</th>
      <th scope="col">Created On</th>
      <th scope="col">Created By</th>
      <th scope="col">Modified On</th>
      <th scope="col">Modified By</th>
      <sec:authorize access="hasAnyAuthority('admin','manager')">
      <th scope="col">Actions</th>
      </sec:authorize>
    </tr>
  </thead>
  <tbody>
  
  <c:forEach items="${consumedMeals}" var="consumedMeal">
    <tr>
      <th scope="row">${consumedMeal.id}</th>
      <td>${consumedMeal.meal.name}</td>
      <td>${consumedMeal.meal.price}</td>
      <td>${consumedMeal.createdDate}</td>
      <td>${consumedMeal.createdBy.contact.email}</td>
      <td>${consumedMeal.modifiedDate}</td>
      <td>${consumedMeal.modifiedBy.contact.email}</td>
      <sec:authorize access="hasAnyAuthority('admin','manager')">
      <td><a href="${pageContext.request.contextPath}/consumedMeal/delete/${consumedMeal.id}"><button class="btn btn-danger btn-sm">Revert</button></a></td>
      </sec:authorize>
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