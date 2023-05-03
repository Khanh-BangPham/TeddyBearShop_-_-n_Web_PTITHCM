<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- top Header -->
<div id="top-header">
    <div class="container">
        <div class="pull-left">
            <span>Welcome to GROUP 13's SHOP</span>
        </div>
    </div>
</div>
<div id="header">
    <div class="container">
        <div class="pull-left">
            <div class="header-logo">
        		<a class="logo" href="redirect.jsp">
        			<img src="resource/img/logo.png" alt="">
        		</a>
        	</div>

        </div>
        <div class="pull-right">
            <form id="search-form-header" action="searchsanpham.htm" method="get">
                <input  class="input search-input" type="text" name="tensp" placeholder="Nhập từ khóa">
                <i class="fa fa-search"></i>
            </form>
            <ul class="header-btns">
                <!-- Account -->
                <li class="header-account dropdown default-dropdown">
                    <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
                        <div class="header-btns-icon">
                            <i class="fa fa-user"></i>
                        </div>
                        <% if (session.getAttribute("ten") != null) {%>
                        <strong class="text-uppercase"><%=session.getAttribute("ten")%> <i class="fa fa-caret-down"></i></strong>
                            <% } else { %>
                        <strong class="text-uppercase">Tài khoản</strong>
                            <% } %>
                    </div>

                    <% if (session.getAttribute("ten") != null) {%>
                    <a href="dangxuat.htm" class="text-uppercase">Đăng xuất</a>
                    <% } else { %>
                    <a href="loginuser.htm" class="text-uppercase"><h6>Đăng nhập</h6></a>
                    <% }%>

                    <% if (session.getAttribute("ten") != null) {%>
                    <ul class="custom-menu">
                        <li><a href = "thongtinkhachhang.htm?maKh=<%=session.getAttribute("ma")%>"><i class="fa fa-user-o"></i> Tài khoản của tôi</a></li>
                        <li><a href = "donhangkhachhang.htm?maKh=<%=session.getAttribute("ma")%>"><i class="fa fa-check"></i> Đơn hàng của tôi</a></li>
                        <li><a href = "danhsachuathich.htm?maKh=<%=session.getAttribute("ma")%>"><i class="fa fa-heart-o"></i> Yêu thích của bạn</a></li>
                    </ul>
                    <% } else { %>
                    <strong class="text-uppercase"></strong>
                    <% }%>

                </li>
                <!-- /Account -->

                <!-- Cart -->

                <jsp:include page="cart.jsp" flush="true"></jsp:include>

                <!-- /Cart -->

                <!-- Mobile nav toggle-->
                <li class="nav-toggle">
                    <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                </li>
                <!-- / Mobile nav toggle -->
            </ul>
        </div>
    </div>
    <!-- container -->
</div>
<!-- header -->
<!-- scrolled header -->
<div id="scrolled-header">
    <div class="container">
            <div class="header-logo">
        		<a class="logo" href="redirect.jsp">
        			<img src="resource/img/logo.png" alt="">
        		</a>
        	</div>
	        <div id="responsive-nav">
	            <!-- category nav -->
	            <div class="category-nav show-on-click">
	                <span class="category-header">Danh Mục Sản Phẩm <i class="fa fa-list"></i></span>
	                <ul class="category-list">
	                    <c:forEach var="item" items="${nhomsanpham}">
	                        <li class="dropdown side-dropdown">
	                            <a onclick="location.href = 'searchbynhomsp.htm?maNhomSp=${item.maNhomSp}'" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">${item.tenNhomSp}<i class="fa fa-angle-right"></i></a>
	                        </li>
	                    </c:forEach>
	                </ul>
	            </div>
	            <!-- /category nav -->
	
	            <!-- menu nav -->
	            <div class="menu-nav">
	                <ul class="menu-list">
	                    <li><a href="">Thông tin</a></li>
	                    <li><a href="">Sản phẩm được ưa thích</a></li>
	                </ul>
	            </div>  
	            <!-- menu nav -->
	        </div>
            <form id="search-form-header" action="searchsanpham.htm" method="get">
                <input  class="input search-input" type="text" name="tensp" placeholder="Nhập từ khóa">
                <i class="fa fa-search"></i>
            </form>
            <ul class="header-btns">
                <!-- Account -->
                <li class="header-account dropdown default-dropdown">
                    <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
                        <div class="header-btns-icon">
                            <i class="fa fa-user"></i>
                        </div>
                        <% if (session.getAttribute("ten") != null) {%>
                        <strong class="text-uppercase"><%=session.getAttribute("ten")%> <i class="fa fa-caret-down"></i></strong>
                            <% } else { %>
                        <strong class="text-uppercase">Tài khoản</strong>
                            <% } %>
                    </div>

                    <% if (session.getAttribute("ten") != null) {%>
                    <a href="dangxuat.htm" class="text-uppercase">Đăng xuất</a>
                    <% } else { %>
                    <a href="loginuser.htm" class="text-uppercase"><h6>Đăng nhập</h6></a>
                    <% }%>

                    <% if (session.getAttribute("ten") != null) {%>
                    <ul class="custom-menu">
                        <li><a href = "thongtinkhachhang.htm?maKh=<%=session.getAttribute("ma")%>"><i class="fa fa-user-o"></i> Tài khoản của tôi</a></li>
                        <li><a href = "donhangkhachhang.htm?maKh=<%=session.getAttribute("ma")%>"><i class="fa fa-check"></i> Đơn hàng của tôi</a></li>
                        <li><a href = "danhsachuathich.htm?maKh=<%=session.getAttribute("ma")%>"><i class="fa fa-heart-o"></i> Yêu thích của bạn</a></li>
                    </ul>
                    <% } else { %>
                    <strong class="text-uppercase"></strong>
                    <% }%>

                </li>
                <!-- /Account -->

                <!-- Cart -->

                <jsp:include page="cart.jsp" flush="true"></jsp:include>

                <!-- /Cart -->

                <!-- Mobile nav toggle-->
                <!-- <li class="nav-toggle">
                    <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                </li>-->
                <!-- / Mobile nav toggle -->
            </ul>

    </div>
    <!-- container -->
</div>