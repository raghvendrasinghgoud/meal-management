<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="ISO-8859-1">


<%@include file="cdn.jsp" %>

<title>Home</title>
</head>
<body>

<!-- header  -->
<%@ include file="header.jsp" %>


<h2 align="center">Welcome to Meal Management</h2>

<div class="container mt-5">

<a href="home" class="text-light" ><div style="height:50px;font-size:30px;" class="container mb-1 col-4 bg-info text-center ">
	<p>Home</p>
</div></a>
<sec:authorize access="hasAnyAuthority('admin','manager')">
<a href="user/users" class="text-light" ><div style="height:50px;font-size:30px;" class="container mb-1 col-4 bg-info text-center ">
	<p>User Overview</p>
</div></a>
</sec:authorize>
<sec:authorize access="hasAuthority('admin')">
<a href="role/roles" class="text-light" ><div style="height:50px;font-size:30px;" class="container mb-1 col-4 bg-info text-center ">
	<p>Role Overview</p>
</div></a>
</sec:authorize>
<sec:authorize access="hasAuthority('admin')">
<a href="meal/meals" class="text-light" ><div style="height:50px;font-size:30px;" class="container mb-1 col-4 bg-info text-center ">
	<p>Meal Overview</p>
</div></a>
</sec:authorize>
</div>

</body>
</html>
