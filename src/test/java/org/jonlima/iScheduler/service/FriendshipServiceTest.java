package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Friendship;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.repository.FriendshipRepository;
import org.jonlima.iScheduler.service.impl.FriendshipServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

class FriendshipServiceTest {

    @Mock
    private FriendshipRepository friendshipRepository;

    @InjectMocks
    private FriendshipServiceImpl friendshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("provideUsers")
    void addFriend(User user, User friend) {
        when(friendshipRepository.save(any(Friendship.class))).thenReturn(new Friendship());

        friendshipService.addFriend(user, friend);

        verify(friendshipRepository, times(1)).save(any(Friendship.class));
    }

    private static Stream<User[]> provideUsers() {
        return Stream.of(
                new User[] {new User(1L, "User1"), new User(2L, "User2")},
                new User[] {new User(3L, "User3"), new User(4L, "User4")},
                new User[] {new User(5L, "User5"), new User(6L, "User6")}
        );
    }
}
