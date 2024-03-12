package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.repository.FriendshipRepository;
import org.jonlima.iScheduler.service.FriendshipService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FriendshipServiceImpl implements FriendshipService {
    private FriendshipRepository friendshipRepository;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public List<User> findFriendsByUserId(long id) {
        return friendshipRepository.findFriendsByUserId(id);
    }


}
