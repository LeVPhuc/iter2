<%-- 
    Document   : list
    Created on : Feb 19, 2025, 5:22:40 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
        <style>
            .product-item {
                box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
                padding: 12px;
            }
            img {
                width: 100%;
                height: 300px;
            }
        </style>
    </head>
    <body>
        <%
          ResultSet rsCourse = (ResultSet) request.getAttribute("rsCourse");
          ResultSet rsCourseCategory = (ResultSet) request.getAttribute("rsCourseCategory");
//          int totalPage = (int) request.getAttribute("totalPage");
        %>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <a href="insertCourse">Thêm mới</a>
                </div>
                <div class="side_bar col-2">
                    <div class="menu">
                        <p>Danh mục khóa học</p>
                        <ul>
                            <%while (rsCourseCategory.next()) {%>
                            <li class="category_item"><a href="ClientControlller?service=searchCname&cname=<%=rsCourseCategory.getInt("id")%>"><%=rsCourseCategory.getString("categoryName")%></a></li>
                                <%}%>
                        </ul>
                    </div>
                </div>
                <div class="list_product row col-10">
                    <%while (rsCourse.next()) {%>
                    <div class="col-md-3">
                        <div class="product-item">
                            <div class="product-item-content">
                                <img src="<%=request.getContextPath() + "/" + rsCourse.getString("image")%>"/>
                                <p><%=request.getContextPath()%></p>
                                <p class="product-item-name"><%=rsCourse.getString("courseName")%></p>
                                <p class="product-item-name">Số buổi học: <%=rsCourse.getString("numberLesson")%></p>
                                <a href="updateCourse?courseId=<%=rsCourse.getInt("id")%>">Cập nhật</a>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>
