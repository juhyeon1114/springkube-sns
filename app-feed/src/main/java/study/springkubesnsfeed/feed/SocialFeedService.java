package study.springkubesnsfeed.feed;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SocialFeedService {

    private final SocialFeedRepository socialFeedRepository;

    public List<SocialFeed> getAllFeeds() {
        return socialFeedRepository.findAll();
    }

    public List<SocialFeed> getAllFeedsByUploaderId(int uploaderId) {
        return socialFeedRepository.findByUploaderId(uploaderId);
    }

    public SocialFeed getFeedById(int feedId) {
        return socialFeedRepository.findById(feedId).orElse(null);
    }

    public void deleteFeed(int feedId) {
        socialFeedRepository.deleteById(feedId);
    }

    public SocialFeed createFeed(SocialFeedCreateRequest request) {
        return socialFeedRepository.save(new SocialFeed(request));
    }

}
