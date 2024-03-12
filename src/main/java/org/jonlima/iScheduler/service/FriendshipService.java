package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.User;

import java.util.List;

public interface FriendshipService {
    List<User> findFriendsByUserId(long id);
}
