<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.entity.User, com.database.Dao_View, java.util.*" %>    
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


	<%if (session.getAttribute("administrator")==null){
	
	response.sendRedirect("LoginPage");
}

else{
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	
}%>




<%
int count=0;
Dao_View execution= new Dao_View();
List<User>list=execution.approval_request(); %>



 		
 	

    <section id="main-content">

        <div class="container-fluid">
            <div class="row min-vh-100 flex-column flex-md-row">
                <div class="col-md-2 col-lg-2 sidebar-bg side-content">


 <%if (request.getSession().getAttribute("msg")!=null){ %>
        
        <p>${msg}</p>
        <br>
        
        
        <%}request.getSession().removeAttribute("msg");  %>
        

                    <nav class="navbar navbar-expand-md navbar-dark flex-md-column flex-row">

                        <div class="admin-logo text-center my-5 text-white ">

                            <img src="img/jerome.webp" class="img-fluid rounded-circle">

                            <p>Jerome Obogne
                            </p>
                            <p id="admin-text">Administrator</p>



                        </div>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigation">
                        <span class="navbar-toggler-icon"></span>
                        </button>



                        <div class="collapse navbar-collapse" id="navigation">


                            <ul class="navbar-nav flex-column">
                                <li class="nav-item">
                                    <a href="Admin" class="nav-link"><i class="fa-solid fa-gauge icon"></i>Dashboard</a>
                                </li>
                                <li class="nav-item">
                                    <a href="AdminView" class="nav-link"><i class="fa-solid fa-eye icon"></i>View</a>

                                </li>

                                <li class="nav-item">
                                    <a href="Approval" class="nav-link bg-white text-dark icon"><i class="fa-solid fa-check-to-slot"></i>Approval</a>
                                </li>




                                <li class="nav-item">
                                    <a href="Create_Account" class="nav-link"><i class="fa-solid fa-circle-plus icon"></i>Add</a>
                                </li>
                                <li class="nav-item dropdown dropend">
                                    <a href="" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa-solid fa-user-plus icon"></i>Account</a>
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

                    <h1 class="main-title">APPROVAL REQUEST</h1>

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

                        <div>

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
                                        <th>Status</th>
                                        <th class="text-center ">
                                            Action</th>
                                    </tr>



                                </thead>

                                <%for (User user:list){
                                	count++;%>

                                    <tbody>

                                        
                                            <td>
                                                <%=count %>
                                            </td>

                                            <td>
                                                <img src="http://theclientele.s3.amazonaws.com/images/<%=user.getFile()%>" alt="" width="50" height="80" class="img-fluid">
                                            </td>
                                            
                                            
                                            
                                            <td>
                                                <%=user.getPassword() %>
                                                    <!-- user.getFulname pero to nag base lang dun sa User entity sa java bean -->
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
                                            <td>
                                                <%=user.getUsername() %>
                                            </td>

                                            <td class="text-center table-btn">

                                                <button type="button" class="btn btn-primary" data-bs-toggle="tool-tip" data-bs-placement="top" title="Aprrove Request">


                                            <a href="Controller_Status?id=<%=user.getId() %>&email=<%=user.getEmail() %>">
                                                <i class="fa-solid fa-check"></i>

                                            </a>


                                            </button>


                                                <button type="button" class="btn btn-danger" data-bs-toggle="tool-tip" data-bs-placement="top" title="Reject Request">

                                                    <a href="Controller_Reject?id=<%=user.getId()%>&email=<%=user.getEmail()%>&file=<%=user.getFile()%>">
                                                <i class="fa-solid fa-xmark"></i>
                                            </a>


                                            </button>

                                            </td>

                                            </tr>


                                    </tbody>

                                    <%} if(count==0){  %>

                                        <tr>
                                            <td>NO RECORD FOUND</td>
                                        </tr>
                                        <%} %>


                            </table>

                        </div>


                    </div>

                </div>

            </div>
        </div>


    </section>





 


</body>
</html>