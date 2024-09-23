package com.beck.javaiii_kirkwood.budget_app.controllers; /******************
 Create the Servlet  For Viewing all of the  Transaction table
 Created By Jonathan Beck 7/22/2024
 ***************/

import com.beck.javaiii_kirkwood.budget_app.data.TransactionDAO;
import com.beck.javaiii_kirkwood.budget_app.data.CategoryDAO;
import com.beck.javaiii_kirkwood.budget_app.iData.iCategoryDAO;
import com.beck.javaiii_kirkwood.budget_app.models.Category;
import com.beck.javaiii_kirkwood.budget_app.models.Transaction;
import com.beck.javaiii_kirkwood.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-Transactions")
public class AllTransactionsServlet extends HttpServlet {
  public static iCategoryDAO categoryDAO;

  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    if (categoryDAO==null){
      categoryDAO = new CategoryDAO();
    }
//To restrict this page based on privilege level


  int PRIVILEGE_NEEDED = 0;
  HttpSession session = req.getSession();
  User user = (User)session.getAttribute("User_B");
  //if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
   // resp.sendError(HttpServletResponse.SC_FORBIDDEN);
   // return;
 // }
   HashMap<String,String> parameters = new HashMap<>();
    Integer year = 0;
    try {
      year= Integer.parseInt(req.getParameter("year"));
    } catch (Exception e){
      year=0;
    }
    parameters.put("year",year.toString());
    session.setAttribute("year",year);
    Integer direction = 0;
    boolean reverse=false;
    if (req.getParameter("reverse")!=null) {
      try {
        reverse = Boolean.parseBoolean(req.getParameter("reverse"));
      }
      catch (Exception e ){
        reverse=false;
      }
    }

      if (reverse) {
         direction = 1 ;}

         else {
          direction = 0;
        }


    session.setAttribute("reverse",reverse);


    String category = "";
    try  {
      category= (req.getParameter("category"));
    } catch (Exception e){
      category="";
    }
    if (category==null){category="";}
    parameters.put("category",category);
    session.setAttribute("category",category);
    String sort = "";
    try  {
      sort= (req.getParameter("sort"));
    } catch (Exception e){
      sort="";
    }
    parameters.put("sort",sort);
    session.setAttribute("sort",sort);



  session.setAttribute("currentPage",req.getRequestURL());
  List<Transaction> transactions = null;
  int transaction_count=0;

  int page_size=20;

    int page_number = 1;
    int recordsPerPage = 5;
    if(req.getParameter("page") != null) {
      page_number = Integer.parseInt(req.getParameter("page"));
    }
    session.setAttribute("page_number",page_number);

    // page_size = req.get

    int offset=(page_number-1)*(page_size);


    try {
      transaction_count = TransactionDAO.getTransactionCountByUser(user.getUser_ID(),category,year);

      transactions = TransactionDAO.getTransactionByUser(user.getUser_ID(),category,year,page_size,offset,sort,direction);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    int total_pages = 1+(transaction_count/page_size);

    //https://stackoverflow.com/questions/31410007/how-to-do-pagination-in-jsp

    req.setAttribute("noOfPages", total_pages);
    //fix current page
    req.setAttribute("currentPage", page_number);
    List <Category> allCategories = categoryDAO.getCategoryByUser(user.getUser_ID());
    req.setAttribute("Categories", allCategories);


    req.setAttribute("Transactions", transactions);
  req.setAttribute("pageTitle", "All Transactions");
  req.getRequestDispatcher("WEB-INF/Budget_App/ViewAllTransactions.jsp").forward(req,resp);

}
}
