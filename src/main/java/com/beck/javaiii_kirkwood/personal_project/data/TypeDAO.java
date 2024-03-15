package com.beck.javaiii_kirkwood.personal_project.data;

import com.beck.javaiii_kirkwood.personal_project.models.Type;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.javaiii_kirkwood.personal_project.data.Database.getConnection;

public class TypeDAO {

  public static int add(Type _type) {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Type( ?)}")) {
          statement.setString(1, _type.getName());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Type. Try again later");
          }
        }
      }



    } catch (SQLException e) {
      throw new RuntimeException("Could not add Type. Try again later");
    }
    return numRowsAffected;
  }
  public static Type getTypeByPrimaryKey(Type _type) throws SQLException{
    Type result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Type(?)}")) {
        statement.setString(1, _type.getType_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Type_ID = resultSet.getInt("Type_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new Type( Type_ID, Name, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<Type> getAllType() {
    List<Type> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Type()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Type_ID = resultSet.getInt("Type_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Type _type = new Type( Type_ID, Name, is_active);
            result.add(_type);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Types. Try again later");
    }
    return result;}
  public static List<Type> getActiveType() {
    List<Type> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Type()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Type_ID = resultSet.getInt("Type_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Type _type = new Type( Type_ID, Name, is_active);
            result.add(_type);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Types. Try again later");
    }
    return result;}

  public static int update(Type oldType, Type newType) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Type(? ,?,?,?,?)}"))
        {
          statement.setInt(1,oldType.getType_ID());
          statement.setString(2,oldType.getName());
          statement.setString(3,newType.getName());
          statement.setBoolean(4,oldType.getis_active());
          statement.setBoolean(5,newType.getis_active());
          statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Type . Try again later");
        }
      }
    }
    return result;
  }
  public static int deleteType(int typeID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Type( ?)}")){
          statement.setInt(1,typeID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Type. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Type. Try again later");
    }
    return rowsAffected;
  }

}

