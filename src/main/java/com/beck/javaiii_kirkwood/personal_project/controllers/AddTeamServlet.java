package com.beck.javaiii_kirkwood.personal_project.controllers;

import com.beck.javaiii_kirkwood.personal_project.data.TeamDAO;
import com.beck.javaiii_kirkwood.personal_project.models.Team;
import com.beck.javaiii_kirkwood.personal_project.data.LeagueDAO;
import com.beck.javaiii_kirkwood.personal_project.models.League;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/******************
 Create the Servlet  For adding to The  Team table
 Created By Jonathan Beck3/3/2024

 ***************/

@WebServlet("/addTeam")
public class AddTeamServlet extends HttpServlet{
  static List<League> allLeagues = LeagueDAO.getAllLeague();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Add Team");
    allLeagues = LeagueDAO.getAllLeague();
    req.setAttribute("Leagues", allLeagues);
    req.getRequestDispatcher("WEB-INF/personal-project/AddTeam.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("Leagues", allLeagues);
    String _League_ID = req.getParameter("inputteamLeague_ID");
    String _Team_Name = req.getParameter("inputteamTeam_Name");
    String _Team_Primary_Color = req.getParameter("inputteamTeam_Primary_Color");
    String _Team_City = req.getParameter("inputteamTeam_City");
    String _Team_State = req.getParameter("inputteamTeam_State");
    String _Logo = req.getParameter("inputteamLogo");
    Map<String, String> results = new HashMap<>();
    results.put("League_ID",_League_ID);
    results.put("Team_Name",_Team_Name);
    results.put("Team_Primary_Color",_Team_Primary_Color);
    results.put("Team_City",_Team_City);
    results.put("Team_State",_Team_State);
    results.put("Logo",_Logo);
    Team team = new Team();
    int errors =0;
    try {
      team.setLeague_ID(Integer.valueOf(_League_ID));
    } catch(IllegalArgumentException e) {results.put("teamLeague_IDerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_Name(_Team_Name);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Nameerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_Primary_Color(_Team_Primary_Color);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Primary_Colorerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_City(_Team_City);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Cityerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_State(_Team_State);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Stateerror", e.getMessage());
      errors++;
    }
    try {
      team.setLogo(_Logo);
    } catch(IllegalArgumentException e) {results.put("teamLogoerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=TeamDAO.add(team);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Team Added");
      } else {
        results.put("dbStatus","Team Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Team ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddTeam.jsp").forward(req, resp);

  }
}


