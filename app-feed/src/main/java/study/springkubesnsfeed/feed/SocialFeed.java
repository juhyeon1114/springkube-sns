package study.springkubesnsfeed.feed;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedId;
    private String imageId;
    private int uploaderId;
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime uploadDatetime;
    private String contents;

    @PrePersist
    protected void onCreate() {
        uploadDatetime = ZonedDateTime.now();
    }

    public SocialFeed(SocialFeedCreateRequest request) {
        this.imageId = request.imageId();
        this.uploaderId = request.uploaderId();
        this.contents = request.contents();
    }

}
