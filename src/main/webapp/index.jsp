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



	<%
	if (session.getAttribute("username") != null) {

		response.sendRedirect("UserPage");

	} else {
		session.removeAttribute("username");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");

		response.setDateHeader("Expires", -1);
	}
	%>



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

                    <ul class="navbar-nav ms-auto text-sm-center">
                        <li class="nav-item">
                            <a href="Welcome" class="nav-link">Home</a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">About</a>
                        </li>
                        <li class="nav-item">
                            <a href="LoginPage" class="nav-link">Login</a>
                        </li>
                    </ul>


                </div>

            </nav>
        </div>
    </section>

    <section id="main-content">



        <div class="container">


            <div class="text-center text-white main-title ">


                <h1>USER CREATE FUTURE</h1>
                <h3>[U.M.S]</h3>
                <h6>User Management System</h6>


                <div class="col-lg-5 col-md-8 col-sm d-block mx-auto mt-3  main-input ">

		

   					 <form action="<%=request.getContextPath()%>/Controller_userSearch" method="get">

                        <span><i class="fa-solid fa-magnifying-glass"></i></span>
                        <input type="text" name="fname" class="form-control" placeholder="Enter Name......." autofocus>



                        <button class="btn  btn-dark mt-3" type="submit">Search</button>

                    </form>


                </div>
            </div>




        </div>



    </section>

    <div class="container">
        <div class="main-image">

            <img src="img/IMAGE2.webp" alt="" class="img-fluid">
        </div>
    </div>












































</body>
</html>