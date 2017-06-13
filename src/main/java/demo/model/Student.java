package demo.model;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/13.
 * Java EE_1702.
 */
public class Student {
    private Integer id;
    private String name;
    private String gender;
    private String dob;

    public Student() {
    }

    public Student(Integer id, String name, String gender, String dob) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
