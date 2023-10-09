<%--
  Created by IntelliJ IDEA.
  User: samandykov
  Date: 08.10.2023
  Time: 00:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <%@ include file="/import/front-bootstrap.jsp" %>
</head>
<body>
<section class="vh-100" style="background-color: #9A616D;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form action="/auth/registration" method="post">

                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;"> Registration Form</h5>

                                    <div class="form-outline mb-4">
                                        <input type="email" id="user_email" class="form-control form-control-lg" name="email" required/>
                                        <label class="form-label" for="user_email">Email address</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" id="user_password" class="form-control form-control-lg" name="password" required/>
                                        <label class="form-label" for="user_password" >Password</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="text" id="user_fullName" class="form-control form-control-lg" name="fullName" required/>
                                        <label class="form-label" for="user_fullName" >Full Name</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="tel" id="user_phone_number" class="form-control form-control-lg" name="phoneNumber" placeholder="8**********" required/>
                                        <label class="form-label" for="user_phone_number">Phone Number</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit">Submit Form</button>
                                    </div>

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
