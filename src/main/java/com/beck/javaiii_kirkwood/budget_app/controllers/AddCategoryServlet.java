package com.beck.javaiii_kirkwood.budget_app.controllers;


import com.beck.javaiii_kirkwood.budget_app.data.CategoryDAO;
import com.beck.javaiii_kirkwood.budget_app.models.Category;
import com.beck.javaiii_kirkwood.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Category table
 Created By Jonathan Beck 7/30/2024
 ***************/

@WebServlet("/addTransactionCategory")
public class AddCategoryServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    //if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
     // resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      //return;
    //}

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Category");
    req.getRequestDispatcher("WEB-INF/Budget_App/add_category.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    //if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
     // resp.sendError(HttpServletResponse.SC_FORBIDDEN);
     // return;
    //}

    String _Category_ID = req.getParameter("inputcategoryCategory_ID");
    _Category_ID=_Category_ID.trim();
    Map<String, String> results = new HashMap<>();
    results.put("Category_ID",_Category_ID);
    Category category = new Category();
    int errors =0;
    try {
      category.setCategory_ID(_Category_ID);
    } catch(IllegalArgumentException e) {results.put("categoryCategory_IDerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=CategoryDAO.add(category);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Category Added");
        resp.sendRedirect("all-Categorys");
        return;
      } else {
        results.put("dbStatus","Category Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Category ");
    req.getRequestDispatcher("WEB-INF/Budget_App/add_category.jsp").forward(req, resp);

  }
}

