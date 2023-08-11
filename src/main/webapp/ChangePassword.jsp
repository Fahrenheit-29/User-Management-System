<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Bootstrap Link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />

    <!-- font-family: 'Lato', sans-serif;  font-family: 'Dosis', sans-serif; font-family: 'Open Sans', sans-serif;       -->
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;1,300&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@300;400&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@300;400&family=Open+Sans:ital,wght@0,300;0,400;1,300&display=swap" rel="stylesheet" />

    <!-- external css -->
    <!-- <link rel="stylesheet" href="css/style.css"> -->
    <link rel="stylesheet" href="css/admin.css" />
    <!-- font awesome kit -->
    <script src="https://kit.fontawesome.com/f8210108fd.js" crossorigin="anonymous"></script>

<title>User Management</title>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<%
	if (session.getAttribute("username")==null) {

		response.sendRedirect("Welcome");
	

	} else {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	}
	%>



  <section id="main-content">


        <div class="container-fluid">
            <div class="row min-vh-100 flex-column flex-md-row">
                <div class="col-md-2 col-lg-2 sidebar-bg side-content">



                    <nav class="navbar navbar-expand-md navbar-dark flex-md-column flex-row mt-5">

                        <div class="admin-logo text-center my-5 text-white ">





                        </div>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigation">
                        <span class="navbar-toggler-icon"></span>
                        </button>



                        <div class="collapse navbar-collapse" id="navigation">


                            <ul class="navbar-nav flex-column">

                                <li class="nav-item">
                                    <a href="Controller_View" class="nav-link"><i class="fa-solid fa-eye icon"></i>View</a>
                                </li>
                                <li class="nav-item">
                                    <a href="UserPage" class="nav-link"><i class="fa-solid fa-id-card icon"></i>Profile</a>
                                </li>
                                <li class="nav-item dropdown dropend">
                                    <a href="" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa-solid fa-user-plus icon"></i>Account</a>

                                    <ul class="dropdown-menu">

                                        <li>
                                            <a href="CHangePassword" class="dropdown-item">Change Password</a>
                                        </li>
                                        <li>
                                            <a href="Controller_Logout" class="dropdown-item">Logout</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>


                <div class="col-md-10 col-lg-10 main-content">

                    <h1 class="main-title">CHANGE PASSWORD</h1>

                    <hr>

                    <div class="row justify-content-center">


                        <div class="col-md-6 col-lg-5 changepass-img">

                            <img src="img/undraw_Task_re_wi3v.webp" alt="" class="img-fluid d-block mx-auto">


                        </div>
                        <div class="col-md-6 col-lg-5 changepass-content">









                                    <h5>Change Password</h5>

                                    <form action="<%=request.getContextPath() %>/Controller_ChangePassword" method="post">


                                        <label for="" class="form-label">Old Password</label>

                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fa-solid fa-unlock"></i></span>
                                            <input type="password" name="oldpass" id="" class="form-control">
                                        </div>

                                        <label for="" class="form-label">New Password</label>

                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
                                            <input type="password" name="newpass"  id="password" onkeyup="validatePass()" class="form-control">

                                        </div>
                                        
                                          <div class="input-control">
                                    <div id="pass-error" class="error"></div>
                                </div>
                                        
                                        

                                        <label for="" class="form-label">Confirm Password</label>
                                        <div class="input-group">
                                        
                                            <span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
                                            <input type="password" name="confirmpass"  id="cpass" onkeyup="validateConfirmpass()"  class="form-control">
                                            
                                            
                                        </div>

								<div class="input-control">
									<div id="confirm-error" class="error"></div>
								</div>


                            <%if (request.getSession().getAttribute("msg")!=null){ %>

                                <p class="mt-2 text-danger">${msg}</p>
                                <br>


                                <%}request.getSession().removeAttribute("msg");  %>


								<div class="my-5 form-width">
                                        
                                        	 <div class="input-control">
                                        
                                            <button class="btn  btn-primary d-block ms-auto" type="submit"  onclick="return validateResetPass()">Change Password</button>
                                            
                                            
                                             <div id="submit-error" class="error"></div>
                                            </div>
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