/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dtos.Student;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author atheesh27
 */
public class RetrieveDataController {

    public static Student getStudentData(Connection conn, int id, String recYear) {

        Student recStudent = new Student();

        try (CallableStatement statement = conn.prepareCall("{call get_student(?,?)}")) {

            statement.setInt(1, id);//set student id to the first parameter
            statement.setString(2, recYear);

            boolean hadResults = statement.execute();

            while (hadResults) {

                ResultSet resultSet = statement.getResultSet();

                // process result set
                while (resultSet.next()) {

                    recStudent.setId(resultSet.getInt("id"));
                    recStudent.setFirst_name(resultSet.getString("first_name"));
                    recStudent.setLast_name(resultSet.getString("last_name"));
                    recStudent.setAddress(resultSet.getString("address"));
                    recStudent.setClassId(resultSet.getInt("class_id"));

                    System.out.println("controllers : RetrieveController : " + recStudent.toString());
                }

                hadResults = statement.getMoreResults();
            }

            statement.close();

        } catch (SQLException ex) {
            System.out.println("controllers : RetrieveController : error occured : " + ex.toString());
        }
        return recStudent;
    }

}
