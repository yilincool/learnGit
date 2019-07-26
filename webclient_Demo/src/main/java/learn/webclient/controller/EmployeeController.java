package learn.webclient.controller;

import learn.webclient.Entity.Employee;
import learn.webclient.Service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/getEmp/{id}",method = RequestMethod.GET)
    //@ResponseBody
    public Employee getEmpById(@PathVariable String id){
        log.info("output: "+employeeService.getEmpById(id).toString());
        return employeeService.getEmpById(id);
    }

    @RequestMapping(value = "/getEmp/{id}/hobbies",method = RequestMethod.GET)
    //@ResponseBody
    public List<String> getEmpNameById(@PathVariable String id){
        log.info("output: "+employeeService.getEmpById(id).toString());
        return employeeService.getEmpHobbiesById(id);
    }

    @RequestMapping(value = "/getEmps",method = RequestMethod.GET)
    //@ResponseBody
    public Map<String, Employee> getEmpAll(){
        log.info("output: "+employeeService.getEmpAll().toString());
        return employeeService.getEmpAll();
    }
}
