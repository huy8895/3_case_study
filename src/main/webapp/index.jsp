<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huy8895
  Date: 9/1/20
  Time: 8:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
    <script src="bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/bootstrap-grid.min.css">
    <link rel="stylesheet" href="bootstrap/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Watch Store</title>
    <style>
        .shop-item:hover{
            border-color: #66afe9;
            box-shadow: 0 0 8px rgba(68, 62, 157, 0.6);
        }
        select {
            -webkit-appearance: none;
            -moz-appearance: none;
            background: transparent;
            background-image: url("data:image/svg+xml;utf8,<svg fill='black' height='24' viewBox='0 0 24 24' width='24' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/><path d='M0 0h24v24H0z' fill='none'/></svg>");
            background-repeat: no-repeat;
            background-position-x: 100%;
            /*background-position-y: px;*/
            border: thin solid black;
            border-radius: 2px;
            margin-right: 2rem;
            padding: 2px;
            padding-right: 2px;
        }
    </style>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark pink scrolling-navbar bg-dark">
    <a class="navbar-brand" href="/products?action="><strong>Watch Store</strong></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="../index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cart?action=cart">Cart</a>
            </li>

        </ul>
        <div class="align-content-sm-end">
            <c:if test="${customer==null}">
                <c:out value= "
            <form action=\"login\" method=\"post\" class=\"form-inline\">
                <input type=\"text\" class=\"form-control-sm\" placeholder=\"Username..\" name=\"username\"><span>&nbsp;</span>
                <input type=\"password\" class=\"form-control-sm\" placeholder=\"Password..\" name=\"password\"><span>&nbsp;</span>
                <button class=\"btn btn-light btn-sm\" type=\"submit\" name=\"login\">Login</button><span>&nbsp;</span>
                <a class=\"btn btn-light btn-sm\" href=\"customers?action=create\">Sign Up</a>
                <input type=\"hidden\" name=\"action\" value=\"login\">
            </form>" escapeXml="false"/>
            </c:if>
            <c:if test="${customer!=null}">
                <c:out value= "
               <span type=\"text\" class=\"text-white\" name=\"username\">${customer.getUserName()}</span>
               <div><a href=\"/products\" class=\"text-white\">Sign Out</a></div>" escapeXml="false"/>
            </c:if>
        </div>
    </div>
</nav>

</header>
<!------------------------------------------------------------------HEADER----------------------------------------------->

<!--Main Navigation-->

<!--Main Layout-->

<main class="bd-masthead container col-12" id="content" role="main" style="padding-top:56px">
    <div id="carouselExampleControls" class="container carousel slide col-7 w-100" data-ride="carousel">
        <div class="carousel-inner con">
            <div class="carousel-item active">
                <img src="https://cdn2.jomashop.com/media//wysiwyg/sales-event/2020/big_banner_TUDOR_black_bay.jpg" class="d-block col-12" alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://cdn2.jomashop.com/media//wysiwyg/sales-event/2020/big_banner_SEIKO_prospex.jpg" class="d-block col-12" alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://cdn2.jomashop.com/media//wysiwyg/sales-event/2020/big_banner_DIOR_aviator.jpg" class="d-block col-12" alt="...">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <hr>
    <div class="container col-12">
        <div class="row col-sm-12 col-xs-12">
            <!-- ]Search left size -->
            <div class="col-12 col-sm-2 col-md-2 col-lg-2">
                <form action="/products" method="get">
                    <input type="hidden" name="cusNumber" value="${customer.getCusNumber()}">
                    <div class="form-group">
                        <input type="text" class="form-control border-dark" id="productName" name="SearchBox_productName" aria-describedby="emailHelp" placeholder="Enter Product Name">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control border-dark" id="maxPrice" name="SearchBox_maxPrice" aria-describedby="emailHelp" placeholder="Max Price" value="">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control border-dark" id="minPrice" name="SearchBox_minPrice" aria-describedby="emailHelp" placeholder="Min Price" value="0">
                    </div>
                    <div class="dropdown">
                        <select name="SearchBox_productBrand" style="border-radius: 3px; border-style: solid; border-color: black; border-width: thin;">
                            <option><button class="btn btn-white dropdown-toggle border-dark" type="button" id="dropdownMenuButton"  name="SearchBox_productBrand" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Brand
                            </button></option>
                            <div class="dropdown-menu " aria-labelledby="dropdownMenuButton" >
                                <option><a class="dropdown-item">Casio</a></option>
                                <option> <a class="dropdown-item">Citizen</a></option>
                                <option> <a class="dropdown-item">Hamilton</a></option>
                                <option> <a class="dropdown-item">Invicta</a></option>
                                <option> <a class="dropdown-item">Longines</a></option>
                                <option> <a class="dropdown-item">Omega</a></option>
                                <option> <a class="dropdown-item">Orient</a></option>
                                <option> <a class="dropdown-item">Rado</a></option>
                                <option> <a class="dropdown-item">Seiko</a></option>
                                <option> <a class="dropdown-item">Tag Heuer</a></option>
                                <option> <a class="dropdown-item">Tissot</a></option>
                            </div>
                        </select>
                    </div>
                    <br>
                    <div class="dropdown">
                        <select name="SearchBox_productLine" style="border-radius: 3px; border-style: solid; border-color: black; border-width: thin";>
                        <option><button class="btn btn-white dropdown-toggle border-dark" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Gender
                        </button></option>
                        <div class="dropdown-menu " aria-labelledby="dropdownMenuButton" >
                            <option><a class="dropdown-item">Men's Watches</a></option>
                            <option> <a class="dropdown-item">Women's Watches</a></option>
                        </div>
                        </select>
                    </div>
                    <br>
                    <input type="hidden" name="action" value="search">
                    <button type="submit" class="btn btn-primary">Search</button>

                </form>
                <br><hr>
            </div>
            <!-- ]Row -->
            <div class="col-12 container bg-white col-sm-8 col-md-8 col-lg-8">
                <c:set var = "count" scope = "session" value = "${5}"/>
                <c:forEach items="${productList}" var="product">
                    <c:if test="${count==0||count==5}">
                        <c:out value= "<div class=\"row\">" escapeXml="false"/>
                    </c:if>
                    <c:out value= "
                            <div class=\"col-12 bg-white col-sm-12 col-lg-3 col-md-3 container shop-item\">
                                <img src=\"${product.getProductImage()}\" class=\"float-left col-lg-12 col-md-12\">
                                <p>${product.getProductBrand()} ${product.getProductName()}</p>
                                <p>${product.getProductPrice()}</p>
                                <i class=\"fa fa-shopping-cart\"></i>&nbsp;<a href=\"#\" id=\"${product.getProductCode()}\">Add To Cart</a>
                                <br>
                            </div>
                            " escapeXml="false"/>
                    <c:if test="${count!=5}">
                        <c:set var="count" value="${count+1}"/>
                    </c:if>
                    <c:if test="${count==5}">
                        <c:set var="count" value="${1}"/>
                    </c:if>

                    <c:if test="${count==3}">
                        <c:out value= "</div>" escapeXml="false"/>
                        <c:out value= "<br>" escapeXml="false"/>
                        <c:set var="count" value="${count=0}"/>
                    </c:if>
                </c:forEach>
            </div>
            <!-- Ads -->
            <br>
        </div>
        <div class="col-2 d-none d-sm-block d-md-block">
            One of three columns
        </div>
        <!-- Ads -->
    </div>
    </div>
</main>
<!--Main Layout-->
<footer class="footer bg-dark">
    <div class="">
        <span class="text-muted">Place sticky footer content here.</span>
    </div>
</footer>

<script src="" async defer></script>
</body>
</html>
