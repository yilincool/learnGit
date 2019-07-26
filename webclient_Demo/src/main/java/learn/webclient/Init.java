package learn.webclient;

import learn.webclient.Entity.Employee;
import learn.webclient.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    EmployeeService employeeService;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        List<String> l1=new ArrayList<String>();
        l1.add("唱");
        l1.add("跳");
        l1.add("篮球");
        employeeService.setEmployee("1", new Employee("gggg", "2", "male", "dd",l1));
        employeeService.setEmployee("2", new Employee("ssss", "2", "s", "dd",l1));
        employeeService.setEmployee("3", new Employee("xxxxx", "h", "f", "dd",l1));
        employeeService.setEmployee("4", new Employee("zzzzz", "s", "s", "dd",l1));
        employeeService.setEmployee("5", new Employee("ccccc", "w", "j", "dd",l1));
    }
}
