package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Users;

import java.util.List;

public interface FriendshipService {
    List<Users> findFriendsByUserId(long id);
    void addFriend(Users users, Users friend);
    void removeFriend(Users users, Users friend);
}
