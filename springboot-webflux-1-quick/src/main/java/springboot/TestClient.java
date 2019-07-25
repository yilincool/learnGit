package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import springboot.Entity.Quote;
import springboot.Entity.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class TestClient {
    public static void main(String[] args) {
        WebClient client=WebClient.create("http://localhost:8080");
        //Mono<Quote> result=client.get()
        //        .uri("/quotes")
        //        .accept(MediaType.APPLICATION_JSON)
        //        .retrieve()
        //        .bodyToMono(Quote.class);
        //Quote users=result.block();
        ////JSONArray.toJSONString(users);
        ////log.info(JSONObject.wrap(person).toString());
        //log.info("output: "+users.getTicker());

        Mono<String> result = client.get().uri("/api/user/{id}",1).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class);

        Mono<List<String>> results = client.get().uri("/api/user/{id}/name",1).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(String.class).collectList();

        Map<String,Object> data=Mono.zip(result,results,(person, persons)->{
            Map<String,Object> map=new LinkedHashMap<>();
            map.put("person",person);
            map.put("persons",persons);
            return map;
        }).block();

        log.info("result: "+result);
        log.info("results: " + results.toString());

        log.info("data: "+data.toString());
    }
}
