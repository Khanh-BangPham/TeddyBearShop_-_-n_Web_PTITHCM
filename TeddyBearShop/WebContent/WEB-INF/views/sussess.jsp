<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>E-SHOP HTML Template</title>
        <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="resource/css/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="resource/css/slick.css" />
        <link type="text/css" rel="stylesheet" href="resource/css/slick-theme.css" />
        <link type="text/css" rel="stylesheet" href="resource/css/nouislider.min.css" />
        <link rel="stylesheet" href="resource/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="resource/css/style.css" />
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp" flush="true"></jsp:include>
            </header>
            <jsp:include page="menu.jsp" flush="true"></jsp:include>
                <!-- menu nav -->
            </div>
        </div>
        <!-- /container -->
    </div>
    <!-- /NAVIGATION -->
    <!-- section -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!--  Product Details -->
                <div class="product product-details clearfix">
                    <div class="col-md-12 text-center">
                        <h2 class="color" >${msgsussess}</h2>
                    </div>
                    </br>
                    <div class="col-md-12 text-center">
                        <a href="redirect.jsp" class="color1" >Quay lại trang chủ</a>
                    </div>
                </div>
                <!-- /Product Details -->
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>

    <jsp:include page="footer.jsp" flush="true"></jsp:include>
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script src="resource/js/slick.min.js"></script>
    <script src="resource/js/nouislider.min.js"></script>
    <script src="resource/js/jquery.zoom.min.js"></script>
    <script src="resource/js/main.js"></script>

</body>

</html>

