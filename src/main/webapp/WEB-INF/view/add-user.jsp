<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%@include file="cdn.jsp" %>

<title>Insert title here</title>
</head>
<body>
<div class="modal-content">
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!-- header  -->
<%@ include file="header.jsp" %>
    
        <form:form action="saveUser" method="POST" enctype="multipart/form-data" modelAttribute="userFormObj" >
        
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
			        <div class="picture">
			            <img src="https://lh3.googleusercontent.com/LfmMVU71g-HKXTCP_QWlDOemmWg4Dn1rJjxeEsZKMNaQprgunDTtEuzmcwUBgupKQVTuP0vczT9bH32ywaF7h68mF-osUSBAeM6MxyhvJhG6HKZMTYjgEv3WkWCfLB7czfODidNQPdja99HMb4qhCY1uFS8X0OQOVGeuhdHy8ln7eyr-6MnkCcy64wl6S_S6ep9j7aJIIopZ9wxk7Iqm-gFjmBtg6KJVkBD0IA6BnS-XlIVpbqL5LYi62elCrbDgiaD6Oe8uluucbYeL1i9kgr4c1b_NBSNe6zFwj7vrju4Zdbax-GPHmiuirf2h86eKdRl7A5h8PXGrCDNIYMID-J7_KuHKqaM-I7W5yI00QDpG9x5q5xOQMgCy1bbu3St1paqt9KHrvNS_SCx-QJgBTOIWW6T0DHVlvV_9YF5UZpN7aV5a79xvN1Gdrc7spvSs82v6gta8AJHCgzNSWQw5QUR8EN_-cTPF6S-vifLa2KtRdRAV7q-CQvhMrbBCaEYY73bQcPZFd9XE7HIbHXwXYA=s200-no" class="picture-src" id="wizardPicturePreview" title="">
			            
			            <input type="file" id="wizard-picture" class="" name="profilePic" />
			        </div>
			         <h6 class="">Choose Picture</h6>
			
			    </div>
			</div>
			</div>
			<!-- first name field -->
			<div class="form-group">
			<label for="fName" >First Name</label>
			<form:input  class="form-control" path="firstName" id="fName" />
			</div>
			<!-- last name field -->
			<div class="form-group">
			<label for="lName" >Last Name</label>
			<form:input  class="form-control" path="lastName" id="lName" />
			</div>
			
			<!-- select gender -->
			<div class="form-group">
			<label>Gender</label>
			<div class="form-check">
			  <form:radiobutton class="form-check-input" path="gender" id="exampleRadios1" value="male" />
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
			</div>
			<!-- date of birth -->
			<div class="form-group">
			<label for="birthDate">Date of Birth</label>
			<form:input type="date" class="form-control" path="dob" id="birthDate"  />						
			</div>
			
			<!-- contact fields -->
			<div class="form-group">
			<label for="primary" >Phone Number</label>
			<form:input  class="form-control" path="contact.primaryPhone" id="primary" />
			</div>
			<div class="form-group">
			<label for="alternate" >Alternate Phone Number</label>
			<form:input  class="form-control" path="contact.alternatePhone" id="alternate" />
			</div>
			<div class="form-group">
			<label for="mail" >Email</label>
			<form:input  class="form-control" path="contact.email" id="mail" />
			</div>
			
			<!-- password fields -->
			<div class="form-group">
			<label for="pass" >Password</label>
			<form:input  class="form-control" path="password" id="pass" />
			</div>
			
			<div class="form-group">
			<label for="cpass" >Confirm Password</label>
			<form:input  class="form-control" path="confirmPassword" id="cpass" />
			</div>
			<!--idproof fields -->
			
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
			
			</div>
			<div class="form-group">
			<label for="idnum" >ID Number</label>
			<form:input  class="form-control" path="idProof.idProofNum" id="idnum" />
			</div>
			<!--  address fields -->
			<hr/>
			<div class="form-group">
			<label>Current Address</label>
			</div>
			<div class="row">
			<div class="form-group col-3">
			<label for="Hno" >House No.</label>
			<form:input  class="form-control" path="currentAddress.houseNo" id="Hno" />
			</div>
			<div class="form-group col-9">
			<label for="col" >Colony</label>
			<form:input  class="form-control" path="currentAddress.colony" id="col" />
			</div>
			<div class="form-group col-6">
			<label for="ar" >Area</label>
			<form:input  class="form-control" path="currentAddress.area" id="ar" />
			</div>
			<div class="form-group col-6">
			<label for="land" >Landmark</label>
			<form:input  class="form-control" path="currentAddress.landmark" id="land" />
			</div>
			<div class="form-group col-6">
			<label for="ct" >City</label>
			<form:input  class="form-control" path="currentAddress.city" id="ct" />
			</div>
			<div class="form-group col-6">
			<label for="st" >State</label>
			<form:input  class="form-control" path="currentAddress.state" id="st" />
			</div>
			<div class="form-group col-6">
			<label for="ctr" >Country</label>
			<form:input  class="form-control" path="currentAddress.country" id="ctr" />
			</div>
			<div class="form-group col-6">
			<label for="pin" >Pincode</label>
			<form:input  class="form-control" path="currentAddress.pincode" id="pin" />
			</div>
			</div>
			
			<hr/>
			<div class="form-group">
			<label>Permanent Address</label>
			</div>
			<div class="form-check">
			<form:checkbox class="form-check-input" path="sameAsCurrentAddress" id="exampleSelect1" />
			<label class="form-check-label" for="exampleSelect1" >Same as Current Address</label>
			</div>
			<div class="row">
			<div class="form-group col-3">
			<label for="pHno" >House No.</label>
			<form:input  class="form-control" path="permanentAddress.houseNo" id="pHno" />
			</div>
			<div class="form-group col-9">
			<label for="pcol" >Colony</label>
			<form:input  class="form-control" path="permanentAddress.colony" id="pcol" />
			</div>
			<div class="form-group col-6">
			<label for="par" >Area</label>
			<form:input  class="form-control" path="permanentAddress.area" id="par" />
			</div>
			<div class="form-group col-6">
			<label for="pland" >Landmark</label>
			<form:input  class="form-control" path="permanentAddress.landmark" id="pland" />
			</div>
			<div class="form-group col-6">
			<label for="pct" >City</label>
			<form:input  class="form-control" path="permanentAddress.city" id="pct" />
			</div>
			<div class="form-group col-6">
			<label for="pst" >State</label>
			<form:input  class="form-control" path="permanentAddress.state" id="pst" />
			</div>
			<div class="form-group col-6">
			<label for="pctr" >Country</label>
			<form:input  class="form-control" path="permanentAddress.country" id="pctr" />
			</div>
			<div class="form-group col-6">
			<label for="ppin" >Pincode</label>
			<form:input  class="form-control" path="permanentAddress.pincode" id="ppin" />
			</div>
			</div>
			
			
			
			<!-- User roles -->
			
			<div class="form-group">
			<label multiple class="my-1 mr-2" for="inlineFormCustomSelectPref">Preference</label>
			  <select class="form-select" multiple aria-label="multiple select example">
				  <option selected>Open this select menu</option>
				  <option value="1">One</option>
				  <option value="2">Two</option>
				  <option value="3">Three</option>
				</select>
			</div>
			
			<!-- meal account fields -->
						
			
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
        </form:form>
    </div>
</body>
</html>