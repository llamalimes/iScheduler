package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Friendship;
import org.jonlima.iScheduler.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FriendshipRepositoryTest {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    private Users user1;
    private Users user2;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        friendshipRepository.deleteAll();

        user1 = new Users();
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        user1.setPassword("password");
        userRepository.save(user1);

        user2 = new Users();
        user2.setName("Bob");
        user2.setEmail("bob@example.com");
        user2.setPassword("password");
        userRepository.save(user2);

        // Establishing a friendship
        Friendship friendship = new Friendship();
        friendship.setUsers1(user1);
        friendship.setUsers2(user2);
        friendshipRepository.save(friendship);
    }

    @Test
    void testFindFriendsByUserId() {
        List<Users> friendsOfUser1 = friendshipRepository.findFriendsByUserId(user1.getId());
        assertThat(friendsOfUser1).containsExactly(user2);
    }

    @Test
    void testFindByUsers1AndUsers2() {
        Friendship foundFriendship = friendshipRepository.findByUsers1AndUsers2(user1, user2);
        assertThat(foundFriendship).isNotNull();
        assertThat(foundFriendship.getUsers1()).isEqualTo(user1);
        assertThat(foundFriendship.getUsers2()).isEqualTo(user2);
    }
}
