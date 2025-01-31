package study.springkubesnsfeed.feed;

public record UserInfoResponse(
        Integer userId,
        String username,
        String email
) {

}
