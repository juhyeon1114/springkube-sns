package study.springkubesnsuser.follow;

public record FollowRequest(
        Integer userId,
        Integer followerId
) {

}
