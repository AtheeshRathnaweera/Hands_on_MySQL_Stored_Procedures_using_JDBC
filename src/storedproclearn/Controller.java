package storedproclearn;

import dtos.Student;
import java.sql.*;

public class Controller {

    private static Connection conn = CreateConnection.getConnection();

    public void signIn(int id, String pw) {

        try (CallableStatement statementSignIn = conn.prepareCall("{call signIn(?,?,?)}")) {

            statementSignIn.setInt(1, id);
            statementSignIn.setString(2, pw);
            statementSignIn.registerOutParameter(3, Types.VARCHAR);

            statementSignIn.execute();

            System.out.println("reeee : " + statementSignIn.getString("returnMessage"));

            statementSignIn.close();

        } catch (SQLException ex) {
            System.out.println("COntroller : signIn : " + ex.toString());
        }

    }

    public void logIn(int id, String password) {
        try (CallableStatement statementSignIn = conn.prepareCall("{call logIn(?,?,?)}")) {

            statementSignIn.setInt(1, id);
            statementSignIn.setString(2, password);
            statementSignIn.registerOutParameter(3, Types.VARCHAR);

            statementSignIn.execute();

            System.out.println("reeee : " + statementSignIn.getString("returnMessage"));

            statementSignIn.close();

        } catch (SQLException ex) {
            System.out.println("COntroller : signIn : " + ex.toString());
        }

    }

    public boolean addNewClass(String grade, String name) {


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

    public void getStudentDataById(int id, String recYear) {


        try (CallableStatement statement = conn.prepareCall("{call get_student(?,?)}")) {

            statement.setInt(1, id);//set student id to the first parameter
            statement.setString(2, recYear);

            boolean hadResults = statement.execute();

            while (hadResults) {

                ResultSet resultSet = statement.getResultSet();

                // process result set
                while (resultSet.next()) {
                    Student recStudent = new Student();

                    recStudent.setId(resultSet.getInt("id"));
                    recStudent.setFirst_name(resultSet.getString("first_name"));
                    recStudent.setLast_name(resultSet.getString("last_name"));
                    recStudent.setAddress(resultSet.getString("address"));
                    recStudent.setClassId(resultSet.getInt("class_id"));

                    System.out.println("Rec student : " + recStudent.toString());
                }

                hadResults = statement.getMoreResults();
            }

            statement.close();

        } catch (SQLException ex) {
            System.out.println("Controller : getStudentDataById : error occured : " + ex.toString());

        }

    }
}
