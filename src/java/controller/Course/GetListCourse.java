/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Course;

import dao.CourseDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetListCourse", urlPatterns = {"/getListCourse"})
public class GetListCourse extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  private final int PAGE_SIZE = 5;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    CourseDao courseDao = new CourseDao();
    int currentPage = 1;
//    if (request.getParameter("currentPage") != null) {
//      currentPage = Integer.parseInt(request.getParameter("currentPage"));
//    }
    String textSearch = request.getParameter("textSearch");
    int coursecategory = 0;
    if (request.getParameter("coursecategory") != null) {
      coursecategory = Integer.parseInt(request.getParameter("coursecategory"));
    }
    int totalCourse = courseDao.getTotalRecord("select * from Courses");
    int totalPage;
    if (totalCourse % PAGE_SIZE == 0) {
      totalPage = totalCourse / PAGE_SIZE;
    } else {
      totalPage = totalCourse / PAGE_SIZE + 1;
    }
    String sqlCourseCategory = "select * from CoursesCategories";
    ResultSet rsCourseCategory = courseDao.getData(sqlCourseCategory);
    String sqlCourse = "select * from Courses order by id offset " + ((currentPage - 1) * PAGE_SIZE) + " rows fetch next " + PAGE_SIZE + " rows only";
    if (textSearch != null && coursecategory != 0) {
      sqlCourse = "select * from Courses "
              + "where courseName like '%" + textSearch + "%' and categoryId = " + coursecategory + " "
              + "order by id offset " + ((currentPage - 1) * PAGE_SIZE) + " rows fetch next" + PAGE_SIZE + " rows only";
    }
    if (textSearch != null) {
      sqlCourse = "select * from Courses "
              + "where courseName like '%" + textSearch + "%' "
              + "order by id offset " + ((currentPage - 1) * PAGE_SIZE) + " rows fetch next" + PAGE_SIZE + " rows only";
    }
    if (coursecategory != 0) {
      sqlCourse = "select * from Courses "
              + "where categoryId = " + coursecategory + " "
              + "order by id offset " + ((currentPage - 1) * PAGE_SIZE) + " rows fetch next" + PAGE_SIZE + " rows only";
    }
    ResultSet rsCourse = courseDao.getData(sqlCourse);
    request.setAttribute("totalPage", totalPage);
    request.setAttribute("rsCourseCategory", rsCourseCategory);
    request.setAttribute("rsCourse", rsCourse);
    request.getRequestDispatcher("list.jsp").forward(request, response);
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
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
    processRequest(request, response);
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
