/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
public class CoursesCategories {

  private int id;
  private String categoryName;

  public CoursesCategories() {
  }

  public CoursesCategories(int id, String categoryName) {
    this.id = id;
    this.categoryName = categoryName;
  }

  public CoursesCategories(String categoryName) {
    this.categoryName = categoryName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

}
