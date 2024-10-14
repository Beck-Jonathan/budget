package com.beck.javaiii_kirkwood.budget_app.iData;

import com.beck.javaiii_kirkwood.budget_app.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.javaiii_kirkwood.budget_app.data.Database.getConnection;

/**
 * @author Jonathan
 * @version 1.5
 * @since 1.2
 */

public interface iUserDAO {
  int add(User _user);
  int getUserID(String email) throws SQLException;

  User getUserByPrimaryKey(User _user) throws SQLException ;

  String get_pw(String username) ;

  int getUserIDByUserName(String username);

  boolean usernameFree(String username) throws SQLException;

  boolean addDefaultCategories(int userID) throws SQLException;

  boolean emailFree(String email) throws SQLException ;

  boolean deleteUser(int userID) throws SQLException;
  boolean resetPW(User user) throws SQLException;
  List<String> getUser_Roles(User _user) throws SQLException;
  int addRole(String role, int userID);

  int yearRange(int user_ID) throws SQLException ;

}
