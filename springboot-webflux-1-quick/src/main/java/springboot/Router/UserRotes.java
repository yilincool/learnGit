package springboot.Router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import springboot.Handler.UserHandler;

@Configuration
public class UserRotes {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunctions(UserHandler userhandler) {

        return RouterFunctions.route(RequestPredicates.GET("/api/users"), userhandler::getUserList)
                .and(RouterFunctions.route(RequestPredicates.GET("/api/user/{userId}"), userhandler::getUser))
                .and(RouterFunctions.route(RequestPredicates.GET("/api/user/{userId}/name"),userhandler::getUserName));
    }
}
