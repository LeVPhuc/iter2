<%-- 
    Document   : insert
    Created on : Feb 19, 2025, 9:08:03 PM
    Author     : Admin
--%>

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
        %>
        <div>
            <h1>Thêm mới</h1>
            <form action="insertCourse" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td><label for="courseName">Tên khóa học</label></td>
                        <td><input style="width: 100%" type="text" name="courseName" id="courseName"></td>
                    </tr>
                    <tr>
                        <td><label for="description">Mô tả</label></td>
                        <td><input style="width: 100%" name="description" id="description"></td>
                    </tr>
                    <tr>
                        <td><label for="numberLesson">Số buổi học</label></td>
                        <td><input style="width: 100%" name="numberLesson" id="numberLesson"></td>
                    </tr>
                    <tr>
                        <td><label for="categoryId">Danh mục môn học</label></td>
                        <td>
                            <select style="width: 100%" name="categoryId" id="categoryId">
                                <%while (rsCourseCategory.next()) {%>
                                <option value="<%=rsCourseCategory.getInt("id")%>"><%=rsCourseCategory.getInt("id")%>-<%=rsCourseCategory.getString("categoryName")%></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="image">Ảnh</label></td>
                        <td><input type="file" name="image" id="image"></td>
                    </tr>
                </table>
                <button type="submit">Lưu</button>
            </form>
        </div>
    </body>
</html>
