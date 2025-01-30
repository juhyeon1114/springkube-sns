package study.springkubesnsuser.user;

public record UserRequest(
        String username,
        String email,
        String plainPassword
) {

}
