package learn.webclient.Service;

import learn.webclient.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {
    private Map<String, Employee> empMap = new ConcurrentHashMap<>();

    public void setEmployee(String empId, Employee employee) {
        empMap.put(empId, employee);
    }

    public Employee getEmpById(String empId){
        return empMap.get(empId);
    }
    public List<String> getEmpHobbiesById(String empId){
        return empMap.get(empId).getHobbies();
    }

    public Map<String, Employee> getEmpAll(){
        return empMap;
    }


}
