<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap Link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <!-- font-family: 'Lato', sans-serif;  font-family: 'Dosis', sans-serif; font-family: 'Open Sans', sans-serif;       -->
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,300;0,400;1,300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@300;400&family=Open+Sans:ital,wght@0,300;0,400;1,300&display=swap" rel="stylesheet">


    <!-- external css -->
    <!-- <link rel="stylesheet" href="css/style.css"> -->
    <link rel="stylesheet" href="css/admin.css">
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
	if (session.getAttribute("administrator") == null) {

		response.sendRedirect("LoginPage");

	}

	else {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		response.setDateHeader("Expires", -1);

	}
	%>

  <section id="main-content">
        <div class="container-fluid">
            <div class="row min-vh-100 flex-column flex-md-row">
                <div class="col-md-2 col-lg-2 sidebar-bg side-content">
                    <nav class="navbar navbar-expand-md navbar-dark flex-md-column flex-row">
                        <div class="admin-logo text-center my-5 text-white ">
                            <img src="img/jerome.webp" alt="" class="img-fluid rounded-circle" />

                            <p>Jerome Obogne</p>
                            <p id="admin-text">Administrator</p>
                        </div>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navigation">
                            <ul class="navbar-nav flex-column">
                                <li class="nav-item">
                                    <a href="Admin" class="nav-link"><i
                                            class="fa-solid fa-gauge icon"></i>Dashboard</a>
                                </li>
                                <li class="nav-item">
                                    <a href="AdminView" class="nav-link "><i class="fa-solid fa-eye icon"></i>View</a>
                                </li>

                                <li class="nav-item">
                                    <a href="Approval" class="nav-link"><i class="fa-solid fa-check-to-slot icon"></i>Approval</a>
                                </li>



                                <li class="nav-item">
                                    <a href="Create_Account" class="nav-link bg-white text-dark"><i
                                            class="fa-solid fa-circle-plus icon"></i>Add</a>
                                </li>
                                <li class="nav-item dropdown dropend">
                                    <a href="" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i
                                            class="fa-solid fa-user-plus icon"></i>Account</a>
                                    <ul class="dropdown-menu">
                                       
                                        <li>
                                            <a href="AdminChangePass" class="dropdown-item">Change Password</a>
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
                    <h1 class="main-title">CREATE ACCOUNT</h1>

                    <hr />


                    <div class="main-input">


                        <div class="main-width ms-auto">

                            <form action="<%=request.getContextPath() %>/Controller_Search" method="get">

                                <div class="d-flex flex-row ">

                                    <button class="btn btn-primary" type="submit">Search</button>
                                    <span><i class="fa-solid fa-magnifying-glass"></i></span>
                                    <input type="text" name="fname" id=" " class="form-control ms-3 " autofocus placeholder="Enter Name..... ">

                                </div>
                            </form>

                        </div>

                    </div>

                    <div class="form-width p-2">


                        <form action="<%=request.getContextPath() %>/Controller_CreateAccount" method="post" enctype="multipart/form-data">


                            <div class="row justify-content-between mt-5 ">



                                <div class="col-md-6 col-lg-4">
                                   <div class="input-control">
                                        <label for="" class="form-label">First Name</label>
                                        <input type="text" name="f_name" class="form-control" id="fname" onkeyup="validateName()">
                                        <div id="fname-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-4">
                                   <div class="input-control">
                                        <label for="" class="form-label">Last Name</label>
                                        <input type="text" name="l_name" class="form-control" id="lname" onkeyup="validateLname()">
                                        <div id="lname-error" class="error"></div>

                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-4">
                                    <label for="" class="form-label">Middle Name</label>
                                    <input type="text" name="mi" class="form-control">
                                </div>

                                <div class="col-md-6 col-lg-4">
                                     <div class="input-control">
                                        <label for="" class="form-label">Email</label>
                                        <input type="email" name="email" class="form-control" id="email" onkeyup="validateEmail()">
                                        <div id="email-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-4">
                                  <div class="input-control">
                                        <label for="" class="form-label">Address</label>
                                        <input type="text" name="addr" class="form-control" id="addr" onkeyup="validateAddr()">
                                        <div id="addr-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-4">
                                    <div class="input-control">
                                        <label for="" class="form-label">Phone</label>
                                        <input type="text" name="phone" class="form-control" id="phone" onkeyup="validateNumber()">
                                        <div id="ph-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-4 ">
                                    <div class="input-control">
                                        <label for="" class="form-label">Username</label>
                                        <input type="text" name="user" placeholder="Username*" class="form-control" id="username" onkeyup="validateUname()">

                                        <div id="user-error" class="error"></div>
                                    </div>
                                </div>
                                
                                
                                <div class="col-md-6 col-lg-4 ">
                                  <div class="input-control">
                                        <label for="" class="form-label">Password</label>
                                        <input type="password" name="pass" placeholder="Password*" class="form-control" id="password" onkeyup="validatePass()">
                                        <div id="pass-error" class="error"></div>
                                    </div>
                                </div>
                                
                                
                                <div class="col-md-6 col-lg-4 mb-4">
                                      <div class="input-control">
                                        <label for="" class="form-label">Job Title</label>
                                        <input type="text" name="job" class="form-control" id="job" onkeyup="validateJob()">
                                        <div id="job-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-6">

                                  
                                    <div class="input-control">
                                        <input type="file" name="file" class="form-control" id="files" onkeyup="validateEditFile()">
                                        <div id="file-error" class="error"></div>
                                    </div>
                                </div>
                                
                                
                                
                                
                                <div class="col-md-6 col-lg-6 mb-4">
                                    <select name="status" id="" class="form-select" required>
                                        <option value="" selected disabled >Active</option>
                                        <option value="Yes">Yes</option>
                                        <option value="No">No</option>
                                    </select>
                                </div>


                                <input type="hidden" name="role" value="User">

                                <%
                                if (request.getSession().getAttribute("msg_create") != null) {
                                %>

                                    <p>${msg_create}</p>
                                    <%
                                }
                                request.getSession().removeAttribute("msg_create");
                                %>


                                        <div class="col-lg-12 mt-3">
                                            <div class="input-control">
                                            <button class="btn btn-primary  d-block ms-auto" type="submit" onclick="return validateCreateForm()">Add<i class="fa-solid fa-plus icon"></i></button>
                                            
                                            
                                        <div id="submit-error" class="error"></div>
                                        </div>
                                        </div>


                            </div>


                        </form>
                    </div>



                </div>
            </div>
        </div>
    </section>
			




 <script src="js/external.js"></script>
</body>
</html>