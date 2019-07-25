package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springboot.Entity.User;
import springboot.Service.UserService;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    UserService userService;


    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        userService.setUser("1", new User("carlos", 18));
        userService.setUser("2", new User("lisa", 19));
        userService.setUser("3", new User("mike", 17));
        userService.setUser("4", new User("tom", 16));
        userService.setUser("5", new User("amy", 15));

    }
}
