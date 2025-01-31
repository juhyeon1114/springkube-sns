package study.springkubesnsfeed.feed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
public class SocialFeedController {

    private final SocialFeedService socialFeedService;

    @GetMapping
    public List<FeedInfoResponse> getAllFeeds() {
        List<FeedInfoResponse> responses = new ArrayList<>();
        List<SocialFeed> feeds = socialFeedService.getAllFeeds();
        for (SocialFeed feed : feeds) {
            UserInfoResponse userInfo = socialFeedService.getUserInfo(feed.getUploaderId());
            FeedInfoResponse feedInfo = new FeedInfoResponse(feed, userInfo.username());
            responses.add(feedInfo);
        }
        return responses;
    }

    @GetMapping("/user/{userId}")
    public List<SocialFeed> getAllFeedsByUploaderId(@PathVariable("userId") int userId) {
        return socialFeedService.getAllFeedsByUploaderId(userId);
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<SocialFeed> getFeedById(@PathVariable("feedId") int feedId) {
        SocialFeed feedById = socialFeedService.getFeedById(feedId);
        return Objects.nonNull(feedById)
                ? ResponseEntity.ok(feedById)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeed(@PathVariable("id") int id) {
        socialFeedService.deleteFeed(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public SocialFeed createFeed(@RequestBody SocialFeedCreateRequest request) {
        return socialFeedService.createFeed(request);
    }

}
