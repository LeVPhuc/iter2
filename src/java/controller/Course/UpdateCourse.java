/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Course;

import dao.CourseDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.ResultSet;
import models.Courses;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCourse", urlPatterns = {"/updateCourse"})
@MultipartConfig
public class UpdateCourse extends HttpServlet {

  private static final String UPLOAD_DIR = "WebPages/uploads";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    CourseDao courseDao = new CourseDao();
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    Courses course = courseDao.getCourseByID(courseId);
    String sqlCourseCategory = "select * from CoursesCategories";
    ResultSet rsCourseCategory = courseDao.getData(sqlCourseCategory);
    request.setAttribute("rsCourseCategory", rsCourseCategory);
    request.setAttribute("course", course);
    request.setAttribute("courseId", courseId);
    request.getRequestDispatcher("/update.jsp").forward(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    CourseDao courseDao = new CourseDao();
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    String courseName = request.getParameter("courseName");
    String description = request.getParameter("description");
    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
    int numberLesson = Integer.parseInt(request.getParameter("numberLesson"));
    String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIR;
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }
    Part part = request.getPart("image");
    String fileUrl = request.getParameter("oldImage");
    if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
      String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
      String filePath = uploadPath + File.separator + fileName;
      part.write(filePath);
      fileUrl = UPLOAD_DIR + "/" + fileName;
    }
    int checkInsert = 0;
    Courses newCourse = new Courses(categoryId, 1, numberLesson, courseName, description, fileUrl);
    checkInsert = courseDao.updateCourse(newCourse, courseId);
    if (checkInsert > 0) {
      response.sendRedirect("http://localhost:8080/SpSWP/getListCourse");
    } else {
      request.setAttribute("message", "Thêm mới thất bại");
      request.getRequestDispatcher("/update.jsp").forward(request, response);
    }
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
