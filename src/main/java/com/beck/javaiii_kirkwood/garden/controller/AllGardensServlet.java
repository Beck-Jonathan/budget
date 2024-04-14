package com.beck.javaiii_kirkwood.garden.controller;

/******************
 Create the Servlet  For Viewing all of the  Garden table
 Created By Jonathan Beck 4/13/2024
 ***************/

import com.beck.javaiii_kirkwood.garden.data.GardenDAO;
import com.beck.javaiii_kirkwood.garden.model.Garden;
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
@WebServlet("/all-Gardens")
public class AllGardensServlet extends HttpServlet {@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
  int PRIVILEGE_NEEDED = 0;
  HttpSession session = req.getSession();
  User user = (User)session.getAttribute("User");


  session.setAttribute("currentPage",req.getRequestURL());
  List<Garden> gardens = null;

  gardens =GardenDAO.getAllGarden();

  req.setAttribute("Gardens", gardens);
  req.setAttribute("pageTitle", "All Gardens");
  req.getRequestDispatcher("WEB-INF/garden/Garden_All.jsp").forward(req,resp);

}
}

