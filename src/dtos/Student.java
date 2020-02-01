package dtos;
/**
 *
 * @author atheesh27
 */
public class Student {
    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private int classId;

    public Student() {
    }

    public Student(int id, String first_name, String last_name, String address, int classId) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", address=" + address + ", classId=" + classId + '}';
    }
    
}
