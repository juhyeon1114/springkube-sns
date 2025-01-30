package study.springkubesnsuser.user;

public record UserInfoResponse(
        Integer userId,
        String username,
        String email
) {

    public static UserInfoResponse create(User user) {
        return new UserInfoResponse(user.getUserId(), user.getUsername(), user.getEmail());
    }

}
