<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<%@include file="cdn.jsp" %>

<title>User Profile</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/home.css" >

</head>
<body>

<!-- header  -->
<%@ include file="header.jsp" %>


<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <form:form action="${pageContext.request.contextPath}/payment/save" method="POST" modelAttribute="payment" >
        
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Add Balance</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
        	<!-- Payment id field -->
			<div class="form-group d-none">
			<label for="rName" >Payment ID</label>
			<form:input  class="form-control" path="id" id="ID" />
			</div>
			
			<!-- Role name field -->
			<div class="form-group">
			<label for="rName" >Amount</label>
			<form:input  class="form-control" path="amount" id="rName" />
			<form:errors path="amount" class="text-danger" />
			</div>
			
			<!--Payment Mode fields -->
			
			<div class="form-group">
			<label>Mode of Payment</label>
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="mode" id="exampleRadios4" value="Cash" />
			  <label class="form-check-label" for="exampleRadios4">
			    Cash
			  </label>
			</div>
			
			
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="mode" id="exampleRadios5" value="UPI" />
			  <label class="form-check-label" for="exampleRadios5">
			   UPI
			  </label>
			</div>
			
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="mode" id="exampleRadios6" value="Bank_Account" />
			  <label class="form-check-label" for="exampleRadios6">
			   Bank Account
			  </label>
			</div>
			
			
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="mode" id="exampleRadios7" value="Card" />
			  <label class="form-check-label" for="exampleRadios7">
			   Card
			  </label>
			</div>
			<form:errors path="mode" class="text-danger" />
			</div>
			
			<!-- Payment.User id field -->
			<div class="form-group d-none">
			<label for="uid" >User ID</label>
			<form:input  class="form-control" path="user.id" id="UID" />
			</div>
			
      </div>
      <div class="modal-footer">
        <a href="${pageContext.request.contextPath}/payment/get/${user.contact.email}"><button type="button" class="btn btn-secondary" >Close</button></a>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
        </form:form>
    </div>
  </div>
</div>

<!-- change password modal -->

<!-- Modal -->
<div class="modal fade" id="exampleModalLong1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <form:form action="${pageContext.request.contextPath}/user/changePass" method="POST" modelAttribute="changePassword" >
        
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Change Password</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			
			<!-- password field -->
			<div class="form-group">
			<label for="pass" >Password</label>
			<form:input  class="form-control" path="password" id="pass" />
			<form:errors path="password" class="text-danger" />
			</div>
			
			<!--confirm fields -->
			<div class="form-group">
			<label for="cpass" >Confirm Password</label>
			<form:input  class="form-control" path="confirmPassword" id="cpass" />
			<form:errors path="confirmPassword" class="text-danger" id="cPassErr" />
			</div>
			
			
			<!-- Payment.User id field -->
			<div class="form-group d-none">
			<label for="uid" >User email</label>
			<form:input  class="form-control" path="user.contact.email" id="UID" />
			</div>
			
      </div>
      <div class="modal-footer">
        <a href="${pageContext.request.contextPath}/payment/get/${user.contact.email}"><button type="button" class="btn btn-secondary" >Close</button></a>
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
        </form:form>
    </div>
  </div>
</div>


<!-- end of change password modal -->


<div class="container-fluid mt-5" >
<div class="row">
	<div class="col-10">
	<h2 class="mb-5">User Profile</h2>
	</div>
	<div class="col-2">
	<sec:authorize access="#changePassword.user.contact.email==authentication.principal.username or hasAuthority('admin')">
	<!-- Button trigger modal -->
		<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModalLong1" id="editPage1" >Change Password</button>
		</sec:authorize>
	</div>
</div>
<div class="row">
	<div class="col-4">
		<img src="${pageContext.request.contextPath}/user/image/${user.profilePic.name}" class="img-fluid" />
	</div>
	<div class="col-4" >
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >ID</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.id}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Full Name</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.firstName} ${user.lastName}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Gender</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.gender}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Date of Birth</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.dob}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Primary Phone</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.contact.primaryPhone}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Alternate Phone</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.contact.alternatePhone}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Email</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.contact.email}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >ID Type</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.idProof.idType}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >ID Number</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.idProof.idProofNum}</p></div>
		</div>
	</div>
	<div class="col-4" >
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Created By</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.createdBy.contact.email}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Created On</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.createdDate}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Modified By</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.modifiedBy.contact.email}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Modified on</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.modifiedDate}</p></div>
		</div>
		<div class="col row mt-5">
			<div class="col-4"><p class="font-weight-bold" >Current Address</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.currentAddress.houseNo},${user.currentAddress.colony},${user.currentAddress.area},
																${user.currentAddress.city},${user.currentAddress.state},${user.currentAddress.pincode},
																${user.currentAddress.country},${user.currentAddress.landmark}</p></div>
		</div>
		<div class="col row ">
			<div class="col-4"><p class="font-weight-bold" >Permanent Address</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.permanentAddress.houseNo},${user.permanentAddress.colony},${user.permanentAddress.area},
																${user.permanentAddress.city},${user.permanentAddress.state},${user.permanentAddress.pincode},
																${user.permanentAddress.country},${user.permanentAddress.landmark}</p></div>
		</div>
		
	</div>
</div>
<div class="row mb-5 mt-5">
	<div class="col-6">
	<h2 >Meal Account Details</h2>
	</div>
	<div class="col-6 d-flex justify-content-end">
		<!-- Button trigger modal -->
		<sec:authorize access="hasAuthority('admin')">
		<button type="button" class="btn btn-primary mr-2" data-toggle="modal" data-target="#exampleModalLong" id="editPage" >Add Balance</button>
		</sec:authorize>
		<a href="${pageContext.request.contextPath}/consumedMeal/consumedMeals/${user.contact.email}"><button class="btn btn-success mr-2">Consume Meal</button></a>
	</div>
</div>
<div class="container m-auto row">
	<div class="col-6">
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Account Number</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.mealAccount.accNo}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Current Balance</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.mealAccount.currentBalance}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Consumed Balance</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.mealAccount.consumedBalance}</p></div>
		</div>
	</div>
	<div class="col-6">
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Created By</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.mealAccount.createdBy.contact.email}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Created On</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.mealAccount.createdDate}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Modified By</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.mealAccount.modifiedBy.contact.email}</p></div>
		</div>
		<div class="col row">
			<div class="col-4"><p class="font-weight-bold" >Modified on</p></div>
			<div class="col-8"><p class="font-weight-normal">${user.mealAccount.modifiedDate}</p></div>
		</div>
	</div>
</div>
</div>

<div class="container-fluid mt-5">
	<h2 class="mb-5">Payment History</h2>

	<table class="table mt-3">
  <thead class="thead-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Amount</th>
      <th scope="col">Mode of Payment</th>
      <th scope="col">Created On</th>
      <th scope="col">Created By</th>
      <th scope="col">Modified On</th>
      <th scope="col">Modified By</th>
      <sec:authorize access="hasAuthority('admin')">
      <th scope="col">Actions</th>
      </sec:authorize>
    </tr>
  </thead>
  <tbody>
  
  <c:forEach items="${payments}" var="payment">
    <tr>
      <th scope="row">${payment.id}</th>
      <td>${payment.amount}</td>
      <td>${payment.mode}</td>
      <td>${payment.createdDate}</td>
      <td>${payment.createdBy.contact.email}</td>
      <td>${payment.modifiedDate}</td>
      <td>${payment.modifiedBy.contact.email}</td>
      <sec:authorize access="hasAuthority('admin')">
      <td><a href="${pageContext.request.contextPath}/payment/update/${payment.id}"><button type="submit" class="btn btn-light btn-sm">Edit</button></a>
      <a href="${pageContext.request.contextPath}/payment/delete/${payment.id}"><button class="btn btn-danger btn-sm">Delete</button></a></td>
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

let passError=false;
passError=${passError};
if(passError){
	document.getElementById('editPage1').click();
}

</script>
</body>
</html>