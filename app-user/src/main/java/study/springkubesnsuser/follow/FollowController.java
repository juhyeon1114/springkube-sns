package study.springkubesnsuser.follow;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springkubesnsuser.user.UserInfoResponse;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/followers/{userId}")
    public List<UserInfoResponse> listFollowers(@PathVariable("userId") Integer userId) {
        return followService.listFollowers(userId);
    }

    @GetMapping("/followings/{userId}")
    public List<UserInfoResponse> listFollowings(@PathVariable("userId") Integer userId) {
        return followService.listFollowings(userId);
    }

    @GetMapping("/follow/{userId}/{followerId}")
    public boolean isFollow(@PathVariable("userId") Integer userId, @PathVariable("followerId") Integer followerId) {
        return followService.isFollow(userId, followerId);
    }

    @PostMapping("/follow")
    public Follow followUser(@RequestBody FollowRequest request) {
        return followService.followUser(request.userId(), request.followerId());
    }


    @PostMapping("/unfollow")
    public boolean unfollowUser(@RequestBody FollowRequest request) {
        return followService.unfollowUser(request.userId(), request.followerId());
    }

}
