/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
public class Courses {

  private int id, categoryId, ownerId, numberLesson;
  private String courseName, description, image;

  public Courses() {
  }

  public Courses(int categoryId, int ownerId, int numberLesson, String courseName, String description, String image) {
    this.categoryId = categoryId;
    this.ownerId = ownerId;
    this.numberLesson = numberLesson;
    this.courseName = courseName;
    this.description = description;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }

  public int getNumberLesson() {
    return numberLesson;
  }

  public void setNumberLesson(int numberLesson) {
    this.numberLesson = numberLesson;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
