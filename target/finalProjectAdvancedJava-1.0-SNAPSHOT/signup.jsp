<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11.11.2020
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <jsp:include page="blocks/links.jsp"/>
</head>
<body>
<div class="container">
    <c:if test="${error!=null}">
        <h1 class="h3 mb-3 font-weight-normal text-danger text-center">${error}</h1>
    </c:if>
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="https://www.alem-edu.kz/wp-content/uploads/2020/05/unnamed-file.png" alt="" width="190" height="72">
        <h2>Sign up form</h2>
<%--        <p class="lead">Below is an example form built entirely with Bootstrap's form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>--%>
    </div>

    <div class="row">
<%--        <div class="col-md-4 order-md-2 mb-4">--%>
<%--            <h4 class="d-flex justify-content-between align-items-center mb-3">--%>
<%--                <span class="text-muted">Your cart</span>--%>
<%--                <span class="badge badge-secondary badge-pill">3</span>--%>
<%--            </h4>--%>
<%--            <ul class="list-group mb-3">--%>
<%--                <li class="list-group-item d-flex justify-content-between lh-condensed">--%>
<%--                    <div>--%>
<%--                        <h6 class="my-0">Product name</h6>--%>
<%--                        <small class="text-muted">Brief description</small>--%>
<%--                    </div>--%>
<%--                    <span class="text-muted">$12</span>--%>
<%--                </li>--%>
<%--                <li class="list-group-item d-flex justify-content-between lh-condensed">--%>
<%--                    <div>--%>
<%--                        <h6 class="my-0">Second product</h6>--%>
<%--                        <small class="text-muted">Brief description</small>--%>
<%--                    </div>--%>
<%--                    <span class="text-muted">$8</span>--%>
<%--                </li>--%>
<%--                <li class="list-group-item d-flex justify-content-between lh-condensed">--%>
<%--                    <div>--%>
<%--                        <h6 class="my-0">Third item</h6>--%>
<%--                        <small class="text-muted">Brief description</small>--%>
<%--                    </div>--%>
<%--                    <span class="text-muted">$5</span>--%>
<%--                </li>--%>
<%--                <li class="list-group-item d-flex justify-content-between bg-light">--%>
<%--                    <div class="text-success">--%>
<%--                        <h6 class="my-0">Promo code</h6>--%>
<%--                        <small>EXAMPLECODE</small>--%>
<%--                    </div>--%>
<%--                    <span class="text-success">-$5</span>--%>
<%--                </li>--%>
<%--                <li class="list-group-item d-flex justify-content-between">--%>
<%--                    <span>Total (USD)</span>--%>
<%--                    <strong>$20</strong>--%>
<%--                </li>--%>
<%--            </ul>--%>

<%--            <form class="card p-2">--%>
<%--                <div class="input-group">--%>
<%--                    <input type="text" class="form-control" placeholder="Promo code">--%>
<%--                    <div class="input-group-append">--%>
<%--                        <button type="submit" class="btn btn-secondary">Redeem</button>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
        <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Fill the blanks</h4>
            <form class="needs-validation" novalidate="" action="<%=request.getContextPath()+"/signup"%>" method="post">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="" required="">
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="" required="">
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="username">Username</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">@</span>
                        </div>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username" required="">
                        <div class="invalid-feedback" style="width: 100%;">
                            Your username is required.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email">Password</label>
                    <input type="password" class="form-control" name="txtPassword" id="email" placeholder="abc123">
                    <div class="invalid-feedback">
                        Please enter a valid email address for shipping updates.
                    </div>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Sign up</button>
            </form>
        </div>
    </div>
    <p class="text-center">or</p>
    <a class="btn btn-lg btn-warning btn-block" href="<%=request.getContextPath()+"/main"%>">Sign in</a>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">© 2017-2018 Company Name</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Privacy</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
    </footer>
</div>
</body>
</html>
