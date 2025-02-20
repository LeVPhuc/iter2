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
  // lý do lấy courseId ở doGet xog lại phải truyền ngược lại cho jsp
  // để có thể set được courseId vào <input type="hidden" name="courseId" value="<%=courseId%>" /> <=> submit form có trường courseId
  // nếu không truyền ngược lại thì khi submit form ở doPost sẽ không có id để thực hiện update

  // luồng của update
  // 1. Khi click vào btn Cập nhật ở màn list => gọi đến controller updateCourse với method get
  // 2. ở hàm doGet sẽ lấy thông của Course theo id để bên jsp có thể fill thông của Course vào form
  // 3.
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // khi gọi 1 controller = 1 form có method get, bằng 1 đường dẫn
    CourseDao courseDao = new CourseDao();
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    // lấy ra course theo id
    Courses course = courseDao.getCourseByID(courseId);
    // lấy ra danh mục môn học
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
    // khi gọi 1 controller = 1 form có method post,
    CourseDao courseDao = new CourseDao();
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    String courseName = request.getParameter("courseName");
    String description = request.getParameter("description");
    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
    int numberLesson = Integer.parseInt(request.getParameter("numberLesson"));
    // xử lý upload file
    // fileUrl để cho việc lưu đường của trường image
    // mặc định ban đầu sẽ gán cho bằng request.getParameter("oldImage"); để nếu người dùng k update thì sẽ giữ lại đường dẫn cũ
    String fileUrl = request.getParameter("oldImage");
    String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIR;
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }
    Part part = request.getPart("image");
    if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
      String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
      String filePath = uploadPath + File.separator + fileName;
      part.write(filePath);
      fileUrl = UPLOAD_DIR + "/" + fileName;
    }
    // checkUpdate để check xem câu lệnh update có được thực hiện thành công không
    int checkUpdate = 0;
    Courses newCourse = new Courses(categoryId, 1, numberLesson, courseName, description, fileUrl);
    // cái courseDao.updateCourse sẽ return về int vì khi chạy câu lệnh insert/update sẽ nhận được số lượng bản ghi thực hiện thành công
    checkUpdate = courseDao.updateCourse(newCourse, courseId);
    if (checkUpdate > 0) {
      response.sendRedirect("http://localhost:8080/SpSWP/getListCourse");
    } else {
      request.setAttribute("message", "Cập nhật thất bại");
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
