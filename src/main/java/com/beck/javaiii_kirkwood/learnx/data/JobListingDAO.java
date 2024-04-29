package com.beck.javaiii_kirkwood.learnx.data;

import com.beck.javaiii_kirkwood.learnx.Models.Department;
import com.beck.javaiii_kirkwood.learnx.Models.JobListing;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.beck.javaiii_kirkwood.learnx.data.Database.getConnection;

public class JobListingDAO {
  public static void main(String[] args) {
    getAll().forEach(System.out::println);
  }

  public static List<JobListing> getAll(){
    return getAll(100,0,"","");
  }
  public static List<JobListing> getAll(int limit, int offset,String _department,String Location) {
    List<JobListing> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_get_job_listings(?,?,?,?)}"))

        {
          statement.setInt(1,limit);
          statement.setInt(2,offset);
          statement.setString(3,_department);
          statement.setString(4,Location);

          try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer job_id = resultSet.getInt("job_id");
            Integer department_id = resultSet.getInt("department_id");
            String department_name = resultSet.getString("department_name");
            Department department = new Department();
            department.setDepartment_id(department_id);
            department.setDepartment_name(department_name);
            Boolean featured = resultSet.getBoolean("featured");
            String position = resultSet.getString("position");
            Timestamp posted_at = resultSet.getTimestamp("posted_at");
            Instant posted_at2= posted_at.toInstant();

            //Instant posted_at = Instant.from(Instant.now());
            String contract = resultSet.getString("contract");
            String location = resultSet.getString("location");
            String job_description = resultSet.getString("job_description");
            JobListing _job_listing = new JobListing( job_id, department, featured, position, posted_at2, contract, location, job_description);
            result.add(_job_listing);
          }
        }
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve job_listings. Try again later");
    }
    return result;}

}