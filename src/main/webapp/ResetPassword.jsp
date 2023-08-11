<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="com.database.Dao_ResetPass" %>
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

<%--if (session.getAttribute("forgotpass")==null){ 
response.sendRedirect("Welcome");


}
--%>

<%if (session.getAttribute("success")!=null){ 
response.sendRedirect("LoginPage");

session.removeAttribute("success");
session.removeAttribute("username");
session.removeAttribute("administrator");

}
else{
	 
	 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	 response.setHeader("Pragma", "no-cache");
	 response.setHeader("Expires", "0");
	 response.setDateHeader("Expires", -1);

}
%>

<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
response.setDateHeader("Expires", -1); %>


 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>




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



                </div>

            </nav>
        </div>
    </section>

    <section id="main-content">



        <div class="container">

<% String token = request.getParameter("key");
Dao_ResetPass execution= new Dao_ResetPass();

boolean validate=execution.validateToken(token);

%>

<% if (validate){%> 

            <div class="Form-width">
                <div class="myCard">
                    <div class="row">

                        <div class="col-lg-4 col-md-4">

                            <div class="first-box">
                                <img src="img/avatar-icon.webp" alt="" class="img-fluid">
                                <h1>[U.M.S]</h1>
                            </div>

                        </div>


                        <div class="col-lg-8  col-md-8 second-box">

                            <form action="<%=request.getContextPath() %>/Controller_ResetPass" method="post" class="px-4">

                                <h1 class="text-center mb-4">Reset Password </h1>


                                <div class="col-lg-10 d-block mx-auto mb-4">
                                
                                 <div class="input-control">
                                    <input type="password" name="new_pass" placeholder="New Password*" class="form-control" id="password" onkeyup="validatePass()">
                                    
                                      <div id="pass-error" class="error"></div>
                                  </div>
                                </div>
                                
                                
                                
                                <div class="col-lg-10 d-block mx-auto mb-4">
                                
                                 <div class="input-control">
                                    <input type="password" name="confirm_pass" placeholder="Confirm Password*" class="form-control" id="cpass" onkeyup="validateConfirmpass()">
                                    
                                       <div id="confirm-error" class="error"></div>
                                    </div>
                                    
                                </div>

                                <input type="hidden" value=<%=request.getParameter("key") %> name="key" placeholder="token">





                                <%if (request.getSession().getAttribute("msg")!=null){ %>

                                    <p>${msg}</p>
                                    <br>


                                    <%}request.getSession().removeAttribute("msg");  %>

                                        <div class="col-lg-10 d-block mx-auto mt-3">
                                          <div class="input-control">
                                          
                                            <button class="btn btn-primary w-50 d-block ms-auto" type="submit" onclick="return validateResetPass()">Reset Password</button>

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

<% }
else {%>
	
	<div class="text-center lh-2">
	<p>INVALID TOKEN</p>	
	<a href="ForgotPassword">TRY AGAIN</a>
	</div>
<%}%>



<!--  NExt is i get and check yung token s reset password tska yung expiration time para ma sure kung pwede pa syang maka pag change password 
or para maka pag proceed sa changepassword process -->


  <script src="js/external.js"></script>
</body>
</html>