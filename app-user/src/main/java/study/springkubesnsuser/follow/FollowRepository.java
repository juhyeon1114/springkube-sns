package study.springkubesnsuser.follow;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.springkubesnsuser.user.UserInfoResponse;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findByUserIdAndFollowerId(Integer userId, Integer followerId);

    @Query(
            value = """
                    select new study.springkubesnsuser.user.UserInfoResponse(
                        u.userId, u.username, u.email
                    )
                    from Follow f
                    join User u on u.userId = f.followerId
                    where f.userId = :userId
                    """
    )
    List<UserInfoResponse> findFollowersByUserId(@Param("userId") Integer userId);

    @Query(
            value = """
                    select new study.springkubesnsuser.user.UserInfoResponse(
                        u.userId, u.username, u.email
                    )
                    from Follow f
                    join User u on u.userId = f.userId
                    where f.followerId = :userId
                    """
    )
    List<UserInfoResponse> findFollowingsByUserId(Integer userId);

}
