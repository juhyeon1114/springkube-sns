package study.springkubesnsuser.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfoResponse createUser(UserRequest request) {
        // Validation
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new RuntimeException("Username already exists");
        });

        userRepository.findByEmail(request.email()).ifPresent(user -> {
            throw new RuntimeException("Email already exists");
        });

        // Create user
        String hashedPassword = passwordEncoder.encode(request.plainPassword());
        User user = userRepository.save(User.create(request, hashedPassword));
        return UserInfoResponse.create(user);
    }

    public UserInfoResponse getUser(int userId) {
        User user = userRepository.findById(userId)
                .orElse(null);

        return user == null ? null : UserInfoResponse.create(user);
    }

    public UserInfoResponse getUserByName(String name) {
        User user = userRepository.findByUsername(name)
                .orElse(null);
        return user == null ? null : UserInfoResponse.create(user);
    }

    public UserInfoResponse signIn(UserRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElse(null);

        if (user == null) {
            return null;
        }

        boolean isPasswordMatched = passwordEncoder.matches(request.plainPassword(), user.getPassword());
        if (!isPasswordMatched) {
            return null;
        }

        return UserInfoResponse.create(user);
    }

}
