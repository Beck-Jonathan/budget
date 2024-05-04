package com.beck.javaiii_kirkwood.personal_project.controllers;


/******************
 Create the Servlet  For Viewing all of the  Team table
 Created By Jonathan Beck 5/4/2024
 ***************/

import com.beck.javaiii_kirkwood.personal_project.data.TeamDAO;
import com.beck.javaiii_kirkwood.personal_project.data.UserDAO;
import com.beck.javaiii_kirkwood.personal_project.models.Team;
import com.beck.javaiii_kirkwood.personal_project.models.User;
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
@WebServlet("/My-Teams")
public class MyTeamsServlet extends HttpServlet {@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
  int PRIVILEGE_NEEDED = 0;
  HttpSession session = req.getSession();
  User user = (User)session.getAttribute("User");
  if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
  }

  session.setAttribute("currentPage",req.getRequestURL());
  List<Team> teams = null;

  teams = UserDAO.selectTeamByUser(user);

  req.setAttribute("Teams", teams);
  req.setAttribute("pageTitle", "My Teams");
  req.getRequestDispatcher("WEB-INF/personal-project/MyTeams.jsp").forward(req,resp);

}
}
