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
	<!-- External js -->
	
	


<title>User Management</title>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>







	<%
	if (session.getAttribute("reg_success") != null) {
	
		response.sendRedirect("LoginPage");
	session.removeAttribute("reg_success");
	}
	%>


	<%  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
response.setDateHeader("Expires", -1); %>
			
 
 
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

                            <form id="form" action="<%=request.getContextPath() %>/Controller_Registration" method="post" enctype="multipart/form-data">

                                <div class="row">

                                       <div class="col-lg-6  mb-3">
                                          <div class="input-control">
                                            <input type="text" name="f_name" placeholder="First Name *" id="fname" class="form-control" onkeyup="validateName()">
                                            <div id="fname-error" class="error"></div>
                                        </div>
                                    </div>
                                    

                                   <div class="col-lg-6 mb-3">
                                        <div class="input-control">
                                            <input type="text" name="addr" placeholder="Address*" class="form-control" id="addr" onkeyup="validateAddr()">
                                            <div id="addr-error" class="error"></div>
                                        </div>
                                    </div>
                                    
                                     <div class="col-lg-6 mb-3">
                                        <div class="input-control">
                                            <input type="text" name="l_name" placeholder="Last Name*" class="form-control" id="lname" onkeyup="validateLname()">
                                            <div id="lname-error" class="error"></div>

                                        </div>

                                    </div>
                                    
                                 <div class="col-lg-6 mb-3">
                                        <div class="input-control">
                                            <input type="text" name="phone" placeholder="Phone" class="form-control" id="phone" onkeyup="validateNumber()">
                                            <div id="ph-error" class="error"></div>
                                        </div>

                                    </div>
                                    
                                    
                                    
                                      <div class="col-lg-6 mb-3">

                                         <div class="input-control">
                                            <input type="text" name="mi" placeholder="Middle Initial*" class="form-control" id="mi">
                                            <div id="" class="error"></div>
                                        </div>

                                    </div>
                                   
                                  <div class="col-lg-6 mb-3">
                                        <div class="input-control">
                                            <input type="text" name="user" placeholder="Username*" class="form-control" id="username" onkeyup="validateUname()">

                                            <div id="user-error" class="error"></div>
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    
                                   <div class="col-lg-6 mb-3">
                                          <div class="input-control">
                                            <input type="email" name="email" placeholder="Email*" class="form-control" id="email" onkeyup="validateEmail()">
                                            <div id="email-error" class="error"></div>
                                        </div>

                                    </div>

                                    
                                    <div class="col-lg-6 mb-3">
                                      <div class="input-control">
                                            <input type="password" name="pass" placeholder="Password*" class="form-control" id="password" onkeyup="validatePass()">
                                            <div id="pass-error" class="error"></div>
                                        </div>

                                    </div>

                                   <div class="col-lg-12 mb-3">
                                        <div class="input-control">
                                            <input type="text" name="job" placeholder="Job Title*" class="form-control" id="job" onkeyup="validateJob()">
                                            <div id="job-error" class="error"></div>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="col-lg-12 mb-3">
                                       <div class="input-control">
                                            <input type="file" name="file" placeholder="File Name*" class="form-control" id="files" onkeyup="validateFile()">
                                            <div id="file-error" class="error"></div>
                                        </div>
                                    </div>
                                    
						<input type="hidden" name="role" value="User">

                                    <%
                                    if (request.getSession().getAttribute("msg_reg") != null) {
                                    %>

                                        <p>${msg_reg}</p>
                                        <%
                                    }
                                    request.getSession().removeAttribute("msg_reg");
                                    %>


                                            <div class="col-lg-12 mt-3">
                                              <div class="input-control">
                                            <button class="btn btn-primary w-25 d-block ms-auto" type="submit" onclick="return validateForm()">Register</button>
                                            <div id="submit-error" class="error"></div>
                                        </div>
                                            </div>

                                </div>


                            </form>


                        </div>

                    </div>


                </div>


            </div>
        </div>
      



    </section>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

  <script defer src="js/external.js"></script>
</body>
</html>