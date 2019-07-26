package learn.webclient.controller;

import learn.webclient.Entity.Employee;
import learn.webclient.Service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
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

    @RequestMapping("/combination")
    public Map<String,Object> combinationReslut(){
        WebClient client=WebClient.create("http://localhost:8080");
        //
        //Employee employee=client.get().uri("/getEmp/{id}",2)
        //        .retrieve().bodyToMono(Employee.class).block();
        //
        //
        //Map<String,Employee> employees=client.get().uri("/getEmps")
        //        .retrieve().bodyToMono(Map.class).block();
        //
        ////List<Employee> employees=client.get().uri("/getEmps")
        ////        .retrieve().bodyToFlux(Employee.class).collectList().block();
        //
        //log.info("employee: "+employee);
        //log.info("employees: "+employees);
        //
        //
        //Map<String , Map> map=client.get().uri("/getEmps")
        //        .retrieve().bodyToFlux(Map.class).collectMap((Emp->{
        //            return "EmployMap";
        //        })).block();
        //
        //log.info("map！！！: " + map);

        Mono<Employee> empMono = client.get().uri("/getEmp/{id}",1).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Employee.class);

        Mono<List<String>> hobbiesMono = client.get().uri("/getEmp/{id}/hobbies",1).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(String.class).collectList();

        Map<String,Object> data=Mono.zip(empMono,hobbiesMono,(emp, hobbies)->{
            Map<String,Object> hashMap=new LinkedHashMap<>();
            hashMap.put("emp",emp);
            hashMap.put("hobbies",hobbies);
            return hashMap;
        }).block();

        log.info("result: "+empMono);
        log.info("results: " + hobbiesMono.toString());
        log.info("data: "+data.toString());

        return data;
    }
}
