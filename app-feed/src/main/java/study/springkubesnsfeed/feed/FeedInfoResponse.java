package study.springkubesnsfeed.feed;

import java.time.ZonedDateTime;

public record FeedInfoResponse(
        Integer feedId,
        String imageId,
        Integer uploaderId,
        String uploaderName,
        ZonedDateTime uploadDatetime,
        String contents
) {

    public FeedInfoResponse(SocialFeed feed, String uploaderName) {
        this(
                feed.getFeedId(),
                feed.getImageId(),
                feed.getUploaderId(),
                uploaderName,
                feed.getUploadDatetime(),
                feed.getContents()
        );
    }

}
