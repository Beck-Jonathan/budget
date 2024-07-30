package com.beck.javaiii_kirkwood.budget_app.models;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 24/7/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines User Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>

import com.beck.javaiii_kirkwood.shared.MyValidators;

import java.util.List;
import java.util.regex.Matcher;

public class User {
  private Integer User_ID;
  private String User_Name;
  private char[] User_PW;
  private String Email;

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  private List<String> roles;

  public User(){}

  public User(Integer User_ID, String User_Name, char[] User_PW, String Email) {

    this.User_ID = User_ID;
    this.User_Name = User_Name;
    this.User_PW = User_PW;
    this.Email = Email;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public String getUser_Name() {
    return User_Name;
  }
  public void setUser_Name(String User_Name) {
    User_Name = User_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(User_Name.length()<4){
      throw new IllegalArgumentException("User_Name is too short.");
    }
    if(User_Name.length()>100){
      throw new IllegalArgumentException("User_Name is too long.");
    }
    this.User_Name = User_Name;
  }
  public char[] getUser_PW() {
    return User_PW;
  }
  public void setUser_PW(char[] User_PW) {
    if (User_PW!=null) {

      String passwordStr = String.valueOf(User_PW);
      Matcher matcher = MyValidators.passwordPattern.matcher(passwordStr);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Password must be 8 characters, with 3 of 4 (lowercase, uppercase, number, symbol)");
      }
    }
    this.User_PW = User_PW;
  }
  public String getEmail() {
    return Email;
  }
  public void setEmail(String Email) {
    if (Email!=null) {


      Matcher matcher = MyValidators.emailPattern.matcher(Email);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Invalid Email");
      }
    }
    this.Email = Email;
  }

}

