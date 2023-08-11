<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap Link -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">


<!-- font-family: 'Lato', sans-serif;  font-family: 'Dosis', sans-serif; font-family: 'Open Sans', sans-serif;       -->
<link
	href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;1,300&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Dosis:wght@300;400&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Dosis:wght@300;400&family=Open+Sans:ital,wght@0,300;0,400;1,300&display=swap"
	rel="stylesheet">

<!-- external css -->
<link rel="stylesheet" href="css/styles.css">


<!-- font awesome kit -->
<script src="https://kit.fontawesome.com/f8210108fd.js"
	crossorigin="anonymous"></script>

<title>User Management</title>
</head>
<body>
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	

	

  <section id="nav-content">
        <div class="container">


            <nav class="navbar navbar-expand-md navbar-dark">
                <a class="navbar-brand" href="#">
                    <h3> J.O.O</h3>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

                <div class="collapse navbar-collapse" id="navigation">

                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a href="Welcome" class="nav-link">Home</a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">About</a>
                        </li>
                        <li class="nav-item">
                            <a href="LoginPage" class="nav-link" id="line">Login</a>
                        </li>
                    </ul>


                </div>

            </nav>
        </div>
    </section>

    <section id="main-content">



        <div class="container ">


            <div class="Form-width">
                <div class="myCard">
                    <div class="row">

                        <div class="col-lg-4 col-md-4 ">

                            <div class="first-box">
                                <img src="img/avatar-icon.webp" alt="" class="img-fluid">
                                <h1>[U.M.S]</h1>
                            </div>

                        </div>


                        <div class="col-lg-8  col-md-8 second-box">


	<div class="text-center mb-4">
		<%
		if (request.getSession().getAttribute("msg") != null) {
		%>

		<p class="text-success">${msg}</p>
		<%
		request.getSession().removeAttribute("msg");
		}
	
		else{%>
			<p class="text-danger">Enter Your Email Address and Will Send you a Link to Reset Password</p>
	<%	}%>
	
	</div>
                            <form action="<%=request.getContextPath() %>/Controller_ForgotPassword" method="get" class="px-4">

                                <h1 class="text-center mb-4">Forgot Password </h1>


                                <div class="col-lg-10 d-block mx-auto mb-4">
                                    <input type="email" name="email" placeholder="Enter Email*" class="form-control" id="email" onkeyup="validateEmail()">
                                </div>
                                  <div class="input-control">
                                    <div id="email-error" class="error"></div>
                                </div>


                                <div class="col-lg-10 d-block mx-auto mt-3">
                                    <div class="input-control">
                                        <button class="btn btn-primary w-50 d-block ms-auto" type="submit" onclick="return validateForgotPass()">Send Email</button>
                                        <div id="submit-error" class="error"></div>
                                    </div>

                                </div>




                                <div class="form-link mt-3">
                                    <p>Not Registered?</p>
                                    <p><span><a href="RegistrationPage">Create Account</a></span></p>
                                </div>

                            </form>


                        </div>

                    </div>


                </div>


            </div>
        </div>
     


    </section>
 


  <script src="js/external.js"></script>

</body>
</html>