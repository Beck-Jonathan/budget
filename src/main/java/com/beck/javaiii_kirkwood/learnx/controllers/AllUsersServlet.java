package com.beck.javaiii_kirkwood.learnx.controllers;

import com.beck.javaiii_kirkwood.learnx.data.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.beck.javaiii_kirkwood.learnx.Models.User;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/all-users")
public class AllUsersServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User userFromSession = (User)session.getAttribute("activeUser");
//        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
    List<User> users = null;
    try {
      users = UserDAO.getAllUsers();
    } catch (SQLException e) {
      resp.sendRedirect("home");
      return;
    }
    req.setAttribute("users", users);
    req.setAttribute("pageTitle", "All Users");
    req.getRequestDispatcher("WEB-INF/learnx/all-users.jsp").forward(req,resp);

  }
}
