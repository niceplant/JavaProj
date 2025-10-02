package app;

import model.User;
import dao.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createOrGetUser(@RequestParam String name, @RequestParam(required = false) String email) {
        return userRepository.findByName(name).orElseGet(() -> {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            return userRepository.save(user);
        });
    }
}
