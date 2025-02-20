create database SpSWP

create table Users (
  id int not null primary key IDENTITY(1,1),
  fullName nvarchar(255) not null
)

create table CoursesCategories (
id int not null primary key IDENTITY(1,1),
  categoryName nvarchar(255) not null
)


drop table Courses

create table Courses (
id int not null primary key IDENTITY(1,1),
  courseName nvarchar(255) not null,
  categoryId int FOREIGN KEY REFERENCES CoursesCategories(id) not null,
  ownerId int FOREIGN KEY REFERENCES Users(id) not null,
  description nvarchar(255) not null,
  image nvarchar(255) not null,
  numberLesson int not null
)

select * from Courses
update Courses set courseName = 'Course 2', categoryId = 1, ownerId = 1, numberLesson= 50, description= 'abcdgfnsfn', image = 'WebPages/uploads/IMG_20231118_134904.jpg' 
where id = 13

update Courses set courseName = 'Course 12'
where id = 12
delete Courses
