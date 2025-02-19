<%-- 
    Document   : update
    Created on : Feb 20, 2025, 12:47:41 AM
    Author     : Admin
--%>

<%@page import="models.Courses"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          ResultSet rsCourseCategory = (ResultSet) request.getAttribute("rsCourseCategory");
          Courses course = (Courses) request.getAttribute("course");
          int courseId = (int) request.getAttribute("courseId");
        %>
        <div>
            <h1>Cập nhật</h1>
            <form action="updateCourse" method="post" enctype="multipart/form-data">
                <input type="hidden" name="oldImage" value="<%=course.getImage()%>" />
                <input type="hidden" name="courseId" value="<%=courseId%>" />
                <table>
                    <tr>
                        <td><label for="courseName">Tên khóa học</label></td>
                        <td>
                            <input style="width: 100%" type="text" name="courseName" id="courseName" value="<%=course.getCourseName()%>">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="description">Mô tả</label></td>
                        <td>
                            <input style="width: 100%" name="description" id="description" value="<%=course.getDescription()%>">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="numberLesson">Số buổi học</label></td>
                        <td>
                            <input style="width: 100%" name="numberLesson" id="numberLesson" value="<%=course.getNumberLesson()%>">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="categoryId">Danh mục môn học</label></td>
                        <td>
                            <select style="width: 100%" name="categoryId" id="categoryId">
                                <%while (rsCourseCategory.next()) {%>
                                <option
                                    value="<%=rsCourseCategory.getInt("id")%>"
                                    <%= (course.getId() == courseId) ? "selected" : ""%>
                                    >
                                    <%=rsCourseCategory.getString("categoryName")%>
                                </option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="image">Ảnh</label></td>
                    <img style="width: 100px; height: 100px" src="<%=request.getContextPath() + "/" + course.getImage()%>"/>
                    <td>
                        <input type="file" name="image" id="image" >
                    </td>
                    </tr>
                </table>
                <button type="submit">Lưu</button>
            </form>
        </div>
    </body>
</html>
