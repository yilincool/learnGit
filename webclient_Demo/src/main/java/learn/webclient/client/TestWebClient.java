package learn.webclient.client;

import learn.webclient.Entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestWebClient {
    public static void main(String[] args) {
        WebClient client=WebClient.create("http://localhost:8080");

        Employee employee=client.get().uri("/getEmp/{id}",2)
                .retrieve().bodyToMono(Employee.class).block();


        Map<String,Employee> employees=client.get().uri("/getEmps")
                .retrieve().bodyToMono(Map.class).block();

        //List<Employee> employees=client.get().uri("/getEmps")
        //        .retrieve().bodyToFlux(Employee.class).collectList().block();

        log.info("employee: "+employee);
        log.info("employees: "+employees);


        Map<String , Map> map=client.get().uri("/getEmps")
                .retrieve().bodyToFlux(Map.class).collectMap((Emp->{
                   return "EmployMap";
                })).block();

        log.info("map！！！: " + map);

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
    }
}
