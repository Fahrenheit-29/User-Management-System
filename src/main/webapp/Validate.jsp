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










<%if (session.getAttribute("username")!=null){
		
		response.sendRedirect("UserPage");
		
	}
else{
	
	
	 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	 response.setHeader("Pragma", "no-cache");
	 response.setHeader("Expires", "0");
	 response.setDateHeader("Expires", -1); 
}%> 
	
	<%
	if (session.getAttribute("administrator") != null) {

		response.sendRedirect("Admin");

	} else {
		session.removeAttribute("administrator");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");

		response.setDateHeader("Expires", -1);
	}
	%>
    
	
      
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

                            <form action="<%=request.getContextPath() %>/Controller_Login" method="post">


                                <div class="col-lg-5 mb-3">
                                    <select name="role" id="" class="form-select mb-3" required>
                                        <option value="" selected disabled>Account</option>
                                        <option value="Admin">Admin</option>
                                        <option value="User">User</option>
                                    </select>
                                </div>




                                <div class="input-group mb-3 icon col-lg-12">
                                    <span class="input-group-text"><i class="fa-solid fa-user"></i></span>
                                    <input type="text" name="user" class="form-control" placeholder="Username" value="${username1}">

                                </div>


                                <div class="input-group mb-3 icon col-lg-12">
                                    <span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
                                    <input type="password" name="pass" class="form-control" placeholder="Password">

                                </div>
                                
                                     <%if (request.getSession().getAttribute("msg")!=null){ %>

                                    <p class="text-danger">${msg}</p>
                                    <br>


                                    <%}request.getSession().removeAttribute("msg");  %>


                                
                                
                                
                                
                                
                                <div class="form-link1">
                                    <a href="ForgotPassword">Forgot Password?</a>

                                </div>
                                <div class="mt-5">
                                    <button class="btn btn-primary w-25 d-block ms-auto">Login</button>


                                </div>

                           

                            </form>

                            <div class="form-link mt-3">
                                <p>Not Registered?</p>
                                <p><span><a href="RegistrationPage">Create Account</a></span></p>
                            </div>
                        </div>

                    </div>


                </div>


            </div>
        </div>
    



  


    </section>






</body>
</html>