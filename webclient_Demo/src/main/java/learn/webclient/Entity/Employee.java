package learn.webclient.Entity;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private String name;
    private String birth;
    private String gender;
    private String order;
    private List<String> hobbies;
    //private Department department;
    public Employee(){

    }

    public Employee(String name, String birth, String gender, String order, List<String> hobbies) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.order = order;
        this.hobbies = hobbies;
    }
}


