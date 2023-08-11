<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="error.jsp"%>
 <%@ page import="com.controller.*, com.database.Dao_View, com.entity.User, java.util.*" %>   
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
	if (session.getAttribute("username") == null) {

		response.sendRedirect("Welcome");
	

	} else {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	}
	%>


<%
int count=0;
List<User> list = (List)request.getSession().getAttribute("data"); %>



    <section id="main-content">


        <div class="container-fluid">
            <div class="row min-vh-100 flex-column flex-md-row">
                <div class="col-md-2 col-lg-2 sidebar-bg side-content ">



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

                    <h1 class="main-title">VIEW</h1>

                    <hr>



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
                    <div class="main-content-Table table-responsive">



                        <table class="table table-hover table-striped">

                            <thead class="table-dark text-left">


                                <tr>
                                    <th>ID</th>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Address</th>
                                    <th>Phone</th>
                                    <th>Job_Title</th>
                                 
                                </tr>



                            </thead>

                            <%for (User user:list){
                                count++;%>
                                <tbody>

                                    <tr>
                                        <td>
                                            <%=count %>
                                        </td>
                                        <td> <img src="http://theclientele.s3.amazonaws.com/images/<%=user.getFile()%>" alt="" width="50" height="80" class="img-fluid"> </td>
                                        <td>
                                            <%=user.getFullname() %>
                                        </td>
                                        <td>
                                            <%=user.getEmail() %>
                                        </td>
                                        <td>
                                            <%=user.getAddress() %>
                                        </td>
                                        <td>
                                            <%=user.getPhone() %>
                                        </td>
                                        <td>
                                            <%=user.getJob_title() %>
                                        </td>
                                      
                                    </tr>
                                    <%} %>

                                </tbody>



                        </table>

                    </div>



                </div>
            </div>


    </section>









</body>
</html>