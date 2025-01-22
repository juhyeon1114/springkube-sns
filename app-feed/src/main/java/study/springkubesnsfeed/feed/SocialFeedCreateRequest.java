package study.springkubesnsfeed.feed;

public record SocialFeedCreateRequest(
        String imageId,
        int uploaderId,
        String contents
) {

}
