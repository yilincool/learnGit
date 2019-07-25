package springboot.Service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private Map<String, User> userMap = new ConcurrentHashMap<>();

    public void setUser(String userId, User user) {
        userMap.put(userId, user);
    }

    public Mono<User> findUserById(String userId) {
        User user = userMap.getOrDefault(userId, new User("nick", 18));
        return Mono.just(user);
    }

    public Mono<String> findUserNameById(String userId) {
        User user = userMap.getOrDefault(userId, new User("nick", 18));
        return Mono.just(user.getName());
    }

    public Flux<User> findUserList(){
        //List<User> userList=new ArrayList<>();
        User[] userList=new User[5];
        Set<Map.Entry<String,User>> entries=userMap.entrySet();
        int i=0;
        for(Map.Entry<String,User> entry :entries){
            userList[i++]=entry.getValue();
        }
        return Flux.fromArray(userList);
        //return Flux.fromStream(userList.stream());
    }
}
