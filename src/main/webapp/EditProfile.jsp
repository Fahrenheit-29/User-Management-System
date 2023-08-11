<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.entity.*"%>
<%@ page errorPage="error.jsp"%>
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
	if (session.getAttribute("username") == null) {

		response.sendRedirect("LoginPage");
	

	} else {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	}
	%>




<%
	User userprofile = (User) session.getAttribute("userprofile");
	request.setAttribute("user", userprofile);%>


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
                                            <a href="ChangePassword" class="dropdown-item">Change Password</a>
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

                    <h1 class="main-title">EDIT PROFILE</h1>

                    <hr>



                    <div class="form-width p-2">


                        <img src="http://theclientele.s3.amazonaws.com/images/<%=userprofile.getFile()%>" alt="" width="50" height="80" class="img-fluid rounded-circle">


                        <form action="<%=request.getContextPath() %>/Controller_EditProfile" method="post" enctype="multipart/form-data">

                            <div class="row justify-content-between mt-5 ">


                                <input type="hidden" name="id" value="${user.id}">
                                <div class="col-md-6 col-lg-4">
                                     <div class="input-control">
                                     
                                    <label for="" class="form-label">First Name</label>
                                    <input type="text" name="fname" class="form-control" id="fname" onkeyup="validateName()"  value="${user.first_name}">
                                      <div id="fname-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-12 col-lg-4">
                                
                                 <div class="input-control">
                                 
                                    <label for="" class="form-label">Last Name</label>
                                    <input type="text" name="lname" class="form-control" id="lname" onkeyup="validateLname()" value="${user.last_name}">
                                     <div id="lname-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-4">
                                    <label for="" class="form-label">Middle Name</label>
                                    <input type="text" name="mi" class="form-control" value="${user.middle_initial}">
                                </div>

                                <div class="col-md-6 col-lg-4">
                                
  									<div class="input-control">
                                    <label for="" class="form-label">Email</label>
                                    <input type="email" name="email" class="form-control" id="email" onkeyup="validateEmail()" value="${user.email}">
                                    <div id="email-error" class="error"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-lg-4">
                                
                                  <div class="input-control">
                                    <label for="" class="form-label">Address</label>
                                    <input type="text" name="addr" class="form-control" id="addr" onkeyup="validateAddr()"  value="${user.address}">
                                     <div id="addr-error" class="error"></div>
                                    </div>
                                    
                                </div>

                                <div class="col-md-6 col-lg-4">
                                
                                  <div class="input-control">
                                    <label for="" class="form-label">Phone</label>
                                    <input type="text" name="phone" class="form-control"  id="phone" onkeyup="validateNumber()" value="${user.phone}">
                                     <div id="ph-error" class="error"></div>
                                    </div>
                                    
                                </div>


                                <div class="col-md-6 col-lg-6 mb-4">
                                
                                 <div class="input-control">
                                    <label for="" class="form-label">Job Title</label>
                                    <input type="text" name="job" class="form-control"  id="job" onkeyup="validateJob()" value="${user.job_title }">
                                    
                                     <div id="job-error" class="error"></div>
                                  </div>
                                    
                                </div>

                                <div class="col-md-12 col-lg-6 mt-4">

									 <div class="input-control">
                                    <input type="file" name="file" class="form-control" id="files" onkeyup="validateEditFile()">
                                    <div id="file-error" class="error"></div>
                                    </div>
                                </div>

                                <input type="hidden" name="oldfile" value="${user.file}">
                                <!-- get the file for file upload in admin update side -->

                                <%if (request.getSession().getAttribute("msg")!=null){ %>

                                    <p>${msg}</p>
                                    <br>


                                    <%}request.getSession().removeAttribute("msg");  %>




                            </div>
                            <div class="col-lg-12 mt-3">
                            	
                            	<div class="input-control">
                            
                            
                                <button class="btn btn-primary  d-block ms-auto" type="submit" onclick="return validateUpdate()">Update<i class="fa-solid fa-rotate"></i></button>
                                 <div id="submit-error" class="error"></div>
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