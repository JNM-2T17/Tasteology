<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="<c:url value="resources/assets/css/bootstrap.min.css"/>">
 <link rel="stylesheet" href="<c:url value="resources/assets/css/loginStyle.css"/>">

<style>

</style>

<script>
    function check() {

        // return true or false, depending on whether you want to allow the `href` property to follow through or not
    }
</script>

</head>
<body>

<div class = "icon">
    <br>
    <h1>Tasteology Admin Login</h1>
    <br>
</div>

<!-- <div class = "logIn">
    <form class="form-inline">
        <span class = "logInLabels"> E-mail: </span>
        <input type="text" class="form-control">
    </form>

    <div>
        <h3>Password: </h3>
    </div>
    
</div> -->
<div class = "logIn">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="input-group col-md-4">
            <span class="input-group-addon" id="basic-addon1">First Name: </span>
            <input type="text" class="form-control" id = "first_name" aria-describedby="basic-addon1">
        </div>
    <div class="col-md-4"></div>
    <br>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="input-group col-md-4">
            <span class="input-group-addon" id="basic-addon1">Last Name: </span>
            <input type="text" class="form-control" id = "last_name" aria-describedby="basic-addon1">
        </div>
    <div class="col-md-4"></div>
    <br>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="input-group col-md-4">
            <span class="input-group-addon" id="basic-addon1">Password: </span>
            <input type="password" class="form-control" id = "pass" aria-describedby="basic-addon1">
        </div>
        <br>
        <div class="col-md-4"></div>
        <div class="col-md-6">
            <a href = "RGhomepage.html" id = "login" class="btn btn-1 btn-1a" onClick = "return check();">LOG IN</a>
        </div>

    <div class="col-md-4"></div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

    <div class = "signUp">
        <h5>Not yet registered? <a href="SignUp.html"><u>Sign Up</u></a></h5>

    </div>
    
</div>


 


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/classie.js"></script>

</body>
</html>

