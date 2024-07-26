package com.beck.javaiii_kirkwood.budget_app.controllers;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/signout_budget")
public class UserSignOutServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Budget With Us");
    req.getRequestDispatcher("WEB-INF/Budget_App/budget_home.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();


    String currentPage =  session.getAttribute("currentPage").toString();
    session.invalidate();
    req.setAttribute("pageTitle", "Budget With Us");
    req.getRequestDispatcher("WEB-INF/Budget_App/budget_home.jsp").forward(req, resp);
  }
}
