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
 <link rel="stylesheet" href="css/admin.css">

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
	if (session.getAttribute("username") == null) {

		response.sendRedirect("Welcome");
	

	} else {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	}
	%>


	<%
	User userprofile = (User) session.getAttribute("userprofile");
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
                                
                                
	   <%if (request.getSession().getAttribute("msg_edit")!=null){ %>

        <p>${msg_edit}</p>
        <br>


        <%}request.getSession().removeAttribute("msg_edit");  %>



            <%if (request.getSession().getAttribute("msg_change")!=null){ %>

                <p>${msg_change}</p>
                <br>


                <%}request.getSession().removeAttribute("msg_change");  %>

                                

                                    <h1 class="main-title">USER PROFILE</h1>

                                    <hr>

                                    <div class="row justify-content-evenly main-profile-font">



                                        <div class="col-sm-5 col-md-3 col-lg-2 main-profile">


                                            <div class="profile-content text-sm-center">

                                                <img src="http://theclientele.s3.amazonaws.com/images/<%=userprofile.getFile()%>" alt="" class="profile-pic img-fluid rounded-circle">

                                                <a href="" class="img-link"><i class="fa-solid fa-location-dot icon"></i><%=userprofile.getAddress() %></a>
                                                <a href="" class="img-link"><i class="fa-solid fa-user-tag icon"></i>User ID: <%=userprofile.getId()%></a>

                                                <div class="my-5 form-width">
                                                    <button class="btn  btn-primary d-block"><a href="EditProfile" class="btn-edit">Edit Profile</a></button>
                                                </div>

                                            </div>



                                        </div>



                                        <div class="col-sm-8 col-md-8 col-lg-8 side-profile">

                                            <div class="side-profile-content">
                                                <h1>
                                                    <%=userprofile.getFirst_name() %>
                                                        <%=userprofile.getMiddle_initial() %>
                                                            <%=userprofile.getLast_name() %>.</h1>
                                                <p>
                                                    <%=userprofile.getJob_title()%></p>


                            </div>

                            <h4 class="side-content-about">About</h4>
                            <div class="text-start">
                                <table class="table-content table">

                                    <tr>
                                        <th><i class="fa-solid fa-envelope icon"></i>Email</th>
                                        <td><%=userprofile.getEmail() %>
                                                        </td>
                                                        </tr>
                                                        <tr>
                                                            <th><i class="fa-solid fa-address-book icon"></i>Address</th>
                                                            <td>
                                                                <%=userprofile.getAddress() %>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <th><i class="fa-solid fa-phone icon"></i>Phone</th>
                                                            <td>
                                                                <%=userprofile.getPhone() %>
                                                            </td>
                                                        </tr>


                                            </div>

                                        </table>

                                            <div class="side-description">

                                                <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Provident aut recusandae saepe itaque earum voluptas explicabo sit praesentium dicta dolores, doloribus molestias? Debitis assumenda distinctio quas
                                                    necessitatibus, at mollitia iure.</p>

                                            </div>



                                        </div>
                                    </div>



                                </div>



                            </div>
                        </div>


                    </section>



	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>