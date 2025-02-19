/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBContext;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Courses;

/**
 *
 * @author Admin
 */
public class CourseDao extends DBContext {

  public int insertCourse(Courses course) {
    int n = 0;
    String sql = "Insert into Courses(courseName, categoryId, ownerId, numberLesson, description, image)"
            + "values (?, ?, ?, ?, ?, ?)";
    try {
      PreparedStatement pre = connect.prepareStatement(sql);
      pre.setString(1, course.getCourseName());
      pre.setInt(2, course.getCategoryId());
      pre.setInt(3, course.getOwnerId());
      pre.setInt(4, course.getNumberLesson());
      pre.setString(5, course.getDescription());
      pre.setString(6, course.getImage());
      //run
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return n;
  }

  public Courses getCourseByID(int courseId) {
    String sql = "select * from Courses where id = ?";
    try {
      PreparedStatement pre = connect.prepareStatement(sql);
      pre.setInt(1, courseId);
      ResultSet rs = pre.executeQuery();
      //run
      if (rs.next()) {
        Courses course = new Courses(rs.getInt("categoryId"), rs.getInt("ownerId"), rs.getInt("numberLesson"), rs.getString("courseName"), rs.getString("description"), rs.getString("image"));
        return course;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public int updateCourse(Courses course, int courseId) {
    int n = 0;
    String sql = "update Courses set courseName = ?, categoryId = ?, ownerId = ?, numberLesson = ?, description = ?, image = ? where id = ?";
    try {
      PreparedStatement pre = connect.prepareStatement(sql);
      pre.setString(1, course.getCourseName());
      pre.setInt(2, course.getCategoryId());
      pre.setInt(3, course.getOwnerId());
      pre.setInt(4, course.getNumberLesson());
      pre.setString(5, course.getDescription());
      pre.setString(6, course.getImage());
      pre.setInt(7, courseId);
      //run
      n = pre.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return n;
  }
}
