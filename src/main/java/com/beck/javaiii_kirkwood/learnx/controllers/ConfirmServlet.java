package com.beck.javaiii_kirkwood.learnx.controllers;

import com.beck.javaiii_kirkwood.learnx.Models.User;
import com.beck.javaiii_kirkwood.learnx.data.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {
  private static Map<String, String> results = new HashMap<>();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Confirm Your Code");
    req.getRequestDispatcher("WEB-INF/learnx/2fa-confirm.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String code = req.getParameter("inputCode");

    results.clear();
    results.put("code", code);

    HttpSession session = req.getSession();
    String codefromSession = (String) session.getAttribute("code");
    User user = new User();
    if (!Objects.equals(code, codefromSession)){
      results.put("CodeError","That code is incorrect");
    }

    else{
      String email=(String)session.getAttribute("email");
      try {
        user = UserDAO.getUserByPrimaryKey(email);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      user.setStatus("active");
      //user.setLast_logged_in();
      user.setPrivileges("student");
      user.setLast_logged_in(Instant.now().atOffset(ZoneOffset.UTC).toInstant());
      UserDAO.update(user);
      session.removeAttribute("code");
      session.removeAttribute("email");
      user.setPassword(null);
      session.setAttribute("activeUser",user);

      session.setAttribute("flashMessageSuccess","Welcome to LearnX " + user.getEmail()+"!");
      resp.sendRedirect("learnx");
      return;



    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Confirm Your Code");
    req.getRequestDispatcher("WEB-INF/learnx/2fa-confirm.jsp").forward(req, resp);
  }
}
