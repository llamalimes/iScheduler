package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.model.Friendship;
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

    @Override
    public void addFriend(User user, User friend) {
        Friendship friendship = new Friendship();
        friendship.setUser1(user);
        friendship.setUser2(friend);
        friendshipRepository.save(friendship);
    }

    @Override
    public void removeFriend(User user, User friend) {
        Friendship friendship = friendshipRepository.findByUser1AndUser2(user, friend);
        System.out.println(friendship);
        if (friendship != null) {
            friendshipRepository.delete(friendship);
        } else {
            // If the friendship does not exist, you may want to handle this case accordingly
            //throw new FriendshipNotFoundException("Friendship not found between user " + user.getId() + " and friend " + friend.getId());
        }
    }

}
