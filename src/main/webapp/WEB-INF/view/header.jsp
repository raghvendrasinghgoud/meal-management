<%@page import="com.meal.helper.Utility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    <%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<header>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Meal Management</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home <span class="sr-only">(current)</span></a>
      </li>
      <sec:authorize access="hasAnyAuthority('admin','manager')">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/user/users">Users <span class="sr-only">(current)</span></a>
      </li>
      </sec:authorize>
      <sec:authorize access="hasAuthority('admin')">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/role/roles">Roles <span class="sr-only">(current)</span></a>
      </li>
      </sec:authorize>
      <sec:authorize access="hasAuthority('admin')">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/meal/meals">Meals <span class="sr-only">(current)</span></a>
      </li>
      </sec:authorize>
    </ul>
  </div>
  <div class="nav-item dropdown">
       
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
       	Hey <sec:authentication property="principal.username" />
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/payment/get/<sec:authentication property="principal.username" />">My Profile</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
          <sec:authorize access="hasAnyAuthority('admin','manager','user')">  
          <a class="dropdown-item" href="${pageContext.request.contextPath}/consumedMeal/consumedMeals/<sec:authentication property="principal.username" />" >My Meal Account</a>
          </sec:authorize>
        </div>
      </div>
</nav>
</header>
