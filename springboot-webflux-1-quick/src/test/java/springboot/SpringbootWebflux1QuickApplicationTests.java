package springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.Entity.Quote;
import springboot.Entity.User;
import springboot.Service.UserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@AutoConfigureWebTestClient
public class SpringbootWebflux1QuickApplicationTests {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void fetchUsers() {
        //List<User> result =webTestClient.get().uri("/api/users").accept(MediaType.APPLICATION_JSON)
        //        .exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
        //        .returnResult(User.class).getResponseBody().collectList().block();
        String result = webClient.get().uri("/api/user/{id}",1).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class).block();

        List<String> results = webClient.get().uri("/api/users").accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(String.class).collectList().block();

        log.info("result: "+result);
        log.info("results: " + results.toString());
    }

    @Test
    public void fetchUsersSynchronous() {
        Mono<String> result = webClient.get().uri("/api/user/{id}",1).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class);

        Mono<List<String>> results = webClient.get().uri("/api/user/{id}/name",1).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(String.class).collectList();

        Map<String,Object> data=Mono.zip(result,results,(person,persons)->{
            Map<String,Object> map=new LinkedHashMap<>();
            map.put("person",person);
            map.put("persons",persons);
            return map;
        }).block();

        log.info("result: "+result);
        log.info("results: " + results.toString());

        log.info("data: "+data.toString());
    }

    //@Test
    //public void fetchQuotes() {
    //    webTestClient
    //            // We then create a GET request to test an endpoint
    //            .get().uri("/quotes?size=20")
    //            .accept(MediaType.APPLICATION_JSON)
    //            .exchange()
    //            // and use the dedicated DSL to test assertions against the response
    //            .expectStatus().isOk()
    //            .expectHeader().contentType(MediaType.APPLICATION_JSON)
    //            .expectBodyList(Quote.class)
    //            .hasSize(20)
    //            // Here we check that all Quotes have a positive price value
    //            .consumeWith(allQuotes ->
    //                    assertThat(allQuotes.getResponseBody())
    //                            .allSatisfy(quote -> assertThat(quote.getPrice()).isPositive()));
    //}
    //
    @Test
    public void fetchQuotesAsStream() {
        List<Quote> result = webTestClient
                // We then create a GET request to test an endpoint
                .get().uri("/quotes")
                // this time, accepting "application/stream+json"
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON)
                .returnResult(Quote.class)
                .getResponseBody()
                .take(30)
                .collectList()
                .block();

        log.info("Quote" + result.toString());

        //assertThat(result).allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());

    }


}
