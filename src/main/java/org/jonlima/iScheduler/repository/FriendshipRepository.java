package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Friendship;
import org.jonlima.iScheduler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT f.user2 FROM Friendship f WHERE f.user1.id = :userId")
    List<User> findFriendsByUserId(@Param("userId") Long userId);

    //@Query("SELECT f FROM Friendship f WHERE (f.user1.id = :userId1 AND f.user2.id = :userId2)")
    Friendship findByUser1AndUser2(User user1, User user2);

    void deleteFriendshipById(long friendshipId);
}
