package study.springkubesnsuser.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String email;
    private String password;

    public static User create(UserRequest request, String hashedPassword) {
        User user = new User();
        user.username = request.username();
        user.email = request.email();
        user.password = hashedPassword;
        return user;
    }

}
