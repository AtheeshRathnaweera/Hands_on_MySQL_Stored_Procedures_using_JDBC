package storedproclearn;

import java.sql.*;

public class Controller {

    public boolean addNewClass(String grade, String name) {

        Connection conn = CreateConnection.getConnection();

        try (CallableStatement statement = conn.prepareCall("{call add_class(?, ?)}")) {

            statement.setString(1, grade);
            statement.setString(2, name);

            statement.execute();
            statement.close();

            System.out.println("Controller : addNewClass : class saved successfully !");
            return true;

        } catch (SQLIntegrityConstraintViolationException constIn) {
            System.out.println("Controller : addNewClas : error occured : INVALID INPUTS " + constIn.toString());
            return false;

        } catch (SQLException ex) {
            System.out.println("Controller : addNewClas : error occured : " + ex.toString());
            return false;
        }

    }

    public boolean addNewStudent(String firstName, String lastName, String address, int classId, String year) {

        Connection conn = CreateConnection.getConnection();

        try (CallableStatement statement = conn.prepareCall("{call add_student(?, ?, ?, ?, ?)}")) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setInt(4, classId);
            statement.setString(5, year);

            statement.execute();
            statement.close();

            System.out.println("Controller : addNewStudent : student saved successfully !");
            return true;

        } catch (SQLIntegrityConstraintViolationException consEx) {
            //a foreign constraint fails
            System.out.println("Controller : addNewStudent : error occured : INVALID INPUTS . " + consEx.toString());
            return false;
        } catch (SQLException ex) {
            System.out.println("Controller : addNewStudent : error occured : " + ex.toString());
            return false;
        }
    }

    public void getStudentDataById(int id) {

        Connection conn = CreateConnection.getConnection();

        try (CallableStatement statement = conn.prepareCall("{call get_student(?)}")) {

            statement.setInt(1, id);//set student id to the first parameter

            boolean hadResults = statement.execute();

            while (hadResults) {
                ResultSet resultSet = statement.getResultSet();

                // process result set
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String address = resultSet.getString("address");

                    System.out.println("Rec student : "+Integer.toString(studentId)+" "+firstName+" "+lastName+" "+address);
                }

                hadResults = statement.getMoreResults();
            }

            statement.close();

        } catch (SQLException ex) {
            System.out.println("Controller : getStudentDataById : error occured : " + ex.toString());

        }

    }
}
