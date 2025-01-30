package study.springkubesnsuser.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public UserInfoResponse signUp(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/sign-in")
    public UserInfoResponse signIn(@RequestBody UserRequest request) {
        return userService.signIn(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable("id") int id) {
        UserInfoResponse user = userService.getUser(id);
        return user == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(user);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserInfoResponse> getUserInfoByName(@PathVariable("name") String name) {
        UserInfoResponse user = userService.getUserByName(name);
        return user == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(user);
    }

}
