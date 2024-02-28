package com.beck.javaiii_kirkwood.personal_project.models;

import java.time.LocalDate;

public class TwoFA {
  private Integer TwoFA_ID;
  private Integer User_ID;
  private String TwoFA_Code;
  private LocalDate DateSent;

  public TwoFA(){}

  public TwoFA(Integer TwoFA_ID, Integer User_ID, String TwoFA_Code, LocalDate DateSent) {

    this.TwoFA_ID = TwoFA_ID;
    this.User_ID = User_ID;
    this.TwoFA_Code = TwoFA_Code;
    this.DateSent = DateSent;
  }
  public Integer getTwoFA_ID() {
    return TwoFA_ID;
  }
  public void setTwoFA_ID(Integer TwoFA_ID) {
    this.TwoFA_ID = TwoFA_ID;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public String getTwoFA_Code() {
    return TwoFA_Code;
  }
  public void setTwoFA_Code(String TwoFA_Code) {
    if(TwoFA_Code.length()<4){
      throw new IllegalArgumentException("TwoFA_Code is too short.");
    }
    if(TwoFA_Code.length()>6){
      throw new IllegalArgumentException("TwoFA_Code is too long.");
    }
    this.TwoFA_Code = TwoFA_Code;
  }
  public LocalDate getDateSent() {
    return DateSent;
  }
  public void setDateSent(LocalDate DateSent) {
    this.DateSent = DateSent;
  }

}