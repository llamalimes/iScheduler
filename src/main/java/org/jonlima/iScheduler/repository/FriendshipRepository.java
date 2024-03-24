package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Friendship;
import org.jonlima.iScheduler.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT f.users2 FROM Friendship f WHERE f.users1.id = :userId")
    List<Users> findFriendsByUserId(@Param("userId") Long userId);

    //@Query("SELECT f FROM Friendship f WHERE (f.users1.id = :userId1 AND f.users2.id = :userId2)")
    Friendship findByUsers1AndUsers2(Users users1, Users users2);

    //void deleteFriendshipById(long friendshipId);
}
