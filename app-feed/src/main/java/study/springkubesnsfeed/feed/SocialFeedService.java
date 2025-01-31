package study.springkubesnsfeed.feed;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@Transactional
@RequiredArgsConstructor
public class SocialFeedService {

    @Value("${sns.user-server}")
    private String userServerUrl;
    private final RestClient restClient = RestClient.create();
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

    public UserInfoResponse getUserInfo(Integer userId) {
        return restClient.get()
                .uri(userServerUrl + "/api/users/" + userId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ((request, response) -> {
                    throw new RuntimeException("User server error " + response.getStatusText());
                }))
                .body(UserInfoResponse.class);
    }

}
