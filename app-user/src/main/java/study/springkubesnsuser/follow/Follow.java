package study.springkubesnsuser.follow;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;
    private Integer userId;
    private Integer followerId;
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime followDatetime;

    public Follow(Integer userId, Integer followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    @PrePersist
    protected void onPersist() {
        followDatetime = ZonedDateTime.now();
    }

}
