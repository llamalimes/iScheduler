package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.Friendship;
import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.repository.FriendshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendshipServiceImplTest {

    @Mock
    private FriendshipRepository friendshipRepository;

    @InjectMocks
    private FriendshipServiceImpl friendshipService;

    private Users user1;
    private Users user2;
    private Friendship friendship;

    @BeforeEach
    void setUp() {
        user1 = new Users();
        user1.setId(1);
        user1.setName("Alice");

        user2 = new Users();
        user2.setId(2);
        user2.setName("Bob");

        friendship = new Friendship();
        friendship.setUsers1(user1);
        friendship.setUsers2(user2);

        when(friendshipRepository.findByUsers1AndUsers2(any(Users.class), any(Users.class))).thenReturn(friendship);
    }

    @Test
    void removeFriend_ShouldDeleteFriendshipIfExist() {
        // Act
        friendshipService.removeFriend(user1, user2);

        // Assert
        verify(friendshipRepository, times(1)).findByUsers1AndUsers2(user1, user2);
        verify(friendshipRepository, times(1)).delete(friendship);
    }

    @Test
    void removeFriend_ShouldDoNothingIfFriendshipDoesNotExist() {
        // Arrange
        when(friendshipRepository.findByUsers1AndUsers2(user1, user2)).thenReturn(null);

        // Act
        friendshipService.removeFriend(user1, user2);

        // Assert
        verify(friendshipRepository, times(1)).findByUsers1AndUsers2(user1, user2);
        verify(friendshipRepository, never()).delete(any(Friendship.class));
    }
}
