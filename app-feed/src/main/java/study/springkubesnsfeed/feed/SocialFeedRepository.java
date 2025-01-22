package study.springkubesnsfeed.feed;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialFeedRepository extends JpaRepository<SocialFeed, Integer> {

    List<SocialFeed> findByUploaderId(int uploaderId);

}
