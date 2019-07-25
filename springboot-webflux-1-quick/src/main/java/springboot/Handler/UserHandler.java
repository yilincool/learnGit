package springboot.Handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.Entity.User;
import springboot.Service.UserService;

@Component
@Slf4j
public class UserHandler {
    @Autowired
    UserService userService;

    public Mono<ServerResponse> getUserList(ServerRequest request){
        Flux<User> userFlux = userService.findUserList();
        userFlux.subscribe(user -> log.info(user.toString()));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userFlux,User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request){
        String userId=request.pathVariable("userId");
        Mono<User> userMono=userService.findUserById(userId);
        userMono.subscribe(user -> log.info(user.toString()));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userMono,User.class);
    }

    public Mono<ServerResponse> getUserName(ServerRequest request){
        String userId=request.pathVariable("userId");
        Mono<String> userMono=userService.findUserNameById(userId);
        userMono.subscribe(user -> log.info(user.toString()));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userMono,String.class);
    }

}
