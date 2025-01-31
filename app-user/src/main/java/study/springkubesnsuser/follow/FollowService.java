package study.springkubesnsuser.follow;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springkubesnsuser.user.UserInfoResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public boolean isFollow(Integer userId, Integer followerId) {
        return followRepository.findByUserIdAndFollowerId(userId, followerId).isPresent();
    }

    public Follow followUser(Integer userId, Integer followerId) {
        if (isFollow(userId, followerId)) {
            return null;
        }

        return followRepository.save(new Follow(userId, followerId));
    }

    public boolean unfollowUser(Integer userId, Integer followerId) {
        Follow follow = followRepository.findByUserIdAndFollowerId(userId, followerId).orElse(null);
        if (follow == null) {
            return false;
        }

        followRepository.delete(follow);
        return true;
    }

    public List<UserInfoResponse> listFollowers(Integer userId) {
        return followRepository.findFollowersByUserId(userId);
    }

    public List<UserInfoResponse> listFollowings(Integer userId) {
        return followRepository.findFollowingsByUserId(userId);
    }

}
