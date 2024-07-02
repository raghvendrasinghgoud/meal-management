<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<%@include file="cdn.jsp" %>

<title>User Overview</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/home.css" >

</head>
<body>

<!-- header  -->
<%@ include file="header.jsp" %>

<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
        <form:form action="${pageContext.request.contextPath}/user/save" method="POST" enctype="multipart/form-data" modelAttribute="user" >
        
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
        	<div class="form-group">
        <!-- profile pic upload -->
        	<div class="container">
			    <div class="picture-container">
			<div class="form-group d-none ">
			<label for="ID" >ProfilePic ID</label>
			<form:input  class="form-control" path="profilePic.id" id="ID" />
			</div>
			
			<div class="form-group d-none">
			<label for="picName" >ProfilePic Name</label>
			<form:input  class="form-control" path="profilePic.name" id="picName" />
			</div>
			
			<div class="form-group d-none">
			<label for="picSize" >ProfilePic ID</label>
			<form:input  class="form-control" path="profilePic.size" id="picSize" />
			</div>
			
			        <div class="picture">
			            <img src="image/${user.profilePic.name}" class="picture-src" id="wizardPicturePreview" title="">
			            
			            <input type="file" id="wizard-picture" class="" name="imageFile" />
			        </div>
			         <h6 class="">Choose Picture</h6>
			    </div>
			    <form:errors path="imageFile" class="text-danger" />
			</div>
			
			</div>
			
			<!-- User id field -->
			<div class="form-group d-none">
			<label for="ID" >User ID</label>
			<form:input  class="form-control" path="id" id="ID" />
			</div>
			
			<!-- first name field -->
			<div class="form-group">
			<label for="fName" >First Name</label>
			<form:input  class="form-control" path="firstName" id="fName" />
			<form:errors path="firstName" class="text-danger" />
			</div>
			<!-- last name field -->
			<div class="form-group">
			<label for="lName" >Last Name</label>
			<form:input  class="form-control" path="lastName" id="lName" />
			<form:errors path="lastName" class="text-danger" />
			</div>
			
			<!-- select gender -->
			<div class="form-group">
			<label>Gender</label>
			<div class="form-check">
			  <form:radiobutton checked="true" class="form-check-input" path="gender" id="exampleRadios1" value="male" />
			  <label class="form-check-label" for="exampleRadios1">
			    Male
			  </label>
			</div>
			
			
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="gender" id="exampleRadios2" value="female" />
			  <label class="form-check-label" for="exampleRadios2">
			   Female
			  </label>
			</div>
			<form:errors path="gender" class="text-danger" />
			</div>
			<!-- date of birth -->
			<div class="form-group">
			<label for="birthDate">Date of Birth</label>
			<input type="date" class="form-control" name="dob" value="<fmt:formatDate value='${user.dob}' pattern='yyyy-MM-dd'/>" id="birthDate"  />	
			<form:errors path="dob" class="text-danger" />					
			</div>
			
			<!-- contact fields -->
			<div class="form-group d-none">
			<label for="cid" >Contact ID</label>
			<form:input  class="form-control" path="contact.id" id="cid" />
			<form:errors path="contact.id" class="text-danger" />
			</div>
			<div class="form-group">
			<label for="primary" >Phone Number</label>
			<form:input  class="form-control" path="contact.primaryPhone" id="primary" />
			<form:errors path="contact.primaryPhone" class="text-danger" />
			</div>
			<div class="form-group">
			<label for="alternate" >Alternate Phone Number</label>
			<form:input  class="form-control" path="contact.alternatePhone" id="alternate" />
			<form:errors path="contact.alternatePhone" class="text-danger" />
			</div>
			<div class="form-group">
			<label for="mail" >Email</label>
			<form:input  class="form-control" path="contact.email" id="mail" />
			<form:errors path="contact.email" class="text-danger" />
			</div>
			
			<!--idproof fields -->
			
			<div class="form-group d-none">
			<label for="proofid" >IDType ID</label>
			<form:input  class="form-control" path="idProof.id" id="proofid" />
			<form:errors path="idProof.id" class="text-danger" />
			</div>
			
			<div class="form-group">
			<label>ID Type</label>
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="idProof.idType" id="exampleRadios4" value="AADHAR_NO" />
			  <label class="form-check-label" for="exampleRadios4">
			    Aadhar Number
			  </label>
			</div>
			
			
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="idProof.idType" id="exampleRadios5" value="VOTER_ID" />
			  <label class="form-check-label" for="exampleRadios5">
			   Voter ID
			  </label>
			</div>
			
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="idProof.idType" id="exampleRadios6" value="DRIVING_LIECENCE" />
			  <label class="form-check-label" for="exampleRadios6">
			   Driving Liecence
			  </label>
			</div>
			
			
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="idProof.idType" id="exampleRadios7" value="PAN_NO" />
			  <label class="form-check-label" for="exampleRadios7">
			   PAN Number
			  </label>
			</div>
			<form:errors path="idProof.idType" class="text-danger" />
			</div>
			
			<div class="form-group">
			<label for="idnum" >ID Number</label>
			<form:input  class="form-control" path="idProof.idProofNum" id="idnum" />
			<form:errors path="idProof.idProofNum" class="text-danger" />
			</div>
			<!--  address fields -->
			<hr/>
			<div class="form-group">
			<label>Current Address</label>
			</div>
			
			<div class="form-group d-none">
			<label for="caddid" >Current Addres ID</label>
			<form:input  class="form-control" path="currentAddress.id" id="caddid" />
			<form:errors path="currentAddress.id" class="text-danger" />
			</div>
			
			<div class="row">
			<div class="form-group col-3">
			<label for="Hno" >House No.</label>
			<form:input  class="form-control" path="currentAddress.houseNo" id="Hno" />
			<form:errors path="currentAddress.houseNo" class="text-danger" />
			</div>
			<div class="form-group col-9">
			<label for="col" >Colony</label>
			<form:input  class="form-control" path="currentAddress.colony" id="col" />
			<form:errors path="currentAddress.colony" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="ar" >Area</label>
			<form:input  class="form-control" path="currentAddress.area" id="ar" />
			<form:errors path="currentAddress.area" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="land" >Landmark</label>
			<form:input  class="form-control" path="currentAddress.landmark" id="land" />
			<form:errors path="currentAddress.landmark" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="ct" >City</label>
			<form:input  class="form-control" path="currentAddress.city" id="ct" />
			<form:errors path="currentAddress.city" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="st" >State</label>
			<form:input  class="form-control" path="currentAddress.state" id="st" />
			<form:errors path="currentAddress.state" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="ctr" >Country</label>
			<form:input  class="form-control" path="currentAddress.country" id="ctr" />
			<form:errors path="currentAddress.country" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="pin" >Pincode</label>
			<form:input  class="form-control" path="currentAddress.pincode" id="pin" />
			<form:errors path="currentAddress.pincode" class="text-danger" />
			</div>
			</div>
			
			<hr/>
			<div class="form-group">
			<label>Permanent Address</label>
			</div>
			
			
			<div class="form-check">
			<form:checkbox class="form-check-input" path="sameAsCurrentAddress" id="exampleSelect1" />
			<label class="form-check-label" for="exampleSelect1" >Same as Current Address</label>
			<form:errors path="sameAsCurrentAddress" class="text-danger" />
			</div>
			
			<div class="form-group d-none">
			<label for="paddid" >Permanent Addres ID</label>
			<form:input  class="form-control" path="permanentAddress.id" id="paddid" />
			<form:errors path="permanentAddress.id" class="text-danger" />
			</div>
			
			<div class="row">
			<div class="form-group col-3">
			<label for="pHno" >House No.</label>
			<form:input  class="form-control" path="permanentAddress.houseNo" id="pHno" />
			<form:errors path="permanentAddress.houseNo" class="text-danger" />
			</div>
			<div class="form-group col-9">
			<label for="pcol" >Colony</label>
			<form:input  class="form-control" path="permanentAddress.colony" id="pcol" />
			<form:errors path="permanentAddress.colony" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="par" >Area</label>
			<form:input  class="form-control" path="permanentAddress.area" id="par" />
			<form:errors path="permanentAddress.area" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="pland" >Landmark</label>
			<form:input  class="form-control" path="permanentAddress.landmark" id="pland" />
			<form:errors path="permanentAddress.landmark" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="pct" >City</label>
			<form:input  class="form-control" path="permanentAddress.city" id="pct" />
			<form:errors path="permanentAddress.city" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="pst" >State</label>
			<form:input  class="form-control" path="permanentAddress.state" id="pst" />
			<form:errors path="permanentAddress.state" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="pctr" >Country</label>
			<form:input  class="form-control" path="permanentAddress.country" id="pctr" />
			<form:errors path="permanentAddress.country" class="text-danger" />
			</div>
			<div class="form-group col-6">
			<label for="ppin" >Pincode</label>
			<form:input  class="form-control" path="permanentAddress.pincode" id="ppin" />
			<form:errors path="permanentAddress.pincode" class="text-danger" />
			</div>
			</div>
			
			
			
			<!-- User roles -->
			
			<div class="form-group">
			<label multiple class="my-1 mr-2" for="inlineFormCustomSelectPref">Roles</label>
			  <form:select path="roles" class="form-select" multiple="true" aria-label="multiple select example" >
				  
				  <c:forEach items="${roles}" var="role" >
				  
				  <form:option value="${role.id}">${role.name}</form:option>
				  
				  </c:forEach>
				  
				</form:select>
				<form:errors path="roles" class="text-danger" />
			</div>
			
			<!-- meal account fields -->
						<div class="form-group d-none">
			<label for="maid" >Meal Account ID</label>
			<form:input  class="form-control" path="mealAccount.accNo" id="maid" />
			<form:errors path="mealAccount.accNo" class="text-danger" />
			</div>
			
      </div>
      <div class="modal-footer">
        <a href="${pageContext.request.contextPath}/user/users"><button type="button" class="btn btn-secondary" >Close</button></a>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
        </form:form>
    </div>
  </div>
</div>

<div class="container-fluid mt-5">
	<h2 class="mb-5">User Overview</h2>
	<sec:authorize access="hasAuthority('admin')">
	<div>
	<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong" id="editPage">Add User</button>
	</div>
	</sec:authorize>
	<table class="table mt-3">
  <thead class="thead-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Profile Picture</th>
      <th scope="col">First Name</th>
      <th scope="col">Gender</th>
      <th scope="col">Date of Birth</th>
      <th scope="col">Primary Phone</th>
      <th scope="col">Email</th>
      <th scope="col">ID Type</th>
      <th scope="col">Roles</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
  
  <c:forEach items="${users}" var="user">
    <tr>
      <th scope="row">${user.id}</th>
      <td><img src="image/${user.profilePic.name}" alt="..." width="150" height="100" /> </td>
      <td>${user.firstName}</td>
      <td>${user.gender}</td>
      <td>${user.dob}</td>
      <td>${user.contact.primaryPhone}</td>
      <td>${user.contact.email}</td>
      <td>${user.idProof.idType}</td>
      <td>${user.roles}</td>
      <td>
      <div class="row">
      <sec:authorize access="hasAnyAuthority('admin','manager')">
      <a class="col-4" href="${pageContext.request.contextPath}/payment/get/${user.contact.email}"><button class="btn btn-info btn-sm">View</button></a>
      </sec:authorize>
      <sec:authorize access="hasAuthority('admin')">
      <a class="col-4" href="${pageContext.request.contextPath}/user/update/${user.id}"><button class="btn btn-secondary btn-sm">Edit</button></a>
      </sec:authorize>
      <sec:authorize access="hasAuthority('admin')">
      <a class="col-4" href="${pageContext.request.contextPath}/user/delete/${user.id}"><button class="btn btn-danger btn-sm">Delete</button></a>
      </sec:authorize>
      </div>
      </td>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/home.js" >

</script>
</body>
</html>