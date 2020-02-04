package controllers;

import dtos.Student;
import dtos.StudentClass;
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

                }

                hadResults = statement.getMoreResults();
            }

            statement.close();
            
            System.out.println("controllers : RetrieveController : getStudentData : " + recStudent.toString());
            
        } catch (SQLException ex) {
            System.out.println("controllers : RetrieveController : getStudentData : error occured : " + ex.toString());
        }
        return recStudent;
    }

    public static StudentClass getClassById(Connection conn, int classId) {

        StudentClass tempClass = new StudentClass();

        try (CallableStatement statement = conn.prepareCall("{call get_class_by_id(?)}")) {

            statement.setInt(1, classId);

            boolean hadResults = statement.execute();

            while (hadResults) {

                ResultSet resultSet = statement.getResultSet();

                // process result set
                while (resultSet.next()) {

                    tempClass.setId(resultSet.getInt("id"));
                    tempClass.setGrade(resultSet.getString("grade"));
                    tempClass.setName(resultSet.getString("name"));

                }

                hadResults = statement.getMoreResults();
            }

            statement.close();

            System.out.println("controllers : RetrieveController : getClassData : " + tempClass.toString());

        } catch (SQLException ex) {
            System.out.println("controllers : RetrieveController : getCLassById : error occured : " + ex.toString());
        }

        return tempClass;

    }

}
