/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storedproclearn;

/**
 *
 * @author User
 */
public class Test {
    
    public static void main(String[] args) {
        Controller myController = new Controller();

        //insert queries
//        myController.addNewClass("1", "Nelum");
//        myController.addNewStudent("Atheesh", "Rathnaweera", "Maharagama", 4, "2020");
        myController.getStudentDataById(1);
        
    }
    
}
