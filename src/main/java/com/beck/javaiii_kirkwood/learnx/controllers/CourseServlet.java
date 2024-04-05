package com.beck.javaiii_kirkwood.learnx.controllers;

import com.beck.javaiii_kirkwood.learnx.Models.Course;
import com.beck.javaiii_kirkwood.learnx.Models.CourseCategory;
import com.beck.javaiii_kirkwood.learnx.data.CourseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String [] categoryTry = req.getParameterValues("category");
    String skillTry = req.getParameter("skill-level");
    int limitTry = 0;
    int offsetTry= 0;
    try{
      limitTry = Integer.parseInt(req.getParameter("limit"));
    }
    catch (Exception e){
      limitTry = 25;
    }
    try{
      offsetTry = Integer.parseInt(req.getParameter("offset"));
    }
    catch (Exception e){
      offsetTry = 0;
    }


    String categoryFilter = "";
    if (categoryTry!=null && categoryTry.length>0){
      categoryFilter=String.join(",",categoryTry);
    }
    String skillFilter="";
    if (skillTry!=null) {
      skillFilter = skillTry;
    }
    // To do: For pagination
    int limit = limitTry;
    int offset = offsetTry;
    List<Course> courses = CourseDAO.get(limit, offset, categoryFilter, skillFilter);
    List<CourseCategory> categories = CourseDAO.getAllCategories();
    req.setAttribute("courses", courses);
    req.setAttribute("categories", categories);
    req.setAttribute("pageTitle", "Courses");
    req.setAttribute("categoryFilter",categoryFilter);
    req.setAttribute("skillFilter",skillFilter);
    req.getRequestDispatcher("WEB-INF/learnx/all-courses.jsp").forward(req, resp);
  }
}